package ru.job4j.grabber;

import ru.job4j.grabber.utils.SqlRuDateTimeParser;
import ru.job4j.html.SqlRuParse;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"), cfg.getProperty("username"), cfg.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into post(name, text, link, created) values(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    posts.add(new Post(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("link"),
                            rs.getString("text"),
                            rs.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        String sql = String.format("select * from post where id = %s", id);
        try (PreparedStatement statement = cnn.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    post = new Post(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("link"),
                            rs.getString("text"),
                            rs.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws IOException {
        SqlRuParse srp = new SqlRuParse(new SqlRuDateTimeParser());
        Post post = srp.detail("https://www.sql.ru/forum/1336747/programmist-ms-sql-udalyonno-150-200tr-net");
        Post post2 = srp.detail("https://www.sql.ru/forum/1332112-1/programmist-prikladnogo-po-c-po-produktu-polator");
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream(
                "sql_ru.properties")) {
            Properties config = new Properties();
            config.load(in);
            try (PsqlStore store = new PsqlStore(config)) {
                store.save(post);
                store.save(post2);
                System.out.println();
                System.out.println("Выводим все объявления: ");
                store.getAll().forEach(System.out::println);
                System.out.println();
                System.out.println("Выводим объявление с id = 2: ");
                System.out.println(store.findById(2));
                System.out.println();
                System.out.println("Выводим объявление с id = 1: ");
                System.out.println(store.findById(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
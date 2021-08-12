package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse srp = new SqlRuParse(new SqlRuDateTimeParser());
        Post post = srp.detail("https://www.sql.ru/forum/1336747/programmist-ms-sql-udalyonno-150-200tr-net");
        System.out.println(post);
        System.out.println();
        Post post2 = srp.detail("https://www.sql.ru/forum/1332112-1/programmist-prikladnogo-po-c-po-produktu-polator");
        System.out.println(post2);
        System.out.println();
        List<Post> list = srp.list("https://www.sql.ru/forum/job-offers");
        list.forEach(System.out::println);
    }

    /**
     * Данный метод принимает в качестве аргумента ссылку на сайт вакансий и возвращает список объектов Post,
     * каждый из которых содержит ненулевые поля title, link и created
     * @param link Ссылка на сайт с вакансиями
     * @return List объектов Post c инициализированными полями link и title
     * @throws IOException
     */
    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> rsl = new ArrayList<>();
        for (int j = 1; j < 6; j++) {
            Document doc = Jsoup.connect(String.format("%s/%s", link, j)).get();
            Elements row = doc.select(".postslisttopic");
            Elements rowDate = doc.select("tr > td:nth-child(6)");
            for (int i = 0; i < row.size(); i++) {
                Element href = row.get(i).child(0);
                String postLink = href.attr("href");
                String title = String.format(
                        "%s%sДата обновления: %s", href.text(), System.lineSeparator(), rowDate.get(i).text()
                        );
                Document forPostLink = Jsoup.connect(postLink).get();
                String created = forPostLink.select(".msgFooter").get(0).text().split(" \\[")[0];
                rsl.add(new Post(title, postLink, this.dateTimeParser.parse(created)));
            }
        }
        return rsl;
    }

    /**
     * Принимает ссылку на вакансию и возвращает обЪект Post с её описанием.
     * @param link ссылка на вакансию
     * @return объект Post с ненулевыми полями title, link, created, description
     * @throws IOException
     */
        @Override
        public Post detail(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String name = doc.title().split(" / ")[0];
        String description =  doc.select(".msgBody").get(1).text();
        String created = doc.select(".msgFooter").get(0).text().split(" \\[")[0];
        return new Post(name, link, description, this.dateTimeParser.parse(created));
    }
}


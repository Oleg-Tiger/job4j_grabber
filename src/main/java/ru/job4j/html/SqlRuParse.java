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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;
    private Map<String, String> tittles = new HashMap<>();

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse srp = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> list = srp.list("https://www.sql.ru/forum/job-offers");
        Post post =  srp.detail(list.get(9).getLink());
        System.out.println(post);
    }

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
                rsl.add(new Post(title, postLink));
                this.tittles.put(postLink, title);
            }
        }
        return rsl;
    }

        @Override
        public Post detail(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String description =  doc.select("table:nth-child(3) > tbody > tr:nth-child(2) > td:nth-child(2)").text();
        String created = doc.select("table:nth-child(3) > tbody > tr:nth-child(3)").text().split(" \\[")[0];
        return new Post(this.tittles.get(link), link, description, this.dateTimeParser.parse(created));
    }
}


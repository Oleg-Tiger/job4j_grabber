package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int j = 1; j < 6; j++) {
            Document doc = Jsoup.connect(String.format("https://www.sql.ru/forum/job-offers/%s", j)).get();
            Elements row = doc.select(".postslisttopic");
            Elements rowDate = doc.select("tr > td:nth-child(6)");
            for (int i = 0; i < row.size(); i++) {
                Element href = row.get(i).child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                System.out.println(rowDate.get(i).text());
            }
        }
    }

    private static void uploadAPostDescriptionAndCreated(Post post) throws IOException {
        Document doc = Jsoup.connect(post.getLink()).get();
        post.setDescription(doc.select("table:nth-child(3) > tbody > tr:nth-child(2) > td:nth-child(2)").text());
        String date = doc.select("table:nth-child(3) > tbody > tr:nth-child(3)").text();
        post.setCreated(new SqlRuDateTimeParser().parse(date.split(" \\[")[0]));
    }
}

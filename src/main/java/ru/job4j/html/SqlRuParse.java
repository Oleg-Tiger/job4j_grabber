package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
}
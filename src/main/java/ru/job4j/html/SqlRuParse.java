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
            }
        }
        return rsl;
    }

        @Override
        public Post detail(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String table = "table:nth-child(3)";
        Elements pages = doc.select("#content-wrapper-forum > table:nth-child(2) > tbody > tr > td > a");
        if (pages.size() != 0) {
            table = "table:nth-child(4)";
        }
        String description =  doc.select(table.concat(" > tbody > tr:nth-child(2) > td:nth-child(2)")).text();
        String created = doc.select(table.concat(" > tbody > tr:nth-child(3)")).text().split(" \\[")[0];
        String name = doc.select(table.concat(" > tbody > tr:nth-child(1)")).get(0).text().replace(" [new]", "");
        if (pages.size() != 0) {
            doc = createDocForLastPage(pages);
        }
        Elements rowDate = doc.select("tr:nth-child(3) > td");
        String title = String.format(
                    "%s%sДата обновления: %s", name, System.lineSeparator(), rowDate.get(rowDate.size() - 1).text().split(" \\[")[0]
            );
            return new Post(title, link, description, this.dateTimeParser.parse(created));
    }

    private Document createDocForLastPage(Elements pages) throws IOException {
            String linkOfLastPage = pages.get(pages.size() - 3).attr("href");
            return Jsoup.connect(linkOfLastPage).get();
    }
}


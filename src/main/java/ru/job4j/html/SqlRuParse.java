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

        /* pages содержит ссылки для перехода на другие страницы обсуждения и, если его размер не равен нулю, значит в данном обсуждении
            несколько страниц, следовательно, ссылки на имя, дату и описание в первом посте будут отличаться. Также, чтобы
            получить дату обновления, придётся получить дату последнего поста на последней странице */

        Elements pages = doc.select("table:nth-child(2) > tbody > tr > td > a");
        if (pages.size() != 0) {
            table = "table:nth-child(4)";
        }

        /* Создаём переменные, содержащие описание вакансии, название (без даты обновления), дату создания в формате String
        Ссылки будут разные для одно- и многостраничного обсуждения, они зависят от переменной table */

        String description =  doc.select(table.concat(" > tbody > tr:nth-child(2) > td:nth-child(2)")).text();
        String created = doc.select(table.concat(" > tbody > tr:nth-child(3)")).text().split(" \\[")[0];
        String name = doc.select(table.concat(" > tbody > tr:nth-child(1)")).get(0).text().replace(" [new]", "");

        /* Если в обсуждении много страниц, то создаём Document для парсинга HTML последней страницы для получения даты обновления
        Либо продолжаем читать с первой страницы */

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


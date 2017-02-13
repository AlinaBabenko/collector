package collectorOfVacancies.big01.model;

import collectorOfVacancies.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by Алина on 09.02.2017.
 */
public class RabotaKharkovStrategy implements Strategy {
    private static final String URL_FORMAT =
            "https://www.rabota.kharkov.ua/vacancies/search/page2";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL_FORMAT).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").
                    referrer("google.com.ua").get();
            System.out.println(doc.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByAttributeValue("class", "card");
        for (Element element: elements) {
            System.out.println(element.text());
        }


        return null;
    }
    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, null, page);
        Document doc = Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").
                referrer("google.com.ua").get();


        return doc;
    }

}

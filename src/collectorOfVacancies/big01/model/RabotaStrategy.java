package collectorOfVacancies.big01.model;


import collectorOfVacancies.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алина on 09.02.2017.
 */
public class RabotaStrategy implements Strategy {
    private static final String URL_FORMAT =
            "https://rabota.ua/jobsearch/vacancy_list?regionId=%s&keyWords=Java&pg=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)  {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 1;
        try {
            for (Document doc = getDocument(searchString, page); doc != null; doc = getDocument(searchString, ++page)) {
                Elements elements = doc.getElementsByAttributeValue("class", "f-vacancylist-vacancyblock");
                if (elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();

                    String title = element.getElementsByAttributeValue("class", "f-visited-enable").first().text();
                    vacancy.setTitle(title);

                    String companyName = element.getElementsByAttributeValue("class", "f-visited-enable").get(1).text();
                    vacancy.setCompanyName(companyName);

                    String city = searchString;
                    vacancy.setCity(city);

                    String salary = element.getElementsByAttributeValue("class", "fd-beefy-soldier -price").text();
                    vacancy.setSalary(salary);

                    String siteName = "https://rabota.ua/";
                    vacancy.setSiteName(siteName);

                    String link = siteName + element.getElementsByTag("a").attr("href");
                    vacancy.setUrl(link);

                    vacancies.add(vacancy);

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        String idRegion = null;
        switch (searchString) {
            case "Киев": idRegion = "1"; break;
            case "Днепр": idRegion = "4"; break;
            case "Запорожье": idRegion = "9"; break;
            case "Одесса": idRegion = "3"; break;
            case "Харьков": idRegion = "21"; break;
            case "Львов": idRegion = "2"; break;

        }


        String url = String.format(URL_FORMAT, idRegion, page);
        Document doc = Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").
                referrer("google.com.ua").get();

        return doc;
    }


}



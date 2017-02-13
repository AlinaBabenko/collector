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
 * Created by Алина on 23.01.2017.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://hh.ua/search/vacancy?text=java+%s&page=%d";



    @Override
    public List <Vacancy> getVacancies(String searchString)  {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try {
            for (Document doc = getDocument(searchString, page); doc != null; doc = getDocument(searchString, ++page)) {


                Elements elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;

                for (Element element : elements) {

                    Vacancy vacancy = new Vacancy();



                    String title = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text();
                    vacancy.setTitle(title);

                    String companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text();
                    vacancy.setCompanyName(companyName);

                    String city = searchString;
                    vacancy.setCity(city);

                    String salary  = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text();
                    if (salary.isEmpty()) {
                        vacancy.setSalary(" ");
                    }
                    else
                    vacancy.setSalary(salary);

                    String siteName = "http://hh.ua/";
                    vacancy.setSiteName(siteName);

                    String link = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
                    vacancy.setUrl(link);

                    vacancies.add(vacancy);
                }
            }
        } catch (Exception e) {}

        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        switch (searchString) {
            case "Киев": searchString = "Kiev"; break;
            case "Днепр": searchString = "Dnepr"; break;
            case "Запорожье": searchString = "Zaporoje"; break;
            case "Одесса": searchString = "Odessa"; break;
            case "Харьков": searchString = "Kharkov"; break;
            case "Львов": searchString = "Lviv"; break;

        }

     String url = String.format(URL_FORMAT, searchString, page);


        Document doc = Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").
                referrer("google.com.ua").get();


        return doc;
    }
}

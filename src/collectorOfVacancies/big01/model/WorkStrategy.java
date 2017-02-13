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
 * Created by Алина on 03.02.2017.
 */
public class WorkStrategy implements Strategy {
    private static final String URL_FORMAT = "https://www.work.ua/jobs-%s-java/?page=%d";


    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;

        try {
        for (Document doc = getDocument(searchString, page); doc!=null; doc = getDocument(searchString, ++page)) {

            Elements elements = doc.getElementsByAttributeValueContaining("class","card card-hover card-visited job-link");

            if (elements.size()==0) break;

            for (Element elem: elements) {

                Vacancy vacancy = new Vacancy();
                String title = elem.getElementsByTag("a").text();
                vacancy.setTitle(title);


                String companyName = elem.getElementsByTag("span").first().text();
                vacancy.setCompanyName(companyName);


                String salary = elem.getElementsByAttributeValue("data-toggle", "popover").text();
                if (!salary.isEmpty()) {
                vacancy.setSalary(salary);}
                else vacancy.setSalary(" ");


                String city = searchString;
                vacancy.setCity(city);

                String siteName = "https://www.work.ua/";
                vacancy.setSiteName(siteName);

                String link = siteName+ elem.getElementsByTag("a").attr("href");
                vacancy.setUrl(link);

              vacancies.add(vacancy);

            }
               }
        } catch (IOException e) {
         //   e.printStackTrace();
        }


        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, searchString, page);
        Document doc = Jsoup.connect(url).
                userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36").
                referrer("google.com.ua").get();
     //   System.out.println(doc);

        return doc;
    }
}

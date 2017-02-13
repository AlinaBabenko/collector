package collectorOfVacancies.big01.view;


import collectorOfVacancies.big01.Controller;
import collectorOfVacancies.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Алина on 31.01.2017.
 */
public class HtmlView implements View {
private Controller controller;
    private final String filePath = "./src/"+this.getClass().getPackage().getName().replaceAll("\\.","/")+"/vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies) {
       try {
        updateFile(getUpdatedFileContent(vacancies));}
       catch (Exception e) {
           e.printStackTrace();
           System.out.println("Some exception occurred");
       }

    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }
    public void userCitySelectEmulationMethod() {
        // Select city - Харьков, Днепр, Львов, Одесса, Киев
        controller.onCitySelect("Харьков");
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc = null;
        try {
            doc = getDocument();
           // System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element elem = doc.getElementsByClass("template").first();
        Element cloneElem = elem.clone();
        cloneElem.removeClass("template").removeAttr("style");
        doc.getElementsByAttributeValue("class","vacancy").remove();
        for (Vacancy vacancy: vacancies) {
            Element element = cloneElem.clone();
            element.getElementsByAttributeValue("class", "city").first().text(vacancy.getCity());
            element.getElementsByAttributeValue("class", "companyName").first().text(vacancy.getCompanyName());
            element.getElementsByAttributeValue("class", "salary").first().text(vacancy.getSalary());
            Element link = element.getElementsByTag("a").first();
            link.text(vacancy.getTitle());
            link.attr("href", vacancy.getUrl());
            elem.before(element.outerHtml());
        }
        return doc.html();
    }

    private void updateFile(String content) throws IOException {
        File file = new File(filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.flush();
        writer.close();
    }
   protected Document getDocument() throws IOException {
     return  Jsoup.parse(new File(filePath), "UTF-8");

   }

}

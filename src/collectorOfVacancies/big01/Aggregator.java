package collectorOfVacancies.big01;


import collectorOfVacancies.big01.model.*;
import collectorOfVacancies.big01.view.HtmlView;

/**
 * Created by Алина on 10.01.2017.
 */
public class Aggregator {
    public static void main(String[] args) {

        Provider providerWork = new Provider(new WorkStrategy());
        Provider providerRabota = new Provider(new RabotaStrategy());
        Provider providerHH = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, providerRabota, providerWork, providerHH);
        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod();










    }
}

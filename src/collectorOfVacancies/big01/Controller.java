package collectorOfVacancies.big01;

import collectorOfVacancies.big01.model.Model;


/**
 * Created by Алина on 23.01.2017.
 */
public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model==null) throw new IllegalArgumentException();
        else this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}

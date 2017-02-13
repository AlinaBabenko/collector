package collectorOfVacancies.big01.model;

import collectorOfVacancies.big01.view.View;
import collectorOfVacancies.big01.vo.Vacancy;

import java.util.ArrayList;


/**
 * Created by Алина on 31.01.2017.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view==null || providers==null || providers.length==0) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }
    public void selectCity(String city) {
        ArrayList<Vacancy> vacancies = new ArrayList<>();

        for (Provider provider: providers) {
            vacancies.addAll(provider.getJavaVacancies(city));
           }
        view.update(vacancies);

        }
    }



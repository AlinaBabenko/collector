package collectorOfVacancies.big01.model;

import collectorOfVacancies.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Алина on 22.01.2017.
 */
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public List<Vacancy> getJavaVacancies(String searchString) {
      return  strategy.getVacancies(searchString);
        }
}

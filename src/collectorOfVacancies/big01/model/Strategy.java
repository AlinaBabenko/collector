package collectorOfVacancies.big01.model;

import collectorOfVacancies.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Алина on 22.01.2017.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}

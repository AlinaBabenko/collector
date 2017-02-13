package collectorOfVacancies.big01.view;

import collectorOfVacancies.big01.Controller;
import collectorOfVacancies.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Алина on 31.01.2017.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}

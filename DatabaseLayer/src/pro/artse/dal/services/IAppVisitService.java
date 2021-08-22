package pro.artse.dal.services;

import java.time.LocalDate;
import java.util.List;
import pro.artse.dal.dto.AppVisitDTO;

public interface IAppVisitService {
	void add();

	int getOn(LocalDate date);

	List<AppVisitDTO> getInLast(int numberOfDays);
}

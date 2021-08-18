package pro.artse.dal.services;
import pro.artse.dal.dto.*;

import java.time.LocalDate;
import java.util.*;

public interface IFlightService {
	
	public List<FlightDTO> getAll(LocalDate date, boolean isDepartured);
	
	public List<FlightDTO> getFeatured(boolean isDepartured);
}

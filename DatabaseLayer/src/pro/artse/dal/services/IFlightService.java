package pro.artse.dal.services;
import pro.artse.dal.dto.*;
import java.util.*;

public interface IFlightService {
	
	public List<FlightDTO> getAll(boolean isDepartured);
	
	public List<FlightDTO> getFeatured(boolean isDepartured);
}

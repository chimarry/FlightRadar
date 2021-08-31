package pro.artse.employee.mapper;

import org.mapstruct.Mapper;

import pro.artse.employee.entities.Flight;
import pro.artse.employee.wrapper.FlightWrapper;

@Mapper
public interface IFlightMapper {
	public Flight sourceToDestination(FlightWrapper wrapper);
}

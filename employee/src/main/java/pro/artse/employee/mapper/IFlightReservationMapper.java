package pro.artse.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pro.artse.employee.entities.FlightReservation;
import pro.artse.employee.wrapper.FlightReservationWrapper;

@Mapper(componentModel="spring")
public interface IFlightReservationMapper {

	@Mappings({ @Mapping(target = "arrivalCityName", source = "entity.flight.arrivalCity.name"),
			@Mapping(target = "arrivalCountryName", source = "entity.flight.arrivalCity.country.name"),
			@Mapping(target = "departureCountryName", source = "entity.flight.departureCity.country.name"),
			@Mapping(target = "departureCityName", source = "entity.flight.departureCity.name"),
			@Mapping(target = "username", source = "entity.account.username"),
			@Mapping(target = "accountId", source = "entity.account.accountId"),
			@Mapping(target = "flightType", source = "entity.flight.type"),
			@Mapping(target = "airportDateTime", source = "entity.flight.airportDateTime"),
			@Mapping(target = "createdOn", source="entity.createdOn")})
	public FlightReservationWrapper toWrapper(FlightReservation entity);
}

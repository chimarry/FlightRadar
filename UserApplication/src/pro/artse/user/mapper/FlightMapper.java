package pro.artse.user.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pro.artse.dal.dto.FlightDTO;
import pro.artse.user.beans.FlightBean;

public final class FlightMapper {

	public static final FlightBean mapToBean(FlightDTO dto) {
		FlightBean bean = new FlightBean(dto.getFlightId(), dto.getArrivalCityId(), dto.getDepartureCityId(),
				dto.getAirportDateTime(), dto.getArrivalCityName(), dto.getDepartureCityName(), dto.getType());
		bean.setStatus(dto.getStatus());
		bean.calculateAndSetTime();
		return bean;
	}

	public static final ArrayList<FlightBean> mapToBeans(List<FlightDTO> flights) {
		return flights.stream().map(x -> FlightMapper.mapToBean(x)).collect(Collectors.toCollection(ArrayList::new));
	}
}

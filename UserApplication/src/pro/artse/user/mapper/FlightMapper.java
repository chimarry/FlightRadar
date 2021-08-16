package pro.artse.user.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pro.artse.dal.dto.FlightDTO;
import pro.artse.user.beans.FlightBean;

public final class FlightMapper {

	public static final FlightBean mapToBean(FlightDTO dto, boolean isDeparture) {
		FlightBean bean = new FlightBean(dto.getFlightId(), dto.getArrivalCityId(), dto.getDepartureCityId(),
				dto.getArrivalOn(), dto.getDepartureOn(), dto.getArrivalCityName(), dto.getDepartureCityName(),
				dto.getType());
		bean.setStatus(dto.getStatus());
		bean.calculateAndSetTime(isDeparture);
		return bean;
	}

	public static final ArrayList<FlightBean> mapToBeans(List<FlightDTO> flights, boolean isDeparture) {
		return flights.stream().map(x -> FlightMapper.mapToBean(x, isDeparture))
				.collect(Collectors.toCollection(ArrayList::new));
	}
}

package pro.artse.user.mapper;

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
}

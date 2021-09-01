import { FlightReservation } from "./flight-reservation";
import { FlightReservationStatus } from "./flight-reservation-status";
import { FlightType } from "./flight-type";

export class TransportFlightReservation extends FlightReservation {
    cargoDescription: string | null;

    constructor(username?: string, flightReservationId?: number, airportDateTime?: string,
        arrivalCityName?: string, arrivalCountryName?: string, departureCityName?: string,
        departureCountryName?: string, createdOn?: string, status?: FlightReservationStatus,
        flightType?: FlightType, cargoDescription?: string) {
        super(username, flightReservationId, airportDateTime, arrivalCityName, arrivalCountryName,
            departureCityName, departureCountryName, createdOn, status, flightType);
        this.cargoDescription = cargoDescription || null;
    }
}

import { FlightReservation } from "./flight-reservation";
import { FlightReservationStatus } from "./flight-reservation-status";
import { FlightType } from "./flight-type";

export class PassengerFlightReservation extends FlightReservation {
    seatNumber: number | null;

    constructor(username?: string, flightReservationId?: number, airportDateTime?: string,
        arrivalCityName?: string, arrivalCountryName?: string, departureCityName?: string,
        departureCountryName?: string, createdOn?: string, status?: FlightReservationStatus,
        flightType?: FlightType, seatNumber?: number) {
        super(username, flightReservationId, airportDateTime, arrivalCityName, arrivalCountryName,
            departureCityName, departureCountryName, createdOn, status, flightType);
        this.seatNumber = seatNumber || null;
    }
}
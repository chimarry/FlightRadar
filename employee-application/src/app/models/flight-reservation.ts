import { FlightReservationStatus } from "./flight-reservation-status"
import { FlightType } from "./flight-type";

export class FlightReservation {
    username: string | null;
    flightReservationId: number | null;
    airportDateTime: string | null;
    arrivalCityName: string | null;
    arrivalCountryName: string | null;
    departureCityName: string | null;
    departureCountryName: string | null;
    createdOn: string | null;
    status: FlightReservationStatus | null;
    flightType: FlightType | null;
    cancellationReason: string | null;

    constructor(username?: string, flightReservationId?: number, airportDateTime?: string,
        arrivalCityName?: string, arrivalCountryName?: string, departureCityName?: string,
        departureCountryName?: string, createdOn?: string, status?: FlightReservationStatus,
        flightType?: FlightType, cancellationReason?: string) {
        this.username = username || null;
        this.flightReservationId = flightReservationId || null;
        this.airportDateTime = airportDateTime || null;
        this.arrivalCityName = arrivalCityName || null;
        this.arrivalCountryName = arrivalCountryName || null;
        this.departureCityName = departureCityName || null;
        this.departureCountryName = departureCountryName || null;
        this.createdOn = createdOn || null;
        this.status = status || null;
        this.flightType = flightType || null;
        this.cancellationReason = cancellationReason || null;
    }
}

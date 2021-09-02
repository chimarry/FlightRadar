import { FlightType } from "./flight-type";

export class Flight {
    arrivalCityId: number | null;
    departureCityId: number | null;
    type: FlightType | FlightType.Passenger;
    airportDateTimes: Date[] | null;

    constructor( arrivalCityId?: number, departureCityId?: number, type?: FlightType, dates?: Date[]) {
        this.type = type || FlightType.Passenger;
        this.arrivalCityId = arrivalCityId || null;
        this.departureCityId = departureCityId || null;
        this.airportDateTimes = dates|| null;
    }
}

import { FlightType } from "./flight-type";

export class Flight {
    flightId: number | null;
    arrivalCityId: number | null;
    departureCityId: number | null;
    type: FlightType | FlightType.Passenger;
    date: Date | null;
    time: string | null;

    constructor(flightId?: number, arrivalCityId?: number, departureCityId?: number, type?: FlightType, date?: Date, time?: string) {
        this.type = type || FlightType.Passenger;
        this.flightId = flightId || null;
        this.arrivalCityId = arrivalCityId || null;
        this.departureCityId = departureCityId || null;
        this.date = date || null;
        this.time = time || null;
    }
}

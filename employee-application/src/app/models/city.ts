export class City {
    cityId: number;
    countryId: number;
    name: string;

    constructor(cityId: number, countryId: number, name: string) {
        this.cityId = cityId;
        this.countryId = countryId;
        this.name = name;
    }
}

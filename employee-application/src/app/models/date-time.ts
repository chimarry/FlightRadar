export class DateTime {
    time: string | null;
    date: Date | null;
    selectable: boolean = false;

    constructor(time?: string, date?: Date) {
        this.time = time || null;
        this.date = date || null;
    }

    getAsString(): string {
        return this.date?.toDateString + " " + this.time;
    }

    getDateWithTime(): Date {
        if (this.time != null) {
            let values = this.time.split(":");
            this.date?.setHours(Number(values[0]));
            this.date?.setMinutes(Number(values[1]));
        }
        return this.date ?? new Date();
    }
}

export class DateTime {
    time: string | null;
    date: Date | null;
    selectable: boolean = false;

    constructor(time?: string, date?: Date) {
        this.time = time || null;
        this.date = date || null;
    }

    getDateWithTime(): Date {
        if (this.time != null) {
            let values = this.time.split(":");
            this.date?.setHours(Number(values[0]));
            this.date?.setMinutes(Number(values[1]));
        }
        console.log(this.date?.toISOString())
        if (this.date != null)
            this.date = new Date(this.date?.toLocaleString('en-US', { timeZone: 'Europe/Paris' }));
        return this.date ?? new Date();
    }
}

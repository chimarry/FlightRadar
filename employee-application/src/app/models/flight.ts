export class Flight {
    id: number | null;
    name: string | null;
    description: string | null;
    image: string | null;
    date: Date | null;
    time: string | null;

    constructor(id?: number, name?: string, description?: string, image?: string,
        date?: Date, time?: string) {
        this.id = id || null;
        this.name = name || null;
        this.description = description || null;
        this.image = image || null;
        this.date = date || null;
        this.time = time || null;
    }
}

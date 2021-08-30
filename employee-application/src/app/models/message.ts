export class Message {
    email: string | null;
    name: string | null;
    text: string | null;

    constructor(email?: string, name?: string, text?: string) {
        this.name = name || null;
        this.email = email || null;
        this.text = text || null;
    }
}

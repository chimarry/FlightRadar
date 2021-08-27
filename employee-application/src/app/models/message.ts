export class Message {
    email: string;
    name: string;
    text: string;

    constructor(email: string, name: string, text: string) {
        this.name = name;
        this.email = email;
        this.text = text;
    }
}

export class Message {
    messageId: number | null;
    email: string | null;
    name: string | null;
    text: string | null;

    constructor(messageId?: number, email?: string, name?: string, text?: string) {
        this.messageId = messageId || null;
        this.name = name || null;
        this.email = email || null;
        this.text = text || null;
    }
}

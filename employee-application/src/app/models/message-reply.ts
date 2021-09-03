export class MessageReply {
    messageId: number | null;
    email: string | null;
    title: string | null;
    content: string | null;

    constructor(messageId?: number, email?: string, title?: string, content?: string) {
        this.messageId = messageId || null;
        this.title = title || null;
        this.email = email || null;
        this.content = content || null;
    }
}

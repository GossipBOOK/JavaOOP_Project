public class ChatItem {
    private String name;
    private String message;
    private String date;
    private byte[] image_data;

    public ChatItem(String name, String message, String date, byte[] image_data) {
        this.name = name;
        this.message = message;
        this.date = date;
        this.image_data = image_data;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public byte[] getImage_data() {
        return image_data;
    }
}


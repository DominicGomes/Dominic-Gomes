package contact;

public class Message {
    private String name;
    private String text;
    private int id;

    public String getName() {
        return name;
    }
    public void getDetails(){
        System.out.println("Name: "+name+"\nMessage: "+text+"\nGoogle ID: "+id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message(String name, String text, int id) {
        this.name = name;
        this.text = text;
        this.id = id;
    }
}

package Quiz;

public class Joker {

    private final String jokerName;
    private boolean available;

    public Joker(String jokerName) {
        this.jokerName = jokerName;
        this.available = true;
    }

    public String getJokerName() {
        return jokerName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}

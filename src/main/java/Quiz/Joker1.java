package Quiz;

public class Joker1 {

    private final String jokerName;
    private boolean available;

    public Joker1(String jokerName) {
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

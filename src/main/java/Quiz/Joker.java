//manages the bare bones of Jokers

package Quiz;

public class Joker {

    //Class variables
    private final String jokerName;
    private boolean available;

    //Constructors
    public Joker(String jokerName) {
        this.jokerName = jokerName;
        this.available = true;
    }

    //Methods
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

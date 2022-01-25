//manages player information

package Quiz;

public class Player {

    //Class variables
    private final String playerName;
    private final int score;
    private final String timeStamp;

    //Constructor
    public Player(String playerName, int score, String timeStamp) {
        this.playerName = playerName;
        this.score = score;
        this.timeStamp = timeStamp;
    }

    //Methods
    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}

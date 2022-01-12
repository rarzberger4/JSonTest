//manages the player information

package Quiz;

public class Player {

    //Class variables
    private final String playerName;
    private final int Score;
    private final String timeStamp;

    //Constructor
    public Player(String playerName, int score, String timeStamp) {
        this.playerName = playerName;
        this.Score = score;
        this.timeStamp = timeStamp;
    }

    //Methods
    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return Score;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}

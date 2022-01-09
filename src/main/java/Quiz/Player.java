package Quiz;

public class Player {

    private final String playerName;
    private final int Score;
    private final String timeStamp;

    public Player(String playerName, int score, String timeStamp) {
        this.playerName = playerName;
        this.Score = score;
        this.timeStamp = timeStamp;
    }

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

package Quiz;

public class Player {
    String playerName;
    int Score;
    String timeStamp;

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

}

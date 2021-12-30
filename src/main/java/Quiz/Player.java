package Quiz;

public class Player {
    String playerName;
    int Score;

    public Player(String playerName, int score) {
        this.playerName = playerName;
        Score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

}

package Quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Highscore {
    private List<Player> highscore = new ArrayList<>();

    public Highscore() {
        try (BufferedReader br = new BufferedReader(new FileReader("Highscore.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Player player = new Player(line.split(",")[0], Integer.parseInt(line.split(",")[1]));
                highscore.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printHighscore() {
        for (Player player : highscore) {
            System.out.println(player.getPlayerName()+"\t"+player.getScore());
        }
    }

    public void addNewHighscore(String playerName, int score) {
        Player player = new Player(playerName, score);
        highscore.add(player);
    }

    public void safeNewHighscore() throws IOException {
        FileWriter writer = new FileWriter("test.csv");
        for (Player player: highscore) {
            writer.append(player.getPlayerName());
            writer.append(",");
            writer.append(String.valueOf(player.getScore()));
            writer.append(System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }

}

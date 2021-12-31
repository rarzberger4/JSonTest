package Quiz;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Highscore {
    private List<Player> highscore = new ArrayList<>();

    public Highscore() {
        if (!Files.exists(Paths.get("Highscore.csv"))) {
            highscore = new ArrayList<>();
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader("Highscore.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Player player = new Player(line.split(",")[0], Integer.parseInt(line.split(",")[1]), line.split(",")[2]);
                    highscore.add(player);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printHighscore() {
        highscore.sort((p1, p2) -> p2.getScore() - p1.getScore());
        System.out.println("Player\tScore\tTime");
        for (Player player : highscore) {
            System.out.println(player.playerName+"\t"+player.Score+"\t\t"+player.timeStamp);
        }
    }

    public void addNewHighscore(String playerName, int score) {
        String time = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Timestamp(System.currentTimeMillis()));
        Player player = new Player(playerName, score, time);
        highscore.add(player);
        highscore.sort((p1, p2) -> p2.getScore() - p1.getScore());
    }


    public void safeNewHighscore() throws IOException {
        FileWriter writer = new FileWriter("Highscore.csv");
        for (Player player: highscore) {
            writer.append(player.playerName);
            writer.append(",");
            writer.append(String.valueOf(player.Score));
            writer.append(",");
            writer.append(player.timeStamp);
            writer.append(System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }

}

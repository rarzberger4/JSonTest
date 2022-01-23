//allows that high scores can be saved between games

package Quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Highscore {

    //Class variables
    private List<Player> highscore = new ArrayList<>();

    //Constructor
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

    public List<Player> getHighscore() {
        return highscore;
    }

    //writes current Highscore to Highscore.csv
    public void updateHighscore(String playerName, int score) throws IOException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Timestamp(System.currentTimeMillis()));
        Player p = new Player(playerName, score, time);
        highscore.add(p);
        highscore.sort((p1, p2) -> p2.getScore() - p1.getScore());
        FileWriter writer = new FileWriter("Highscore.csv");
        for (int i = 0; i < 20 && i < highscore.size(); i++) {
            writer.append(highscore.get(i).getPlayerName());
            writer.append(",");
            writer.append(String.valueOf(highscore.get(i).getScore()));
            writer.append(",");
            writer.append(highscore.get(i).getTimeStamp());
            writer.append(System.lineSeparator());
        }
        writer.flush();
        writer.close();
    }

}

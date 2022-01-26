package Quiz;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stage05Controller implements Initializable {

    @FXML
    private Button quitGameButton;
    @FXML
    private TableView<Player> highscoreTableView;
    @FXML
    private TableColumn<Player, String > playerNameTableViewColumn;
    @FXML
    private TableColumn<Player, Integer> scoreTableViewColumn;
    @FXML
    private TableColumn<Player, String> timeStampTableViewColumn;

    private final Highscore h = new Highscore();

    // start scene initializing TableView using players as items of high score
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerNameTableViewColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        scoreTableViewColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeStampTableViewColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        highscoreTableView.setItems(FXCollections.observableArrayList((h.getHighscore()))); // make high score an observable arraylist and use items to fill table
    }

    public void onPlayGameButtonClick(ActionEvent actionEvent) throws IOException {
        MainStageController.onPlayGameButtonClick(actionEvent);
    }

    public void onQuitGameButtonClick() {
        MainStageController.onQuitGameButtonClick(quitGameButton);
    }

}

package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stage04Controller implements Initializable {

    @FXML
    private Button quitGameButton;
    @FXML
    private Label congratsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void startScene(Game game) {
        congratsLabel.setText("Congratulations, you finished the game!" + System.lineSeparator() + game.getVictory());
    }

    @FXML
    public void onPlayGameButtonClick(ActionEvent actionEvent) throws IOException {
        MainStageController.onPlayGameButtonClick(actionEvent);
    }

    @FXML
    public void onShowHighscoreBClick(ActionEvent actionEvent) throws IOException {
        MainStageController.onShowHighscoreBClick(actionEvent);
    }

    @FXML
    public void onQuitGameButtonClick() {
        MainStageController.onQuitGameButtonClick(quitGameButton);
    }

}

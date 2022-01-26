package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stage04Controller {

    @FXML
    private Label congratsLabel;
    @FXML
    private Button quitGameButton;

    // start using current game to create congratulation message
    public void startScene(Game game) {
        congratsLabel.setText("Congratulations, you finished the game!" + System.lineSeparator() + game.getVictory());
    }

    public void onPlayGameButtonClick(ActionEvent actionEvent) throws IOException {
        MainStageController.onPlayGameButtonClick(actionEvent);
    }

    public void onShowHighscoreBClick(ActionEvent actionEvent) throws IOException {
        MainStageController.onShowHighscoreBClick(actionEvent);
    }

    public void onQuitGameButtonClick() {
        MainStageController.onQuitGameButtonClick(quitGameButton);
    }

}

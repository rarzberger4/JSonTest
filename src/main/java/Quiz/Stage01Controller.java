package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Stage01Controller {

    @FXML
    private Button quitGameButton;

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

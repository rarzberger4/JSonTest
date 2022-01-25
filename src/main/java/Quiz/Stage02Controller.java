package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Stage02Controller {

    @FXML
    private ToggleGroup buttonGroup;
    @FXML
    private Button quitGameButton;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private ToggleButton nineQuestionsButton;
    @FXML
    private ToggleButton twelveQuestionsButton;
    @FXML
    private ToggleButton fifteenQuestionsButton;

    private String playerName;
    private int maxQuestions;

    @FXML
    public void getPlayerName() {
        playerName = playerNameTextField.getText();
    }

    public void getMaxQuestions() {
        Toggle selectedToggle = buttonGroup.getSelectedToggle();
        if (selectedToggle == nineQuestionsButton) {
            maxQuestions = 9;
        } else if (selectedToggle == twelveQuestionsButton) {
            maxQuestions = 12;
        } else if (selectedToggle == fifteenQuestionsButton){
            maxQuestions = 15;
        } else {
            maxQuestions = 0;
        }
    }

    @FXML
    public void onStartGameButtonClick(ActionEvent actionEvent) throws IOException {
        getPlayerName();
        getMaxQuestions();
        if (playerName == null || playerName.isEmpty() || maxQuestions == 0) {
            Alert alert;
            if (playerName == null  || playerName.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR, "Please enter a player name.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Please choose a game length.");
            }
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stage03_PlayGame.fxml")) ;
            Parent root = loader.load();
            Stage03Controller controller = loader.getController();
            controller.startScene(new Game(playerName, maxQuestions));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onQuitGameButtonClick() {
        MainStageController.onQuitGameButtonClick(quitGameButton);
    }

}

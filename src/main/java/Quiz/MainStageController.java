package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStageController { //Is in control of what the buttons should do

    @FXML
    public static void onPlayGameButtonClick(ActionEvent actionEvent) throws IOException {
        //After an IO operation on Play Game Stage02 appears
        FXMLLoader loader = new FXMLLoader(MainStageController.class.getResource("/Stage02_SetUpGame.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public static void onShowHighscoreBClick(ActionEvent actionEvent) throws IOException {
        //After an IO operation on Show Highscore Stage05 appears
        FXMLLoader loader = new FXMLLoader(MainStageController.class.getResource("/Stage05_Highscore.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public static void onQuitGameButtonClick(Button quitButton) {
        //The Button Quit Game exists to close the Game
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you really want to quit?");
        alert.setTitle("Quit Game");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(type -> {
            if (type == yes) {
                Stage stage = (Stage) quitButton.getScene().getWindow();
                stage.close();
            }
        });
    }

}

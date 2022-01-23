package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Stage03Controller implements Initializable {

    @FXML
    private Label questionNumberLabel;
    @FXML
    private Label currentScoreLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button answer0Button;
    @FXML
    private Button answer1Button;
    @FXML
    private Button answer2Button;
    @FXML
    private Button answer3Button;
    @FXML
    private Button fiftyFiftyButton;
    @FXML
    private Button skipQuestionButton;
    @FXML
    private Button hintButton;
    @FXML
    private ProgressBar myProgressBar;
    @FXML
    private Button quitGameButton;

    private Game myGame;
    private final Sounds s = new Sounds();
    private final Highscore h = new Highscore();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void startScene(Game game) {
        myGame = game;
        myGame.addQuestionNumber();
        drawNewQuestion();
    }

    public void onAnswerButton0Click(ActionEvent actionEvent) throws IOException {
        onAnswerButtonClick(actionEvent, 0);
    }

    public void onAnswerButton1Click(ActionEvent actionEvent) throws IOException {
        onAnswerButtonClick(actionEvent, 1);
    }

    public void onAnswerButton2Click(ActionEvent actionEvent) throws IOException {
        onAnswerButtonClick(actionEvent, 2);
    }

    public void onAnswerButton3Click(ActionEvent actionEvent) throws IOException {
        onAnswerButtonClick(actionEvent, 3);
    }

    public void onFiftyFiftyButtonClick() {
        Boolean[] ans = new Boolean[] {false, false, false, false};
        ArrayList<Integer> answers = new ArrayList<>(List.of(0, 1, 2, 3));
        answers.remove(myGame.getRightAnswerInt());  //remove index of right answer --> indices of 3 wrong answers remain
        answers.remove((int) (Math.random() * 3));        //remove random index of 1 of 3 remaining wrong answer --> indices of 2 wrong answers remain
        for (int i: answers) {
            ans[i] = true;      // set Disable of 2 remaining wrong answers to true
        }
        answer0Button.setDisable(ans[0]);
        answer1Button.setDisable(ans[1]);
        answer2Button.setDisable(ans[2]);
        answer3Button.setDisable(ans[3]);
        fiftyFiftyButton.setDisable(true);
    }

    public void onSkipQuestionButtonClick() {
        drawNewQuestion();
        skipQuestionButton.setDisable(true);
    }

    public void onHintButtonClick() {
        questionLabel.setText(myGame.getQuestionAsString() + System.lineSeparator() + System.lineSeparator() + "Hint: " + myGame.getHint());
        hintButton.setDisable(true);
    }

    public void onAnswerButtonClick(javafx.event.ActionEvent actionEvent, int i) throws IOException {
        if (myGame.getRightAnswerInt() == i) {
            myGame.addPoints();
            Alert alert = new Alert(Alert.AlertType.NONE, "Correct!", ButtonType.OK);
            s.playPosSound();
            alert.showAndWait();
        } else {
            myGame.deductPoints();
            Alert alert = new Alert(Alert.AlertType.NONE, ("Wrong... Correct answer: " + myGame.getRightAnswerString()), ButtonType.OK);
            s.playNegSound();
            alert.showAndWait();
        }
        if (myGame.getQuestionNumber() != myGame.getMaxQuestions()) {
            myGame.addQuestionNumber();
            drawNewQuestion();
            myProgressBar.setProgress((double) myGame.getQuestionNumber()/myGame.getMaxQuestions());
        } else {
            h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stage04_GameEnd.fxml")) ;
            Parent root = loader.load();
            Stage04Controller controller = loader.getController();
            controller.startScene(myGame);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void drawNewQuestion() {
        myGame.drawNewQuestion();
        currentScoreLabel.setText("Current score: " + myGame.getPoints());
        questionLabel.setText(myGame.getQuestionAsString());
        answer0Button.setText(myGame.getAnswer(0));
        answer1Button.setText(myGame.getAnswer(1));
        answer2Button.setText(myGame.getAnswer(2));
        answer3Button.setText(myGame.getAnswer(3));
        questionNumberLabel.setText("Question number " + myGame.getQuestionNumber() + " of " + myGame.getMaxQuestions());
        answer0Button.setDisable(false);
        answer1Button.setDisable(false);
        answer2Button.setDisable(false);
        answer3Button.setDisable(false);
        questionLabel.requestFocus();
    }

    @FXML
    public void onQuitGameButtonClick() {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you really want to quit? " + System.lineSeparator() + "Your current score will be added to the highscore.");
        alert.setTitle("Quit Game");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(type -> {
            if (type == yes) {
                try {
                    h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) quitGameButton.getScene().getWindow();
                stage.close();
            }
        });
    }

}

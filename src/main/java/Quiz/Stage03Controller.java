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

public class Stage03Controller {

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

    public void startScene(Game game) {
        myGame = game;
        myGame.addQuestionNumber();
        drawNewQuestion();
    }

    public void drawNewQuestion() {
        myGame.drawNewQuestion();
        currentScoreLabel.setText("Current score: " + myGame.getPoints());
        questionLabel.setText(myGame.getQuestionAsString());
        questionNumberLabel.setText("Question number " + myGame.getQuestionNumber() + " of " + myGame.getMaxQuestions());
        Button[] answerButtons = new Button[] {answer0Button, answer1Button, answer2Button, answer3Button};
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setDisable(false);
            answerButtons[i].setText(myGame.getAnswer(i));
        }
        questionLabel.requestFocus();
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

    public void onAnswerButtonClick(ActionEvent actionEvent, int i) throws IOException {
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
            // open Stage 4
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

    public void onFiftyFiftyButtonClick() {
        Boolean[] ans = new Boolean[] {false, false, false, false};
        List<Integer> answers = new ArrayList<>(List.of(0, 1, 2, 3));
        answers.remove(myGame.getRightAnswerInt());  // remove index of right answer --> indices of 3 wrong answers remain
        answers.remove((int) (Math.random() * 3));   // remove 1 random index of 3 remaining wrong answers --> indices of 2 wrong answers remain in arraylist
        for (int i: answers) {
            ans[i] = true;                           // change value of two values in array ans > true (used to set Disable > true)
        }
        Button[] answerButtons = new Button[] {answer0Button, answer1Button, answer2Button, answer3Button};
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setDisable(ans[i]);     // set Disable of all buttons according to array ans
        }
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

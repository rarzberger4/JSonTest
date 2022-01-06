package Quiz;


import java.util.ArrayList;

public class Game {

    private Questionnaire questionnaire;
    private final int maxQuestions;
    private int questionNumber;
    private int points;
    private Player player;
    private Joker1 hint = new Joker1("Hint");
    private Joker1 fifty = new Joker1("50/50");


    public Game(Questionnaire questionnaire, int maxQuestions, String playerName) {
        this.questionnaire = questionnaire;
        this.maxQuestions = maxQuestions;
        this.questionNumber = 0;
        this.points = 0;
        this.player = new Player(playerName, 0, "");
        Joker1 hint = this.hint;
        Joker1 fifty = this.fifty;
    }

    public Question getQuestion() {
        int difficulty = (int) Math.ceil((float)this.questionNumber/(float)this.maxQuestions*3);
        return this.questionnaire.randomQuestion(difficulty);
    }

    public void addQuestionNumber() {
        this.questionNumber++;
    }

    public void addPoints() {
        int difficulty = (int) Math.ceil((float)this.questionNumber/(float)this.maxQuestions*3);
        this.points += this.questionNumber * difficulty;
    }

    public void deductPoints() {
        int difficulty = (int) Math.ceil((float)this.questionNumber/(float)this.maxQuestions*3);
        if (this.points > this.questionNumber * difficulty / 3) {
            this.points -= this.questionNumber * difficulty / 3;
        } else {
            this.points = 0;
        }
    }

    public String printStatus() {
        return "Current points: " + this.points + System.lineSeparator();
    }

    public String printQuestionNumber() {
        return "Question number " + this.questionNumber + " of " + this.maxQuestions + ":";
    }

    public boolean End() {
        return this.questionNumber == this.maxQuestions;
    }

    public String printVictory() {
        String output = this.points + " of " + 108 + " Points!";
        if (this.points == this.maxQuestions) {
            output =  "Full score! Incredible!";
        } else if (this.points < this.maxQuestions *0.5) {
            output = "You need some practice. You only have " + output;
        } else if (this.points < this.maxQuestions *0.8) {
            output = "Good job! You have " + output;
        } else {
            output = "Excellent! You have " + output;
        }
        return output;
    }

    public int getPoints() {
        return this.points;
    }

    public String getPlayerName() {
        return this.player.getPlayerName();
    }

    public void useFiftyFifty() {

        this.fifty.setAvailable(false);
    }


}

package Quiz;

public class Game {

    private final Questionnaire[] questionnaire;
    private final int maxQuestions;
    private int questionNumber;
    private int points;
    private Player player;

    public Game(Questionnaire[] questionnaire, int maxQuestions, String playerName) {
        this.questionnaire = questionnaire;
        this.maxQuestions = maxQuestions;
        this.questionNumber = 0;
        this.points = 0;
        this.player = new Player(playerName, 0);
    }

    public Question getQuestion() {
        int difficulty;
        if (this.questionNumber <= this.maxQuestions /3) {
            difficulty = 1;
        } else if (this.questionNumber <= this.maxQuestions /3*2) {
            difficulty = 2;
        } else {
            difficulty = 3;
        }
        return this.questionnaire[0].randomQuestion(difficulty);
    }

    public void addQuestionNumber() {
        this.questionNumber++;
    }

    public void addPoints() {
        this.points++;
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
        String output = this.points + " of " + this.maxQuestions + " Points!";
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
}

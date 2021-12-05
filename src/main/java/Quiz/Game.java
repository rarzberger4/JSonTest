package Quiz;

public class Game {

    private final Questionnaire[] questionnaire;
    private final int maxQuestions;
    private int questionNumber;
    private int points;

    public Game(Questionnaire[] questionnaire, int maxQuestions) {
        this.questionnaire = questionnaire;
        this.maxQuestions = maxQuestions;
        this.questionNumber = 0;
        this.points = 0;
    }

    public Question GetQuestion() {
        int difficulty;
        if (this.questionNumber <= this.maxQuestions /3) {
            difficulty = 1;
        } else if (this.questionNumber <= this.maxQuestions /3*2) {
            difficulty = 2;
        } else {
            difficulty = 3;
        }
        return this.questionnaire[0].RandomQuestion(difficulty);
    }

    public void AddQuestionNumber() {
        this.questionNumber++;
    }

    public void AddPoints() {
        this.points++;
    }

    public String PrintStatus() {
        return "Current points: " + this.points + System.lineSeparator();
    }

    public String PrintQuestionNumber() {
        return "Question number " + this.questionNumber + " of " + this.maxQuestions + ":";
    }

    public boolean End() {
        return this.questionNumber == this.maxQuestions;
    }

    public String printVictory() {
        String output = "";
        output += this.points + " of " + this.maxQuestions + " Points!";
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

}

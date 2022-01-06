package Quiz;


import java.util.ArrayList;
import java.util.List;

public class Game {

    private Questionnaire questionnaire;
    private final int maxQuestions;
    private int questionNumber;
    private int points;
    private Player player;
    private Question question;
    private Joker hint = new Joker("Hint");
    private Joker fifty = new Joker("50/50");
    private Joker skip = new Joker("Skip question");


    public Game(Questionnaire questionnaire, int maxQuestions, String playerName) {
        this.questionnaire = questionnaire;
        this.maxQuestions = maxQuestions;
        this.questionNumber = 1;
        this.points = 0;
        this.player = new Player(playerName, 0, "");
        Joker hint = this.hint;
        Joker fifty = this.fifty;
        Joker skip = this.skip;
    }

    public Question getQuestion() {
        int difficulty = (int) Math.ceil((float)this.questionNumber/(float)this.maxQuestions*3);
        this.question = this.questionnaire.randomQuestion(difficulty);
        return this.questionnaire.randomQuestion(difficulty);
    }

    public void printQuestion() {
        String output = this.question.question + System.lineSeparator();
        for(int i = 0; i < this.question.answers.length; i++) {
            output += "(" + (i+1) + ") " + this.question.answers[i] + System.lineSeparator();
        }
        output += "Jokers: (5) " + this.fifty.getJokerName() + " (6) " + this.hint.getJokerName() + " (7) " + this.skip.getJokerName() + System.lineSeparator();
        output += "(8) give up and quit the game";
        output.length();
        System.out.println(output);
    }

    public boolean checkAnswer(int answer) {
        return this.question.rightAnswer == answer;
    }

    public String printRightAnswer() {
        return this.question.answers[this.question.rightAnswer - 1];
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

    public void printStatus() {
        System.out.println("Current points: " + this.points + System.lineSeparator());
    }

    public void printQuestionNumber() {
        System.out.println("Question number " + this.questionNumber + " of " + this.maxQuestions + ":");
    }

    public boolean End() {
        return this.questionNumber == this.maxQuestions;
    }

    public void printVictory() {
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
        System.out.println(output);
    }

    public int getPoints() {
        return this.points;
    }

    public String getPlayerName() {
        return this.player.getPlayerName();
    }

    public void useFiftyFifty() {
        if (this.fifty.isAvailable()) {
            System.out.println("You selected the 50/50-joker.");
            ArrayList<Integer> a = new ArrayList<Integer>(List.of(0,1,2,3));
            a.remove(this.question.rightAnswer-1);  //remove index of right answer --> indices of 3 wrong answers remain
            a.remove((int) (Math.random() * 3));        //remove random index of 1 of 3 remaining wrong answer --> indices of 2 wrong answers remain
            for (int i: a) {
                this.question.answers[i] = "";      // set values of 2 remaining wrong answers to ""
                }
            this.printQuestion();
            this.fifty.setAvailable(false);
        } else {
            System.out.println("You already used the 50/50-joker.");
        }

    }

    public void useHint() {
        if (this.hint.isAvailable()) {
            System.out.println("You selected the hint joker.");
            System.out.println("Hint: " + this.question.hint);
            System.out.println("Hint joker not fully implemented.");
            this.hint.setAvailable(false);
        } else {
            System.out.println("You already used the hint joker.");
        }
    }

    public void useSkip() {
        if (this.skip.isAvailable()) {
            System.out.println("You selected the skip joker.");
            this.skip.setAvailable(false);
        } else {
            System.out.println("You already used the skip joker.");
        }
    }

}

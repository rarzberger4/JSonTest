//manages the game the user plays

package Quiz;

public class Game {

    //Class variables
    private final Questionnaire questionnaire;
    private final int maxQuestions;
    private int questionNumber;
    private int points;
    private final Player player;
    private Question question;

    //Constructor
    public Game(String playerName, int maxQuestions) {
        this.questionnaire = new Questionnaire();
        this.player = new Player(playerName, 0, "");
        this.maxQuestions = maxQuestions;
        this.questionNumber = 0;
        this.points = 0;
    }

    //Methods
    public int getPoints() {
        return this.points;
    }

    public String getPlayerName() {
        return this.player.getPlayerName();
    }

    public String getQuestionAsString() {
        return this.question.getQuestion();
    }

    public String getAnswer(int i) { return this.question.getAnswers()[i]; }

    public String getRightAnswerString() { return this.question.getAnswers()[this.question.getRightAnswer()]; }

    public int getRightAnswerInt() { return this.question.getRightAnswer(); }

    public String getHint() { return this.question.getHint(); }

    public int getQuestionNumber() {
        return this.questionNumber;
    }

    public int getMaxQuestions() { return this.maxQuestions; }

    public int getDifficulty() { return (int) Math.ceil((float)this.questionNumber/(float)this.maxQuestions*3); }

    //Returns question with difficulty according to game progress
    public void drawNewQuestion() {
        this.question = this.questionnaire.randomQuestion(this.getDifficulty());
    }

    public void addQuestionNumber() {
        this.questionNumber++;
    }

    public void addPoints() {
        this.points += this.questionNumber * this.getDifficulty();
    }

    public void deductPoints() {
        if (this.points > this.questionNumber * this.getDifficulty() / 3) {
            this.points -= this.questionNumber * this.getDifficulty() / 3;
        } else {
            this.points = 0;
        }
    }

    //prints Victory message, different depending on points
    public String getVictory() {
        int maxPoints = 0;
        if (this.maxQuestions == 9) {
            maxPoints = 108;
        } else if (this.maxQuestions == 12) {
            maxPoints = 188;
        } else if (this.maxQuestions == 15) {
            maxPoints = 290;
        }
        String output = this.points + " of " + maxPoints + " Points!";
        if (this.points < maxPoints *0.5) {
            output = "You need some practice. You only have " + output;
        } else if (this.points < maxPoints *0.8) {
            output = "Good job! You have " + output;
        } else if (this.points < maxPoints) {
            output = "Excellent! You have " + output;
        } else {
            output =  "Full score! Incredible! You have " + output;
        }
        return output;
    }

}

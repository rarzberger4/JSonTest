//manages individual question that are asked during the game

package Quiz;

public class Question {

    //CLass variables
    private final String question;
    private final String[] answers = new String[4];
    private final int rightAnswer;
    private final int difficulty;
    private final String hint;

    //Constructors
    public Question (Questiont q){
        this.question = q.getQuestion();
        this.answers[0] = q.getAnswer0();
        this.answers[1] = q.getAnswer1();
        this.answers[2] = q.getAnswer2();
        this.answers[3] = q.getAnswer3();
        this.rightAnswer = q.getAnswer();
        this.difficulty = q.getDifficulty();
        this.hint = q.getHint();
    }

    //Methods
    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public String getHint() {
        return hint;
    }
}

package Quiz;

public class Question {

    private final String question;
    private final String[] answers = new String[4];
    private final int rightAnswer;
    private final int difficulty;
    private final String hint;

    public Question (Questiont q){
        this.question = q.getQuestion();
        this.answers[0] = q.getAnswer1();
        this.answers[1] = q.getAnswer2();
        this.answers[2] = q.getAnswer3();
        this.answers[3] = q.getAnswer4();
        this.rightAnswer = q.getAnswer();
        this.difficulty = q.getDifficulty();
        this.hint = q.getHint();
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public String getHint() {
        return hint;
    }
}

package Quiz;

public class Question {
    private final String question;
    private final String[] answers;
    private final int rightAnswer;
    private final int difficulty;

    public Question(String question, String[] answers, int rightAnswer, int difficulty) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.difficulty = difficulty;
    }

    public String PrintQuestion() {
        String output = "";
        output += this.question + System.lineSeparator();
        for(int i = 0; i < this.answers.length; i++) {
            output += "(" + (i+1) + ")" + this.answers[i] + System.lineSeparator();
        }
        return output;
    }

    public boolean CheckAnswer(int answer) {
        return this.rightAnswer == answer;
    }

    public String PrintRightAnswer() {
        return this.answers[this.rightAnswer - 1];
    }

    public int getDifficulty() {
        return this.difficulty;
    }
}

package Quiz;

public class Question {
    public String question;
    public String[] answers = new String[4];
    public int rightAnswer;
    public int difficulty;

    public Question(String question, String[] answers, int rightAnswer, int difficulty) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.difficulty = difficulty;
    }

    public Question (Questiont q){
        this.question = q.question;
        this.answers[0] = q.answer1;
        this.answers[1] = q.answer2;
        this.answers[2] = q.answer3;
        this.answers[3] = q.answer4;
        this.rightAnswer = q.answer;
        this.difficulty = q.difficulty;
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

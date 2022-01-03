package Quiz;

public class Question {
    public String question;
    public String[] answers = new String[4];
    public int rightAnswer;
    public int difficulty;

    public Question (Questiont q){
        this.question = q.question;
        this.answers[0] = q.answer1;
        this.answers[1] = q.answer2;
        this.answers[2] = q.answer3;
        this.answers[3] = q.answer4;
        this.rightAnswer = q.answer;
        this.difficulty = q.difficulty;
    }

    public String printQuestion() {
        String output = "";
        output += this.question + System.lineSeparator();
        for(int i = 0; i < this.answers.length; i++) {
            output += "(" + (i+1) + ") " + this.answers[i] + System.lineSeparator();
        }
        output += "\n(5) give up and quit the game\n";
        return output;
    }

    public boolean checkAnswer(int answer) {
        return this.rightAnswer == answer;
    }

    public String printRightAnswer() {
        return this.answers[this.rightAnswer - 1];
    }

    public int getDifficulty() {
        return this.difficulty;
    }
}

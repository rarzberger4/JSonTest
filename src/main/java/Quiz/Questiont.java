package Quiz;

public class Questiont {

    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int answer;
    int difficulty;
    String hint;

    public Questiont(String question, String answer1, String answer2, String answer3, String answer4, int difficulty, int answer, String hint){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
        this.difficulty = difficulty;
        this.hint = hint;
    }

}

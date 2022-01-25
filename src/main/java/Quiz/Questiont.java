//Helper class to read from the json and added security (User does not know how Question really looks like

package Quiz;

public class Questiont {

    private final String question;
    private final String answer0;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final int answer;
    private final int difficulty;
    private final String hint;

    public Questiont(String question, String answer0, String answer1, String answer2, String answer3, int difficulty, int answer, String hint){
        this.question = question;
        this.answer0 = answer0;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer = answer;
        this.difficulty = difficulty;
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer0() {
        return answer0;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getAnswer() {
        return answer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getHint() {
        return hint;
    }
}

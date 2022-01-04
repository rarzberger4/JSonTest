package Quiz;

public class Joker {

    private String fifty;
    private String hint;
    private String replace;

    public Joker() {
        this.fifty = "(6) 50/50 ";
        this.hint = "(7) Hint ";
        this.replace = "(8) Skip Question ";
    }

    public String getJokers() {
        if (fifty.equals("") && hint.equals("") && replace.equals("")) {
            return "All Jokers used!";
        }
        return "Available Jokers: " + fifty + hint + replace;
    }

    public void useFifty() {
        fifty = "";
    }

    public void useHint() {
        hint = "";
    }

    public void useReplace() {
        replace = "";
    }

    public String getFifty() {
        return fifty;
    }

    public String getHint() {
        return hint;
    }

    public String getReplace() {
        return replace;
    }
}

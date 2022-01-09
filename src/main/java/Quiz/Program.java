package Quiz;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        Questionnaire q = new Questionnaire();
        Highscore h = new Highscore();
        Sounds s = new Sounds();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!" + System.lineSeparator() + "(1) Show Highscore" + System.lineSeparator() + "(2) Play game");
        while (!scanner.hasNext("[12]")) {
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        if (Integer.parseInt(scanner.nextLine().trim()) == 1) {
            h.printHighscore();
            System.out.println("(1) Play new game? (2) Quit game?");
            while (!scanner.hasNext("[12]")) {
                System.out.println("Choose a valid answer");
                scanner.nextLine();
            }
            if (Integer.parseInt(scanner.nextLine().trim()) == 2) {
                System.out.println("You quit the game.");
                System.exit(0);
            }
        }
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("How long do you want to play?\n(1) 9 questions\n(2) 12 questions\n(3) 15 questions");
        while (!scanner.hasNext("[123]")) {
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        int rounds = Integer.parseInt(scanner.nextLine().trim());
        Game myGame = new Game(q, (rounds + 2) * 3, name);
        while (!myGame.End()) {
            myGame.addQuestionNumber();
            Question question = myGame.getQuestion();
            myGame.printQuestionNumber();
            myGame.printQuestion();
            int selectedAnswer = checkInput(h, myGame, scanner);
            if (selectedAnswer < 5) {
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 5) {
                myGame.useFiftyFifty();
                selectedAnswer = checkInput(h, myGame, scanner);
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 6) {
                myGame.useHint();
                selectedAnswer = checkInput(h, myGame, scanner);
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 7){
                myGame.useSkip();
                selectedAnswer = checkInput(h, myGame, scanner);
                checkAnswer(s, selectedAnswer, myGame);
            }
        }
        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
        myGame.printVictory();
    }

    public static int checkInput(Highscore h, Game myGame, Scanner scanner) throws IOException {
        while (!scanner.hasNext("[12345678]")) {
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
        if (selectedAnswer == 8) {
            System.out.println("Are you sure you want to give up? Your final score would be " + myGame.getPoints() + ". \n(1) Yes\n(2) No");
            while (!scanner.hasNext("[12]")) {
                System.out.println("Choose a valid answer");
                scanner.nextLine();
            }
            int answer = Integer.parseInt(scanner.nextLine().trim());
            if (answer == 1) {
                h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                System.out.println("You quit the game.");
                System.exit(0);
            } else {
                while (!scanner.hasNext("[1234567]")) {
                    System.out.println("Choose a valid answer");
                    scanner.nextLine();
                }
                selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
            }
        }
        return selectedAnswer;
    }

    public static void checkAnswer(Sounds s, int selectedAnswer, Game myGame) {
        if (myGame.checkAnswer(selectedAnswer)) {
            System.out.println("Correct!");
            myGame.addPoints();
            s.playPosSound();
        } else {
            System.out.print("Wrong... Correct answer: ");
            myGame.printRightAnswer();
            myGame.deductPoints();
            s.playNegSound();
        }
        myGame.printStatus();
    }

}
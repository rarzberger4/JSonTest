//Main is here

package Quiz;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        Highscore h = new Highscore();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!" + System.lineSeparator() + "(1) Show Highscore" + System.lineSeparator() + "(2) Play game");
        while (!scanner.hasNext("[12]")) {
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        /*Integer.parseInt(scanner.nextLine().trim()) needed because it also consumes the new line character, nextInt() does not --> nextLine() reads new line character instead of expected new Line
        https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint
         */
        //Shows High score on CLI
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
        //Game setup
        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();
        System.out.println("How long do you want to play?\n(1) 9 questions\n(2) 12 questions\n(3) 15 questions");
        while (!scanner.hasNext("[123]")) {
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        int rounds = Integer.parseInt(scanner.nextLine().trim());
        Game myGame = new Game(playerName,(rounds + 2) * 3);
        Sounds s = new Sounds();

        //Game starts here
        int joker1 = 0;
        int joker2 = 0;
        int joker3 = 0;
        while (!myGame.End()) {
            myGame.addQuestionNumber();
            myGame.getQuestion();
            myGame.printQuestionNumber();
            myGame.printQuestion();
            int selectedAnswer = checkInput(h, myGame, scanner, 0);

            while (joker1 == 1 && selectedAnswer == 5) {
                myGame.useFiftyFifty();
                selectedAnswer = checkInput(h, myGame, scanner, 0);
            }
            while (joker2 == 1 && selectedAnswer == 6) {
                myGame.useHint();
                selectedAnswer = checkInput(h, myGame, scanner, 0);
            }
            while (joker3 == 1 && selectedAnswer == 7) {
                myGame.useSkip();
                selectedAnswer = checkInput(h, myGame, scanner, 0);
            }

            if (selectedAnswer < 5) {
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 5) {
                joker1 = 1;
                myGame.useFiftyFifty();
                selectedAnswer = checkInput(h, myGame, scanner, 1);
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 6) {
                joker2 = 1;
                myGame.useHint();
                selectedAnswer = checkInput(h, myGame, scanner, 1);
                checkAnswer(s, selectedAnswer, myGame);
            } else if (selectedAnswer == 7){
                joker3 = 1;
                myGame.useSkip();
                selectedAnswer = checkInput(h, myGame, scanner, 1);
                checkAnswer(s, selectedAnswer, myGame);
            }
        }
        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
        myGame.printVictory();
    }

    //Valid input: 8 -> quit, 1-4 -> chooses answer, 5-7 -> use Joker
    public static int checkInput(Highscore h, Game myGame, Scanner scanner, int jokerUsed) throws IOException {

        if (jokerUsed == 1) {
            while (!scanner.hasNext("[12348]")) {
                System.out.println("Choose a valid answer. You already used a joker on this question.");
                scanner.nextLine();
            }
        }
        else {
            while (!scanner.hasNext("[12345678]")) {
                System.out.println("Choose a valid answer.");
                scanner.nextLine();
            }
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

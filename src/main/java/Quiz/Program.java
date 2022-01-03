package Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        Questionnaire q = new Questionnaire();
        Highscore h = new Highscore();
        Sounds s = new Sounds();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!" + System.lineSeparator() + "(1) Show Highscore" + System.lineSeparator() + "(2) Play game");
        /*
        Integer.parseInt(scanner.nextLine().trim()) needed because it also consumes the new line character, nextInt() does not --> nextLine() reads new line character instead of expected new Line
        https://stackoverflow.com/questions/26586489/integer-parseintscanner-nextline-vs-scanner-nextint
         */
        if (Integer.parseInt(scanner.nextLine().trim()) == 1) {
            h.printHighscore();
        } else {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("How long do you want to play?");
            int rounds = Integer.parseInt(scanner.nextLine().trim());
            Game myGame = new Game(q, rounds, name);
            while(!myGame.End()) {
                myGame.addQuestionNumber();
                Question question = myGame.getQuestion();
                System.out.println(myGame.printQuestionNumber());
                System.out.print(question.printQuestion());
                int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                if(selectedAnswer == 5){
                    System.out.println("Are you sure you want to give up? Your current score will be saved to highscore. \n(1) Yes\n(2) No");
                    int answer = Integer.parseInt(scanner.nextLine().trim());
                    if(answer == 1){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game. Your score of " + myGame.getPoints() + " points was saved to highscore.");
                        return;
                    }
                } else if (question.checkAnswer(selectedAnswer)) {
                    System.out.println("Correct!");
                    myGame.addPoints();
                    s.playPosSound();
                } else {
                    System.out.print("Wrong... Correct answer: ");
                    System.out.println(question.printRightAnswer());
                    s.playNegSound();
                }
                System.out.println(myGame.printStatus());
            }
            h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
            System.out.println(myGame.printVictory());
        }

    }


}

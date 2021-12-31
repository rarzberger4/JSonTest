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
        if (Integer.parseInt(scanner.nextLine().trim()) == 1) {
            h.printHighscore();
        } else {
            System.out.println("Enter your name:");
            Game myGame = new Game(new Questionnaire[] {q}, 9, scanner.nextLine());
            while(!myGame.End()) {
                myGame.addQuestionNumber();
                Question question = myGame.getQuestion();
                System.out.println(myGame.printQuestionNumber());
                System.out.print(question.printQuestion());
                int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                if(question.checkAnswer(selectedAnswer)) {
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

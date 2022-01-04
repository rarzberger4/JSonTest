package Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        Questionnaire q = new Questionnaire();
        Highscore h = new Highscore();
        Sounds s = new Sounds();
        Joker jokers = new Joker();
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
            Game myGame = new Game(q, rounds, name, jokers);
            while(!myGame.End()) {
                myGame.addQuestionNumber();
                Question question = myGame.getQuestion();
                System.out.println(jokers.getJokers() + System.lineSeparator());
                System.out.println(myGame.printQuestionNumber());
                System.out.print(question.printQuestion());
                int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                if(selectedAnswer == 5){
                    h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                    System.out.println("You quit the game. Your score of " + myGame.getPoints() + " points was saved to highscore.");
                    return;
                } else if (question.checkAnswer(selectedAnswer)) {
                    System.out.println("Correct!");
                    myGame.addPoints();
                    s.playPosSound();
                } else if ((selectedAnswer == 6) && (!jokers.getFifty().equals(""))) {
                    jokers.useFifty();
                    question.useFifty();
                    System.out.println("Joker 50/50 used!");
                    System.out.println(question.printQuestion());
                    selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                    if(selectedAnswer == 5){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game. Your score of " + myGame.getPoints() + " points was saved to highscore.");
                        return;
                    } else if (question.checkAnswer(selectedAnswer)) {
                        System.out.println("Correct!");
                        myGame.addPoints();
                        s.playPosSound();
                    } else {
                        System.out.print("Wrong... Correct answer: ");
                        System.out.println(question.printRightAnswer());
                        s.playNegSound();
                    }
                } else if ((selectedAnswer == 7) && (!jokers.getHint().equals(""))) {
                    jokers.useHint();
                    System.out.println("Joker Hint used!");
                    System.out.println("HINT: " + question.getHint());
                    System.out.println(question.printQuestion());
                    selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                    if(selectedAnswer == 5){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game. Your score of " + myGame.getPoints() + " points was saved to highscore.");
                        return;
                    } else if (question.checkAnswer(selectedAnswer)) {
                        System.out.println("Correct!");
                        myGame.addPoints();
                        s.playPosSound();
                    } else {
                        System.out.print("Wrong... Correct answer: ");
                        System.out.println(question.printRightAnswer());
                        s.playNegSound();
                    }
                } else if ((selectedAnswer == 8) && (!jokers.getReplace().equals(""))) {
                    jokers.useReplace();
                    System.out.println("Joker Skip Question used! Skipping question..." + System.lineSeparator());
                    question = myGame.getQuestion();
                    System.out.println(myGame.printQuestionNumber());
                    System.out.print(question.printQuestion());
                    selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                    if(selectedAnswer == 5){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game. Your score of " + myGame.getPoints() + " points was saved to highscore.");
                        return;
                    } else if (question.checkAnswer(selectedAnswer)) {
                        System.out.println("Correct!");
                        myGame.addPoints();
                        s.playPosSound();
                    } else {
                        System.out.print("Wrong... Correct answer: ");
                        System.out.println(question.printRightAnswer());
                        s.playNegSound();
                    }

                }
                else {
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

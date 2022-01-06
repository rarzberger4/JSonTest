package Quiz;

import java.io.IOException;
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
        while (!scanner.hasNext("[12]")) {     // if input != 1 or 2 >> error message
            System.out.println("Choose a valid answer");
            scanner.nextLine();
        }
        if (Integer.parseInt(scanner.nextLine().trim()) == 1) {
            h.printHighscore();
        } else {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("How long do you want to play?\n(1) 9 questions\n(2) 12 questions\n(3) 15 questions");
            while (!scanner.hasNext("[123]")) {
                System.out.println("Choose a valid answer");
                scanner.nextLine();
            }
            int rounds = Integer.parseInt(scanner.nextLine().trim());
            Game myGame = new Game(q, (rounds+2)*3, name, jokers);
            while(!myGame.End()) {
                myGame.addQuestionNumber();
                Question question = myGame.getQuestion();
                System.out.println(myGame.printQuestionNumber());
                System.out.print(question.printQuestion());
                System.out.println(jokers.getJokers() + System.lineSeparator());
                while (!scanner.hasNext("[12345678]")) {
                    System.out.println("Choose a valid answer");
                    scanner.nextLine();
                }
                int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                if(selectedAnswer == 5){
                    System.out.println("Are you sure you want to give up? Your final score would be " + myGame.getPoints() + ". \n(1) Yes\n(2) No");
                    while (!scanner.hasNext("[12]")) {
                        System.out.println("Choose a valid answer");
                        scanner.nextLine();
                    }
                    int answer = Integer.parseInt(scanner.nextLine().trim());
                    if(answer == 1){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game.");
                        return;
                    }
                } else if (question.checkAnswer(selectedAnswer)) {
                    System.out.println("Correct!");
                    myGame.addPoints();
                    s.playPosSound();
                } else if ((selectedAnswer == 6) && (!jokers.getFifty().equals(""))) {
                    jokers.useFifty();
                    question.useFifty();
                    System.out.println("50/50 Joker used!");
                    System.out.println(question.printQuestion());
                    selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
                    if(selectedAnswer == 5){
                        h.updateHighscore(myGame.getPlayerName(), myGame.getPoints());
                        System.out.println("You quit the game.");
                        return;
                    } else if (question.checkAnswer(selectedAnswer)) {
                        System.out.println("Correct!");
                        myGame.addPoints();
                        s.playPosSound();
                    } else {
                        System.out.print("Wrong... Correct answer: ");
                        System.out.println(question.printRightAnswer());
                        s.playNegSound();
                        myGame.deductPoints();
                    }
                } else if ((selectedAnswer == 7) && (!jokers.getHint().equals(""))) {
                    jokers.useHint();
                    System.out.println("Hint Joker used!");
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

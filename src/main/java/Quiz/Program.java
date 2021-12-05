package Quiz;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Questionnaire k = new Questionnaire();
        Game myGame = new Game(new Questionnaire[] {k}, 9);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        while(!myGame.End()) {
            myGame.AddQuestionNumber();
            Question question = myGame.GetQuestion();
            System.out.println(myGame.PrintQuestionNumber());
            System.out.print(question.PrintQuestion());
            int selectedAnswer = Integer.parseInt(scanner.nextLine().trim());
            if(question.CheckAnswer(selectedAnswer)) {
                System.out.println("Correct!");
                myGame.AddPoints();
            } else {
                System.out.print("Wrong... Correct answer: ");
                System.out.println(question.PrintRightAnswer());
            }
            System.out.println(myGame.PrintStatus());
        }
        System.out.println(myGame.printVictory());
    }


}

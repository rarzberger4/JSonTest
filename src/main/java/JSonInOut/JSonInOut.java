package JSonInOut;


import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

class Question {
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int answer;

    public Question(String question, String answer1, String answer2, String answer3, String answer4, int answer) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer = answer;
    }

    public Question() {

    }
}


public class JSonInOut {


    public static void writetest() {
        Question question = new Question("How long is the river danube?", "2850 km", "5550 km", "1380 km", "10700 km", 1);

        Gson gson = new Gson();

        String testjson = gson.toJson(question);

        System.out.println(testjson);


        try {
            Writer writer = new FileWriter("Output.json");
            gson.toJson(question, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readtest() {
        Gson gson = new Gson();
        Question question = new Question();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("Output.json"));
            question = gson.fromJson(reader, Question.class);
            // reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(question.question);
        System.out.println(question.answer1);
        System.out.println(question.answer2);
        System.out.println(question.answer3);
        System.out.println(question.answer4);
        System.out.println(question.answer);


    }

    public static void writearraytest() {
        Question[] question = new Question[10];


        for (int i = 0; i < 10; i++) {
            question[i] = new Question("How long is the river danube?", "2850 km", "5550 km", "1380 km", "10700 km", 1);
        }

        Gson gson = new Gson();

        String testjson = gson.toJson(question);

        System.out.println(testjson);


        try {
            Writer writer = new FileWriter("Output.json");
            gson.toJson(question, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readarraytest() {
        Gson gson = new Gson();
        Question[] question = new Question[10];

        try {
            Reader reader = Files.newBufferedReader(Paths.get("Output.json"));
            question = gson.fromJson(reader, Question[].class);
            // reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < question.length; i++){
            System.out.println(question[i].question);
            System.out.println(question[i].answer1);
            System.out.println(question[i].answer2);
            System.out.println(question[i].answer3);
            System.out.println(question[i].answer4);
            System.out.println(question[i].answer);
        }

    }

    public static void main(String[] args) {
        //writetest();
        //readtest();

        //writearraytest();
        readarraytest();

    }
}

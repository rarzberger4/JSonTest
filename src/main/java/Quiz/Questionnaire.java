package Quiz;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Questionnaire {

    private Questiont[] questt;
    private Question[] questionnaire;

    public Questionnaire() {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("Questionnaire.json"));
            questt = gson.fromJson(reader, (Type) Questiont[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        questionnaire = new Question[questt.length];

        for(int i = 0; i < questt.length; i++){
            questionnaire[i] = new Question(questt[i]);     //translate json saved questions
        }
    }

    public Question randomQuestion(int difficulty) {
        List<Question> filtered = Arrays.stream(questionnaire).filter(f -> f.getDifficulty() == difficulty).collect(Collectors.toList());
        return filtered.get((int)(Math.random() * filtered.size()));
    }

}

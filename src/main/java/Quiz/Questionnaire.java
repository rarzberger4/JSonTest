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

    private Question[] questionnaire = new Question[0];

    public Questionnaire() {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("Output.json"));
            questionnaire = gson.fromJson(reader, (Type) Question[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Question RandomQuestion(int difficulty) {
        List<Question> filtered = Arrays.stream(questionnaire).filter(f -> f.getDifficulty() == difficulty).collect(Collectors.toList());
        return filtered.get((int)(Math.random() * filtered.size()));
    }

}

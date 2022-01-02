package Quiz;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Questionnaire {

    private Questiont[] questt;
    private ArrayList<Question> questionnaire = new ArrayList<>();

    public Questionnaire() {
        Gson gson = new Gson();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("Questionnaire.json"));
            questt = gson.fromJson(reader, (Type) Questiont[].class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Questiont gt : questt){
            questionnaire.add(new Question(gt));
        }

    }

    public Question randomQuestion(int difficulty) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questionnaire){
            if(q.getDifficulty()==difficulty){
                filtered.add(q);
            }
        }
        double help = Math.random()*filtered.size();
        int index = (int) help;
        questionnaire.remove(filtered.get(index));
        return filtered.get(index);
    }

}

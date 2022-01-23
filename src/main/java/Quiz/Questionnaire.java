//manages totality of questions asked at the game

package Quiz;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Questionnaire {

    //Class variables
    private Questiont[] questt;
    private final ArrayList<Question> questionnaire = new ArrayList<>();

    //Constructors
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

    //Methods
    public Question randomQuestion(int difficulty) {
        List<Question> filtered = new ArrayList<>();
        for (Question q : questionnaire){
            if(q.getDifficulty()==difficulty){
                filtered.add(q);
            }
        }
        int index = (int) (Math.random() * filtered.size());
        questionnaire.remove(filtered.get(index)); //fixes bug that question are asked more than once
        return filtered.get(index);
    }

}

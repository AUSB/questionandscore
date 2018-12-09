package 0000.eejf1234.caps;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {
    private CountryDB db;
    private String question;
    private String answer;
    private List<String> capitals;
    Map<String, Country> data;

    public Game() {
        this.db = new CountryDB();
        question = "";
        answer = "";
        capitals = db.getCapitals();
        data = db.getData();


    }

    public String qa() {
        int n = capitals.size();
        int index = (int) (n * Math.random());
        String capital = capitals.get(index);
        Country country = data.get(capital);

        if (Math.random() < 0.5) {
            question = "What is the capital of " + country.getName();
            answer = country.getCapital();
        } else {
            question = capital + " is the capital of?" ;
            answer = country.getName();
        }
        System.out.println("=============" + answer);
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

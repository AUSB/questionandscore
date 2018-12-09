package lab5.eecs1022.caps;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CapsActivity extends AppCompatActivity {
    private Game game;
    private String question;
    private String answer;
    private int score;
    private int qNum;
    private String[] log = new String[11];
    private ToneGenerator tg;// add an attribute tg in activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);

        score = 0;
        qNum = 1;
        game = new Game();
        ask();
        this.tg = new ToneGenerator(AudioManager.STREAM_ALARM,100);// initialize tg in onCreate
    }


    private void ask() {
        question = game.qa();
        ((TextView) findViewById(R.id.question)).setText(question);
        ((TextView) findViewById(R.id.qNumber)).setText(String.format("Q# %d", qNum));
        ((TextView) findViewById(R.id.score)).setText(String.format("SCORE %d", score));

        qNum++;
    }



    @SuppressLint("WrongViewCast")
    public void onDone(View v) {
        EditText answerView = findViewById(R.id.answer);
        answer = answerView.getText().toString().trim().toUpperCase();

        if (answer.equals(game.getAnswer().toUpperCase())) {// strings comparing
            score += 1;
            tg.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,400);

        }
        else{
            tg.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,200);
            // invoke this method when answer is incorrect
        }
        ((TextView) findViewById(R.id.score)).setText(String.format("SCORE %d", score));// integer to string

        String logHistory = "Q#" + (qNum - 1) + ": " + question + "\n"
                + "Your answer: " + answer + "\n"
                + "Correct answer: " + game.getAnswer()
                + "\n\n";

        log[qNum] = logHistory;

        String allLog = "";
        for (int i = 10; i >= 1; i--) {
            if (log[i] != null) {
                allLog += log[i];
            }
        }

        ((TextView) findViewById(R.id.log)).setText(allLog);
        if (qNum < 10) {
            ask();
            ((TextView) findViewById(R.id.answer)).setText("");

        } else {
            ((TextView) findViewById(R.id.qNumber)).setText("Game Over!");
            ((TextView) findViewById(R.id.answer)).setText("");
            Button done = findViewById(R.id.done);
            done.setEnabled(false);
        }


//        for (qNum = 1; qNum < 10; qNum++) {
//            for (score = 0; score <= 10; score++) {
//                if(answer == country.getCapital()) {
//                    logHistory = logHistory + question + "\n"
//                               + "Your answer:" + answer + "\n"
//                               + "Correct answer:" + country.getCapital()
//                               + "\n\n" ;
//                    ((TextView) findViewById(R.id.log)).setText(logHistory);
//                }
//                else if(answer == country.getName()){
//
//                    logHistory = logHistory + question + "\n"
//                            + "Your answer:" + answer + "\n"
//                            + "Correct answer:" + country.getName()
//                            + "\n\n" ;
//                    ((TextView) findViewById(R.id.log)).setText(logHistory);
//                }
//            }
//
//            finish();
//            ((EditText) findViewById(R.id.qNumber)).setText("Game Over!");
//            Button done = findViewById(R.id.done);
//            done.setEnabled(false);
//        }
    }
}


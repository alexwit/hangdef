package com.example.lex.hangmanextreme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Gameplay gameplay;
    TextView outputWord;
    EditText inputChar;
    Button btncheckChar;
    Button btnRestart;
    TextView TVattempts;
    TextView TVwrongChars;
    TextView tvnumberGuessses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameplay = new Gameplay();

        btncheckChar = (Button)findViewById(R.id.btnCharcheck);
        btnRestart = (Button)findViewById(R.id.btnRestart);

        TVattempts = (TextView)findViewById(R.id.TVNumberAttempts);
        outputWord = (TextView)findViewById(R.id.outputWord);
        TVwrongChars = (TextView) findViewById(R.id.TVwrongChars);
        tvnumberGuessses = (TextView) findViewById(R.id.tvNumberGuesses);

        inputChar = (EditText) findViewById(R.id.etChar);


        TVattempts.setText(String.valueOf(gameplay.attemptsleft));

        gameplay.emptySpace();
        outputWord.setText(String.valueOf(gameplay.shownWord));


//        EditText inputChar = (EditText)findViewById(R.id.etChar);
//        final Character C = inputChar.getText().toString().charAt(0);
//        Log.i("Mainactivity", "wat is de char van de user: " + C);


        btncheckChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Character C = inputChar.getText().toString().charAt(0);
                Log.i("Mainactivity", "wat is de char van de user: " + C);

            if(gameplay.checkChar(C)) {

                for (int i = 0; i < gameplay.currWord.length(); i++) {
                    if (gameplay.updateChar(i, C)) {
                        gameplay.shownWord.setCharAt(i, C);
                        outputWord.setText(String.valueOf(gameplay.shownWord));
                    }
                }
                gameplay.updateGuesses();
                Log.i("Mainactivity", "Guesses + 1 " + gameplay.numberGuesses);
                tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));

                if (gameplay.checkWord()) {
                    Toast.makeText(MainActivity.this, "You've WON!!", Toast.LENGTH_LONG).show();
                }

            }
//                if (gameplay.checkChar(i, C)) {
//                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
//
//                    int i = gameplay.posLetterShownWord(C);
//                    if (gameplay.posLetterShownWord(C) > 10) {
//                        Log.i("mainactivity", "tegroot" + i);
//                    }
//
//
//
//
//
//                }
            else if (gameplay.updateWrongChar(C)) {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();

                    TVwrongChars.setText(gameplay.wrongChars.toString());
                    Log.i("Mainactivity", "Guesses 2.0 + 1" + gameplay.numberGuesses);

                    tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));

                    Log.i("Mainactivity", "attempts + -1" + gameplay.numberGuesses);

                    TVattempts.setText(String.valueOf(gameplay.attemptsleft));

                    if (gameplay.checkAttempts()) {
                        Toast.makeText(MainActivity.this, "You've DIED!!", Toast.LENGTH_SHORT).show();
                    }

            }
//                else if (gameplay.checkWord()) {
//                    Toast.makeText(MainActivity.this, "You've WON!!", Toast.LENGTH_LONG).show();
//                }

            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameplay.restart();
                gameplay.emptySpace();
                TVattempts.setText((String.valueOf(gameplay.attemptsleft)));
                tvnumberGuessses.setText(String.valueOf(gameplay.numberGuesses));
                TVwrongChars.setText(String.valueOf(gameplay.wrongChars));
                outputWord.setText(String.valueOf(gameplay.shownWord));

            }
        });


    }
}

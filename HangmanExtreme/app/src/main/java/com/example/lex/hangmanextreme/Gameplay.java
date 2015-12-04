package com.example.lex.hangmanextreme;

import java.util.Random;

/**
 * Created by Papi lexus on 3-12-2015.
 */
public class Gameplay {

    // initializes attempts
    public int attemptsleft = 6;

    // number of guesses
    public int numberGuesses = 0;

    // creates a beta word list
    StringBuilder shownWord;

    // array with wrong char's
    StringBuilder wrongChars;

    String[] wordList = {"paard", "hallo", "wereld", "quadriceps"};

    // picks a randomword from the list
    String currWord = wordList[new Random().nextInt(wordList.length)];

    public void emptySpace(){
        shownWord = new StringBuilder();
        wrongChars = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++){
            shownWord.append("_");

            //shownWord.append(" ");
        }
    }

    public boolean checkAttempts() {
        return (attemptsleft==0);
    }

    public boolean checkChar(Character C) {
        if(currWord.contains(C.toString())){
            return true;
        };
        return false;
    }

    public boolean updateChar(int i, Character C){
        if (currWord.charAt(i) == C){
                return true;
        }
        return false;
    }

//    public void updateUnderscore(Character C) {
//        for (int i = 0; i < currWord.length(); i++) {
//            Character wordLetter = currWord.charAt(i);
//            if (currWord.charAt(i) == C) {
//                Log.d("hoi", "");
//                shownWord.setCharAt(i, wordLetter);
//                outputWord.setText(String.valueOf(shownWord));
//            }
//        }
//    }

    public void restart() {
//        shownWord = new StringBuilder();
//        wrongChars = new StringBuilder();
        currWord = wordList[new Random().nextInt(wordList.length)];
        attemptsleft = 6;
        numberGuesses = 0;
    }

    public boolean checkWord() {
        return (shownWord.toString().equals(currWord));
    }

    public int posLetterShownWord(Character C) {
        for (int i = 0; i < currWord.length(); i++) {
            Character wordLetter = currWord.charAt(i);
            if (wordLetter == C) {
                return i;
            }
        }
        return 1000;
    }

    public int getNumberGuesses(){
        return numberGuesses;
    }

    public int getAttempts(){
        return attemptsleft;
    }

    public StringBuilder getWrongChars() {
        return wrongChars;
    }

    public boolean updateWrongChar(Character C){
        for (int i = 0; i < wrongChars.length(); i++){
            Character wrongLetter = wrongChars.charAt(i);
            if(C == wrongLetter){
                return false;
            }
        }
        wrongChars.append(C + " ");
        updateGuesses();
        updateAttempts();
        return true;
    }

    public void updateGuesses() {
        numberGuesses++;
    }

    public void updateAttempts() {
        attemptsleft--;
    }
}

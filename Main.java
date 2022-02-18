package com.justasvaivada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        boolean lock = true;
        String play = "P";
        String quit = "Q";

//        loop game until user wants to quit
        while (lock) {

//            Initiate new hangman Object
            HangmanTechnical.Hangman hangman = new HangmanTechnical.Hangman();
            HangmanTechnical hangmanTech = new HangmanTechnical();
//            Create new instance of object
            ArrayList<Character> wordC = hangman.wordCharList;
            ArrayList<Character> hidden = hangman.wordHidden;

            System.out.println("---Type 'P' to Play---");
            System.out.println("---Type 'Q' to Quit---");
            System.out.print("Choice: ");
//            Scan for user input and convert it to uppercase
            String userChoice = scan.nextLine().toUpperCase();

//            If user wants to play, then initiate game
            if (userChoice.equals(play)){
                System.out.println("WELCOME TO HANGMAN");
                System.out.println(wordC);
                System.out.println("Word Length: " + hangman.wordLength);
                ArrayList<Character> guess = hangmanTech.userAttempt(wordC, hidden);
                System.out.println("The Word Was: " + hangman.wordString);
            } else if (userChoice.equals(quit)) {
                lock = false;
            } else {
                System.out.println("Unknown Input Please Try Again");
            }

        }
    }
}

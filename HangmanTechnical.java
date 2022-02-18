package com.justasvaivada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HangmanTechnical {

//    Method that picks random word from file and generates a list of characters for that word
    private static ArrayList<Character> randomWordCharList() throws IOException {
        String filepath = "src/com/justasvaivada/words.txt";
//        Initiates instance of file reader that takes filepath as parameter
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = reader.readLine();
        ArrayList<String> word = new ArrayList<>();
        int i = 0;
//        Loops through file until .readline() returns null
        while (line != null) {
//            Adds all words in a file to an arraylist
            word.add(i, line.toUpperCase());
            i++;
            line = reader.readLine();
        }

        Random random = new Random();
//        generates a random number between 0 and the amount of items in array
        int randNumb = random.nextInt(0, word.size() + 1);
//        selects random word from the array based on randNumb as an index
        String selectedWord = word.get(randNumb);
        ArrayList<Character> wordChar = new ArrayList<>();

//        loops through the selectedWord and adds characters at specified index into new array
        for (int j = 0; j < selectedWord.length(); j++) {
            wordChar.add(j, selectedWord.charAt(j));
        }
        return wordChar;
    }


//    Method that returns the array of characters replaced with '_'
    private static ArrayList<Character> hangmanGuess(int length) {

        ArrayList<Character> hiddenList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            hiddenList.add(i, '_');
        }
        return hiddenList;
    }

    public static ArrayList<Character> userAttempt
            (ArrayList<Character> wordChar, ArrayList<Character> hidden) {
        Scanner scan = new Scanner(System.in);
//        System.out.println(wordChar);

        int limit = 6;
        while (true) {
            if (limit == 6) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=======");
            }
            else if (limit == 5) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println(" O    |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=======");
            }
            else if (limit == 4) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println(" O    |");
                System.out.println(" |    |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=======");
            }
            else if (limit == 3) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println(" O    |");
                System.out.println("/|    |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=======");
            }
            else if (limit == 2) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println(" O    |");
                System.out.println("/|\\   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=======");
            }
            else if (limit == 1) {
                System.out.println(" +----+");
                System.out.println(" |    |");
                System.out.println(" O    |");
                System.out.println("/|\\   |");
                System.out.println("/     |");
                System.out.println("      |");
                System.out.println("=======");
            }
//            Takes user input at index '0' as a char and converts to uppercase
            System.out.print("Enter your guess: ");
            Character userInputC = scan.next().charAt(0);
            Character userInput = Character.toUpperCase(userInputC);
//            Checks if word contains users guess
            if (wordChar.contains(userInput)) {
                for (int i = 0; i < wordChar.size(); i++) {
                    if (wordChar.get(i).equals(userInput)) {
//                        Replaces '_' with user input if the word contains that letter
                        hidden.set(i, userInput);
                    }
                }
            }
//            Converts array to string for readability while playing.
            String hiddenS = hidden.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "");
            System.out.println(hiddenS);

//            If the random word does not contain users input, decreases lives by 1
            if (!wordChar.contains(userInput)) {
                limit--;
                System.out.println("Letter not found: " + limit);
                if (limit == 0){
                    System.out.println("You Lose!");
                    System.out.println(" +----+");
                    System.out.println(" |    |");
                    System.out.println(" O    |");
                    System.out.println("/|\\   |");
                    System.out.println("/ \\   |");
                    System.out.println("      |");
                    System.out.println("=======");
                    break;
                }
            }
//            If users guessed all letters and array no longer contains '_' then user wins
            if (!hidden.contains('_')) {
                System.out.println("You Win!");
                break;
            }
        }
        return hidden;
    }


//    Allows access to methods.
    static class Hangman  {
        ArrayList<Character> wordCharList = randomWordCharList();
        Integer wordLength = wordCharList.size();
        String wordString = wordCharList
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        ArrayList<Character> wordHidden = hangmanGuess(wordLength);

        Hangman() throws IOException {}
    }
}

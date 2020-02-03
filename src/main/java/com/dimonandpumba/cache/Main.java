package com.dimonandpumba.cache;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SymbolCounter counter = new SymbolCounter();

        System.out.println("Enter any phrase or word to getting a result for exit from the program, enter the word \"exit\"");

        while (true) {
            String line = scanner.nextLine();
            if(line.equals("exit")) {
                break;
            }
            Map<Character, Integer> result = counter.countingSymbol(line);
            viewResult(line, result);
        }
        scanner.close();
    }

    public static void viewResult(String line, Map<Character, Integer> output) {
        System.out.println(line);
        for(Map.Entry<Character,Integer > item : output.entrySet()){
            System.out.printf("\"%s\" - %s \n", item.getKey(), item.getValue());
        }
    }
}

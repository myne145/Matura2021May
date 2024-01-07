package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Main {

    private static void zad4_1() throws IOException {
        ArrayList<String> content = Algorithms.readFile(new File("instrukcje.txt"));
        int length = 0;
        for(String s : content) {
            String command = s.split(" ")[0];
            switch (command) {
                case "DOPISZ":
                    length++;
                    break;
                case "USUN":
                    length--;
                    break;
            }
        }
        System.out.println(length);
    }

    private static void zad4_2() throws IOException {
        ArrayList<String> content = Algorithms.readFile(new File("instrukcje.txt"));
        String temp = "";
        int length = 0;
        int lengthMax = 0;
        int index = 0;
        for(int i = 0; i < content.size(); i++) {
            String command = content.get(i).split(" ")[0];
            if(command.equals(temp)) {
                length++;
            } else {
                if(length > lengthMax) {
                    lengthMax = length;
                    index = i-1;
                }
                length = 1;
            }
            temp = command;
        }
        System.out.println(content.get(index).split(" ")[0] + "\t" + lengthMax);
    }

    private static void zad4_3() throws IOException {
        ArrayList<String> content = Algorithms.readFile(new File("przyklad.txt"));
        LinkedHashMap<String, Integer> letters = new LinkedHashMap<>();
        for(String s : content) {
            if(!s.split(" ")[0].equals("DOPISZ"))
                continue;

            String letter = s.split(" ")[1];
            if(letters.get(letter) == null)
                letters.put(letter, 1);
            else
                letters.put(letter, letters.get(letter) + 1);
        }
        System.out.println(Collections.max(letters.values()));
    }

    public static void main(String[] args) throws IOException {
        zad4_3();
    }
}
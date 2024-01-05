package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static void zad4_1() throws IOException {
        ArrayList<String> content = Algorithms.readFile(new File("instrukcje.txt"));
        ArrayList<Character> result = new ArrayList<>();
        for(String s : content) {
            String command = s.split(" ")[0];
            char letter = s.split(" ")[1].charAt(0);
            switch (command) {
                case "DOPISZ":
                    result.add(letter);
                    break;
                case "ZMIEN":

                    break;
                case "USUN":
                    result.remove(result.size() - 1);
                    break;
                case "PRZESUN":

                    break;

            }
        }

    }
    public static void main(String[] args) throws IOException {
        zad4_1();
    }
}
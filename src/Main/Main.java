package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Main.Algorithms.*;

public class Main {

    private static void zad4Part1() throws IOException {
        ArrayList<String> content = loadFileArray(new File("instrukcje.txt"));
        int wordlength = 0;
        for(String s : content) {
            if(s.contains("DOPISZ"))
                wordlength++;
            if(s.contains("USUN"))
                wordlength--;
        }
        System.out.println(wordlength);
    }

    private static void zad4Part2() throws IOException {
        ArrayList<String> content = loadFileArray(new File("przyklad.txt"));
        ArrayList<Integer> counters = new ArrayList<>();
        int maxCount = 1;
        for(int i = 1; i < content.size(); i++) {
            if(content.get(i-1).split(" ")[0].equals(content.get(i).split(" ")[0])) {
                maxCount++;
                counters.add(maxCount);
            }
            else {
                maxCount = 1;
                counters.add(1);
            }
        }

        ArrayList<Integer> result = getHighestNumAndItsIndexInArr(counters);
        System.out.println("Liczba " + result.get(0) + " jest największa dla instrukcji: " + content.get(result.get(1)).split(" ")[0]);
    }
    public static void main(String[] args) throws IOException {
        zad4Part2();
    }
}
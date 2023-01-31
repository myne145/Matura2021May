package Main;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    private static void zad4Part3() throws IOException {
        ArrayList<String> content = loadFileArray(new File("instrukcje.txt"));
        LinkedHashMap<String, Integer> counters = new LinkedHashMap<String, Integer>();
        for (String value : content) {
            if(value.split(" ")[0].contains("DOPISZ")) {
                String s = value.split(" ")[1];
                counters.put(s, 0);
            }
        }
        for(String s : content)
            if(s.split(" ")[0].contains("DOPISZ"))
                counters.put(s.split(" ")[1], counters.get(s.split(" ")[1]) + 1);

        ArrayList<Integer> arr = new ArrayList<>();
        Set<String> countersKeys = counters.keySet();
        for(String s : countersKeys) {
            arr.add(counters.get(s));
        }
        ArrayList<Integer> highestNumAndIndex = getHighestNumAndItsIndexInArr(arr);
        ArrayList<String> setToArray = new ArrayList<>(countersKeys);
        System.out.println("Najczęściej dopisywana litera to " + setToArray.get(highestNumAndIndex.get(1)) + ", była ona dopisana " + highestNumAndIndex.get(0) + " razy.");
    }

    public static void main(String[] args) throws IOException {
        zad4Part3();
    }
}
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
    public static void main(String[] args) throws IOException {
        zad4Part1();
    }
}
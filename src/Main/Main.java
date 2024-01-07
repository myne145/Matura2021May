package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public static void main(String[] args) throws IOException {
        zad4_2();
    }
}
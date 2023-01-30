package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Algorithms {
    public static ArrayList<String> loadFileArray(File file) throws IOException {
        return (ArrayList<String>) Files.readAllLines(file.toPath());
    }

    public static String loadFileString(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> arr = (ArrayList<String>) Files.readAllLines(file.toPath());
        for(String s : arr)
            stringBuilder.append(s);
        return stringBuilder.toString();
    }

    public static ArrayList<Integer> getHighestNumAndItsIndexInArr(ArrayList<Integer> arr) {
        int temp = 0;
        int tempIndex = 0;
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > temp) {
                temp = arr.get(i);
                tempIndex = i;
            }
        }

        return new ArrayList<>(Arrays.asList(temp, tempIndex));
    }
}

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

    /**
     *
     * @param arr
     * @return arraylist: index 0 is the highest number, and index 1 is index of highest number
     */
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

    public static ArrayList<Integer> sumArraysWithTheSameLength(ArrayList<Integer> arr, ArrayList<Integer> arr2) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++)
            result.add(arr.get(i) + arr2.get(i));
        return result;
    }
}

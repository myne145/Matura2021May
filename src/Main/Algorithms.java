package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

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
}

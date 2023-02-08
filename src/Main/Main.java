package Main;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
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

    public static void zad4Part4() throws IOException {
        ArrayList<Character> array = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','r','s','t','u','w','x','y','z'));
        ArrayList<String> alphabet = new ArrayList<>();
        for(Character c : array)
            alphabet.add(c.toString().toUpperCase());

        ArrayList<String> content = loadFileArray(new File("instrukcje.txt"));
        System.out.println(content);
        ArrayList<String> word = new ArrayList<>();
        for(int i = 0; i < content.size(); i++) {
            String instuction = content.get(i).split(" ")[0];
            String value = content.get(i).split(" ")[1];
            switch (instuction) {
                case "DOPISZ":
                    word.add(value);
                    break;
                case "ZMIEN":
                    word.set(word.size()-1, value);
                    break;
                case "USUN":
                    word.remove(word.size()-1);
                    break;
                case "PRZESUN":
                    int indexInAlphabet = 0;
                    for(int x = 0; x < alphabet.size(); x++) {
                        if(alphabet.get(x).equals(value.toUpperCase())) {
                            indexInAlphabet = x;
                            break;
                        }
                    }
                    for(int j = 0; j < word.size(); j++){
                        if(word.get(j).contains(value)) {
                            if(indexInAlphabet != 23)
                                word.set(j, alphabet.get(indexInAlphabet+1));
                            else
                                word.set(j, "A");
                        }
                    }
                    break;
            }
        }
        System.out.println(word);
    }

    private static void zad5Part1() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        ArrayList<Double> averageUsage = new ArrayList<>();
        LinkedHashMap<ClientCode, Double> results = new LinkedHashMap<>();
        LinkedHashMap<Double, ClientCode> reversedResults = new LinkedHashMap<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));
        for(String s : content) {
            String[] contentSplit = s.split(";"); //index 0 = numer klienta, 1=pierwszy miesiac, 2=drugi, itd...
            int sum = 0;
            for(int i = 1; i < contentSplit.length; i++) {
                sum += Integer.parseInt(contentSplit[i]);
            }
            ClientCode code = new ClientCode(contentSplit[0]);
            double averageWater = sum / (double)code.getHowManyPeople();
            results.put(code, averageWater);
            reversedResults.put(averageWater, code);
            averageUsage.add(averageWater);
        }
        Collections.sort(averageUsage);
        DecimalFormat format = new DecimalFormat("###.##");
        for(int i = averageUsage.size()-1; i >= averageUsage.size()-11; i--)
            System.out.println(reversedResults.get(averageUsage.get(i)).getClientNumString() + "\t" + format.format(averageUsage.get(i)));
    }

    //Treść:
    //Dla każdej dzielnicy podaj całkowite roczne zużycie wody przez jej wszystkich mieszkańców.
    private static void zad5Part2() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));
        for(String s : content)
            results.put(new ClientCode(s.split(";")[0]).getCityDistrict(), 0);
        System.out.println(results);
        for(String s : content) {
            String[] splittedLine = s.split(";");
            ClientCode clientCode = new ClientCode(splittedLine[0]);
            int sum = 0;
            for(int i = 1; i < splittedLine.length; i++) {
                sum += Integer.parseInt(splittedLine[i]);
            }
            results.put(clientCode.getCityDistrict(), results.get(clientCode.getCityDistrict()) + sum);
        }
        System.out.println(results);

    }

    /*
    Dla  każdej  dzielnicy  oblicz  zużycie  wody  w  każdym  miesiącu  łącznie  przez  wszystkich mieszkańców tej dzielnicy.
    Podaj maksymalne miesięczne zużycie wody w każdej z dzielnic.
     */ //TODO: nie rozumiem polecenia
    private static void za5Part3() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        LinkedHashMap<String, ArrayList<Integer>> results = new LinkedHashMap<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));
        for(String s : content) {
            ArrayList<Integer> arr = new ArrayList<>();
            results.put(new ClientCode(s.split(";")[0]).getCityDistrict(), new ArrayList<>());
        }

    }



    private static void test() {
        ClientCode code = new ClientCode("1234598WIL");
        System.out.println(code.getClientNumber());
        System.out.println(code.getHowManyPeople());
        System.out.println(code.getCityDistrict());
    }
    public static void main(String[] args) throws IOException {
        zad5Part2();
    }
}
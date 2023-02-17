package Main;

import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.IOException;
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
        System.out.println(results.values());
        System.out.println(results);

    }

    /*
    Dla  każdej  dzielnicy  oblicz  zużycie  wody  w  każdym  miesiącu  łącznie  przez  wszystkich mieszkańców tej dzielnicy.
    Podaj maksymalne miesięczne zużycie wody w każdej z dzielnic.
     */
    private static void zad5Part3() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        LinkedHashMap<String, ArrayList<Integer>> results = new LinkedHashMap<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));
        for(String s : content) {
            results.put(new ClientCode(s.split(";")[0]).getCityDistrict(), new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0)));
        }

        LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<String, ArrayList<Integer>>();
        for(String s : content) {
            map.put(new ClientCode(s.split(";")[0]).getCityDistrict(), new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0)));
        }
        for (String s : content) {
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i = 1; i <= 12; i++) {
                String[] splitLine = s.split(";");
                arr.add(Integer.valueOf(splitLine[i]));
            }
            map.put(new ClientCode(s.split(";")[0]).getCityDistrict(), sumArraysWithTheSameLength(arr, map.get(new ClientCode(s.split(";")[0]).getCityDistrict())));
        }
        List<ArrayList<Integer>> list = new ArrayList<>(map.values());
        Set<String> keys = map.keySet();
        for(int i = 0; i < list.size(); i++) {
            int sum = 0;
            for(int j = 0; j < list.get(i).size(); j++)
                sum += list.get(i).get(j);
            System.out.println("Suma dla dzielnicy " + keys.toArray()[i] + " to: " + sum);
        }
    }
    /*
    Dział inwestycji analizuje konieczność modernizacji sieci wodociągowej na podstawie danych za  rok  2019.
    Jako  podstawę  obliczeń  bierze  sumaryczne  zużycie  wody  w każdym  z  12 miesięcy.
    Inżynierowie założyli, że sumaryczne miesięczne zużycie wody będzie rosło o 1% rok do roku każdego miesiąca (w m3 z zaokrągleniem w górę do najbliższej liczby całkowitej).
    Przykład: jeśli  w  styczniu  2019  roku  sumaryczne  zużycie  wody  w  mieście  wyniosło  53 545  m3,  to w styczniu 2020 przewidywane zużycie wyniesie 54 081 m3.
    Uwaga:  dla  danych  z  zadania  przewidywane  zużycie  wody  w  maju  2025  roku  wyniesie 90 898 m3.
    Obecnie maksymalny miesięczny przepływ (wydajność sieci) wynosi 160 000 m3.
    Podaj rok i miesiąc, w którym pierwszy raz zabraknie wody w mieście (przewidywane zużycie będzie większe niż maksymalny przepływ sieci).
    Sporządź zestawienie obrazujące przewidywane zużycie wody w każdym z kolejnych miesięcy od stycznia 2020 roku do grudnia 2030 roku.
    Narysuj  wykres  liniowy  obrazujący  przewidywane  zużycie  wody  w  każdym  z  kolejnych miesięcy w 2030 roku.
     */
    private static void zad5Part4() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        LinkedHashMap<Integer, ArrayList<Integer>> results = new LinkedHashMap<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));
        double multiplier = 1.0;

        for (int i = 1; i <= 12; i++) {
            double sum = 0;
            for (String s : content) {
                String[] lineSplit = s.split(";");
                sum += Integer.parseInt(lineSplit[i]) * multiplier;
            }
            temp.add((int) Math.ceil(sum));
        }
        for(int year = 2020; year <= 2030; year++) {
            for (int i = 0; i < temp.size(); i++) {
                temp.set(i,  (int)Math.ceil(temp.get(i) * 1.01));
            }
            results.put(year, new ArrayList<>(temp));
        }
        List<ArrayList<Integer>> values = new ArrayList<>(results.values());
        Set<Integer> keys = results.keySet();
        System.out.println("ZESTAWIENIE:");
        for(int i = 0; i < results.size(); i++) {
            System.out.println("Rok " + keys.toArray()[i] + ", Miesiące: " + values.toArray()[i]);
        }
        System.out.println("\nMIESIĄC GDZIE SIEĆ SIĘ PRZEPEŁNI: ");
        ArrayList<Integer> tempdasda = new ArrayList<>();
        for (ArrayList<Integer> value : values) {
            for (int j = 0; j < value.size(); j++) {
                if (value.get(j) > 160000) {
                    tempdasda.add(value.get(j));
                }
            }
        }
        System.out.println(tempdasda.get(0));
    }

    private static void zad5Part5() throws IOException {
        ArrayList<String> a = loadFileArray(new File("wodociagi.txt"));
        ArrayList<String> content = new ArrayList<>();
        LinkedHashMap<Integer, ArrayList<Integer>> results = new LinkedHashMap<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 1; i < a.size(); i++)
            content.add(a.get(i));

        double multiplier = 1.0;
        for (int i = 1; i <= 12; i++) {
            double sum = 0;
            for (String s : content) {
                String[] lineSplit = s.split(";");
                sum += Integer.parseInt(lineSplit[i]) * multiplier;
            }
            temp.add((int) Math.ceil(sum));
        }
        for(int year = 2020; year <= 2035; year++) {
            for (int i = 0; i < temp.size(); i++) {
                temp.set(i,  (int)Math.ceil(temp.get(i) * 1.01));
            }
            results.put(year, new ArrayList<>(temp));
        }
        List<ArrayList<Integer>> values = new ArrayList<>(results.values());
        Set<Integer> keys = results.keySet();
        int maxWaterFlow = 160000;
        for(int i = 0; i < keys.size(); i++) {
            if((int)keys.toArray()[i] >= 2021)
                maxWaterFlow += 1000;
            for(int j = 0; j < values.get(i).size(); j++)
                if(values.get(i).get(j) > maxWaterFlow) {
                    System.out.println(values.get(i).get(j));
                }
        }

    }
    /*
    Wodociągi  miejskie  zaplanowały  inwestycję,  która  począwszy  od  2021  roku  corocznie w styczniu pozwoli na zwiększanie maksymalnego przepływu o 1000 m3.
    Podaj  rok  i  miesiąc,  kiedy  pierwszy  raz  zabraknie  wody  w  mieście  po  uwzględnieniu  tej inwestycji.
     */


    public static void main(String[] args) throws IOException {
        zad5Part5();
    }
}
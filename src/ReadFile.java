import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    public static int[][] readIntArray2FromFile(String fileName) {
        try {
            return toIntArray2(readLinesFromFile(fileName));
        }
        catch(FileNotFoundException e) {
            return null;
        }
    }


    public static String[] readLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            // обязательно, чтобы закрыть открытый файл
        }
        return lines.toArray(new String[0]);
    }

    public static List<Integer> readIntegersFromFile(String fileName) throws FileNotFoundException {


            Scanner s = new Scanner(new File(fileName));
           // s.useDelimiter("\\s*");
            ArrayList<Integer> integers = new ArrayList<Integer>();
            while (s.hasNext()) {
                integers.add(s.nextInt());
                //s.nextLine(); // Eat the next line
                // Remove the conditional if statement to eat the new line
            }
            s.close();
            return integers;

    }



    public static int[][] toIntArray2(String[] lines) {
        int[][] arr2 = new int[lines.length][];
        for (int r = 0; r < lines.length; r++) {
            arr2[r] = toIntArray(lines[r]);
        }
        return arr2;
    }


    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }

        // из List<Integer> можно получить Integer[]
        Integer[] arr = list.toArray(new Integer[0]);
        // Integer[] -> int[]
        return toPrimitive(arr);
    }


    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }

    public static ArrayList<Double> ReadArrayListFromFile(String str){
        Scanner s = null;
        try {
            s = new Scanner(new File("filepath"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Double> list = new ArrayList<Double>();
        while (s.hasNext()){
            list.add(s.nextDouble());
        }
        s.close();
        return list;

    }
}
package Management;

import java.io.*;
import java.util.Comparator;
import java.util.TreeMap;

public class Orders {
    private static final InputStream csvFile = Orders.class.getResourceAsStream("/orders.csv");
    private static String line;
    private static final String csvDelimit = ",";

    // Custom Comparator
    private static Comparator<String> storeComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    };

    // TreeMap mit Comparator
    private static TreeMap<String, String> storeMap = new TreeMap<>(storeComparator);

    // Read Orders.csv
    public static TreeMap<String, String> getOrdersSorted() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(csvFile))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] storeFlavor = line.split(csvDelimit);
                if (storeFlavor.length == 2) {
                    String store = storeFlavor[0].trim();
                    String flavor = storeFlavor[1].trim();
                    storeMap.put(store, flavor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeMap;
    }
}

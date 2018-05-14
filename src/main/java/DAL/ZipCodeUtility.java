package DAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ZipCodeUtility {
    private String filename;
    private Map<Integer, String> zipMap;
    private static ZipCodeUtility INSTANCE;

    private ZipCodeUtility() {
        this.filename = "zipcodes.csv";
        this.zipMap = new HashMap<>();
        readData();
    }

    public static ZipCodeUtility getInstance() {
        if (INSTANCE == null) INSTANCE = new ZipCodeUtility();
        return INSTANCE;
    }

    private void readData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int zip = Integer.parseInt(parts[0]);
                String name = parts[1];

                zipMap.put(zip, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMunicipality(int zipCode) {
        /*String municipality = null;
        List<Integer> zipCodes = new ArrayList<>(zipMap.keySet());
        Collections.sort(zipCodes);

        for (int i = 0; i < zipCodes.size()-1; i++) {
            if (zipCodes.get(i) <= zipCode && zipCode < zipCodes.get(i+1)) {
                municipality = zipMap.get(zipCodes.get(i));
                break;
            }
        }

        return municipality;*/
        return zipMap.get(zipCode);
    }
}

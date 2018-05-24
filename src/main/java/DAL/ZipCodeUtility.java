package DAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to get info about zip codes
 */
public class ZipCodeUtility {
    private String fileName;
    private Map<Integer, String> zipMap;
    private static ZipCodeUtility INSTANCE;

    private ZipCodeUtility() {
        this.fileName = "zipcodes.csv";
        this.zipMap = new HashMap<>();
        readData();
    }

    /**
     * Get the instance of the singleton
     * @return ZipCodeUtility instance
     */
    public static ZipCodeUtility getInstance() {
        if (INSTANCE == null) INSTANCE = new ZipCodeUtility();
        return INSTANCE;
    }

    /**
     * Read zip code data from the file, into a map
     */
    private void readData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)))) {
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

    /**
     * Get the municipality name, from the zip code
     * @param zipCode zipcode
     * @return municipality name
     */
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

    /**
     * Check if the zip code is valid
     * @param zipCode a zip code
     * @return true if the zip code is valid.
     */
    public boolean isZipValid(int zipCode) {
        return zipMap.keySet().contains(zipCode);
    }
}

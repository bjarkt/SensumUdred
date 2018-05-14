import DAL.ZipCodeUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeUtilityTest {

    @Test
    public void ZipCodeTest() {
        assertEquals("Esbjerg N", ZipCodeUtility.getInstance().getMunicipality(6715));
        assertEquals("Odense S", ZipCodeUtility.getInstance().getMunicipality(5260));
        assertEquals("Frederiksberg", ZipCodeUtility.getInstance().getMunicipality(2000));
        assertEquals("København Ø", ZipCodeUtility.getInstance().getMunicipality(2100));

        assertNull(ZipCodeUtility.getInstance().getMunicipality(6716));


        assertTrue(ZipCodeUtility.getInstance().isZipValid(6700));
        assertFalse(ZipCodeUtility.getInstance().isZipValid(6701));
    }
}
package Tests;

import com.Csi;
import com.Kupa;
import com.Subject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KupaTest {
    Subject s = new Csi();
    Kupa k = new Kupa(s, "Kuba");
    List<Integer> values = new ArrayList<>(Arrays.asList(5,6,7));

    @Test
    void calculateAvgTest() {
        assertEquals(6.0, k.calculateAvg(values));
    }

    @Test
    void calculateMinTest() {
        assertEquals(5, k.calculateMin(values));
    }

    @Test
    void calculateMaxTest() {
        assertEquals(7, k.calculateMax(values));
    }
}
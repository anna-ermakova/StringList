import org.example.StringArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringArrayListTest {
    private StringArrayList out = new StringArrayList(5);

    @BeforeEach
    public void testList() {
        out.add("один");
        out.add("два");
        out.add("три");
        out.add("четыре");
        out.add("пять");
    }

    @AfterEach
    public void clearList() {
        out.clear();
    }

    @Test
    public void simpleAdditionPositiveTest() {
        int size = out.size();
        assertEquals("шесть", out.add("шесть"));
        assertEquals(size + 1, out.size());
    }
}

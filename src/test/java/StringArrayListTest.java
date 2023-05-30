import org.example.ElementNotFoundException;
import org.example.InvalidArgumentExсeption;
import org.example.StringArrayList;
import org.example.StringListIndexOutOfBoundsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class StringArrayListTest {
    private StringArrayList out = new StringArrayList(5);

    @BeforeEach
    public void testList() {
        out.add("odin");
        out.add("dva");
        out.add("tre");
        out.add("chetyre");
        out.add("pyt");
    }

    @AfterEach
    public void clearList() {
        out.clear();
    }

    @Test
    public void simpleAdditionPositiveTest() {
        int size = out.size();
        assertEquals("shest", out.add("shest"));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void indexAdditionPositiveTest() {
        int size = out.size();
        int index = 1;
        assertEquals("shest", out.add(index, "shest"));
        assertEquals(index, out.indexOf("shest"));
        assertEquals(size + 1, out.size());
    }

    @Test
    public void indexAdditionNegativeTest() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.add(5, "shest"));
    }

    @Test
    public void settingPositiveTest() {
        int size = out.size();
        int index = 1;
        assertEquals("shest", out.set(index, "shest"));
        assertEquals(index, out.indexOf("shest"));
        assertEquals(size, out.size());
    }

    @Test
    public void removeByValuePositiveTest() {
        int size = out.size();
        assertEquals("odin", out.remove("odin"));
        assertEquals(size - 1, out.size());
    }

    @Test
    public void removeByValueNegativeTest() {
        assertThrows(ElementNotFoundException.class, () -> out.remove("shest"));
    }

    @Test
    public void removeByIndexPositiveTest() {
        int size = out.size();
        assertEquals("odin", out.remove(0));
        assertEquals(size - 1, out.size());
    }

    @Test
    public void removeByIndexNegativeTest() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.remove(5));
    }

    public static Stream<Arguments> argumentsForContainsPositiveTest() {
        return Stream.of(
                Arguments.of("odin"),
                Arguments.of("dva"),
                Arguments.of("tre")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForContainsPositiveTest")
    public void containsPositiveTest(String str) {
        assertTrue(out.contains(str));
    }

    public static Stream<Arguments> argumentsForContainsNegativeTest() {
        return Stream.of(
                Arguments.of("sem"),
                Arguments.of("vosem"),
                Arguments.of("sto")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForContainsNegativeTest")
    public void containsNegativeTest(String str) {
        assertFalse(out.contains(str));
    }

    public static Stream<Arguments> argumentsForIndexOfPositiveTest() {
        return Stream.of(
                Arguments.of("odin", 0),
                Arguments.of("dva", 1),
                Arguments.of("tre", 2),
                Arguments.of("chetyre", 3),
                Arguments.of("pyt", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForIndexOfPositiveTest")
    public void indexOfPositiveTest(String str, int index) {
        assertEquals(index, out.indexOf(str));
    }

    public static Stream<Arguments> argumentsForIndexOfNegativeTest() {
        return Stream.of(
                Arguments.of("sto", -1),
                Arguments.of("bebe", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForIndexOfNegativeTest")
    public void indexOfNegativeTest(String str, int index) {
        assertEquals(index, out.indexOf(str));
    }

    public static Stream<Arguments> argumentsForLastIndexOfTest() {
        return Stream.of(
                Arguments.of("odin", 5),
                Arguments.of("dva", 6),
                Arguments.of("bebe", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForLastIndexOfTest")
    public void lastIndexOfTest(String str, int index) {
        out.add("odin");
        out.add("dva");
        assertEquals(index, out.lastIndexOf(str));
    }

    public static Stream<Arguments> argumentsForGetPositiveTest() {
        return Stream.of(
                Arguments.of("odin", 0),
                Arguments.of("dva", 1),
                Arguments.of("tre", 2),
                Arguments.of("chetyre", 3),
                Arguments.of("pyt", 4)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForGetPositiveTest")
    public void getPositiveTest(String str, int index) {
        assertEquals(str, out.get(index));
    }

    @Test
    public void getNegativeTest() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.get(5));
    }

    @Test
    public void equalsPositiveTest() {
        StringArrayList test = new StringArrayList(5);
        test.add("odin");
        test.add("dva");
        test.add("tre");
        test.add("chetyre");
        test.add("pyt");
        assertTrue(out.equals(test));
    }

    @Test
    public void equalsNullNegativeTest() {
        assertThrows(InvalidArgumentExсeption.class, () -> out.equals(null));
    }

    public static Stream<Arguments> argumentsForEqualsNegativeTest() {
        return Stream.of(
                Arguments.of((new StringArrayList("odin", "dva", "tre"))),
                Arguments.of((new StringArrayList("bebe", "dva", "tre", "chetyre", "pyt"))),
                Arguments.of((new StringArrayList("odin", "dva", "bebe", "chetyre", "pyt")))
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsForEqualsNegativeTest")
    public void equalsNegativeTest(StringArrayList arg) {
        assertFalse(out.equals(arg));
    }

    @Test
    public void isEmptyPositiveTest() {
        StringArrayList test = new StringArrayList(5);
        assertTrue(test.isEmpty());
    }

    @Test
    public void isEmptyNegativeTest() {
        assertFalse(out.isEmpty());
    }
    @Test
    public void toArrayTest() {
        String[] test = {"odin", "dva", "tre", "chetyre", "pyt"};
        assertArrayEquals(test, out.toArray());
    }

}


package fpexercice.extras;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void map1() {
        List<Integer> source = Arrays.asList(1, 2);

        List<String> dest = Utils.map1(i -> Integer.toString(i), source);

        assertArrayEquals(new String[]{"1", "2"}, dest.toArray(new String[0]));
    }

    @Test
    public void map2() {
        List<Integer> source = Arrays.asList(1, 2);

        List<String> dest = Utils.map2(i -> Integer.toString(i), source);

        assertArrayEquals(new String[]{"1", "2"}, dest.toArray(new String[0]));
    }

    @Test
    public void map3() {
        LinkedList<Integer> source = new LinkedList<>(1,
                new LinkedList<>(2,
                        new LinkedList<>(3)));

        LinkedList<String> dest = Utils.map3(i -> Integer.toString(i), source);

        assertArrayEquals(new String[]{"1", "2", "3"}, Utils.toArray(dest, String[]::new));
    }
}
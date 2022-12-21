import neta.mappers.Mappers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappersTest {

    @Test
    public void mapList() {
        var mappedList = Mappers.map(List.of(1, 2, 3), it -> String.valueOf(it));
        var expectedList = List.of("1", "2", "3");

        assertEquals(expectedList, mappedList);
    }

    @Test
    public void mapSet() {
        var mappedSet = Mappers.map(Set.of(1, 2, 3), it -> String.valueOf(it));
        var expectedSet = Set.of("1", "2", "3");

        assertEquals(expectedSet, mappedSet);
    }

    @Test
    public void mapVararg() {
        var mappedVarargs = Mappers.map(String.class, it -> it.toString(), 1, 2, 3);
        var expected = new String[] {"1", "2", "3"};

        assertArrayEquals(expected, mappedVarargs);
    }

    @Test
    public void associate() {
        var associated = Mappers.associate(List.of(1, 2, 3), it -> String.valueOf(it), it -> it * 10);
        var expected = Map.of("1", 10, "2", 20, "3", 30);

        assertEquals(expected, associated);
    }

    @Test
    public void associateBy() {
        var associated = Mappers.associateBy(List.of(1, 2, 3), it -> String.valueOf(it));
        var expected = Map.of("1", 1, "2", 2, "3", 3);

        assertEquals(expected, associated);
    }

    @Test
    public void associateWith() {
        var associated = Mappers.associateWith(List.of(1, 2, 3), it -> String.valueOf(it));
        var expected = Map.of(1, "1", 2, "2", 3, "3");

        assertEquals(expected, associated);
    }

    @Test
    public void groupByList() {
        var grouped = Mappers.groupBy(List.of(1, 2, 3, -1, -2, -3), it -> it < 0);
        var expected = Map.of(true, List.of(-1, -2, -3), false, List.of(1, 2, 3));

        assertEquals(expected, grouped);
    }

    @Test
    public void groupBySet() {
        var grouped = Mappers.groupBy(Set.of(1, 2, 3, -1, -2, -3), it -> it < 0);
        var expected = Map.of(true, Set.of(-1, -2, -3), false, Set.of(1, 2, 3));

        assertEquals(expected, grouped);
    }

    @Test
    public void flattenList() {
        var flattenedList = Mappers.flatten(List.of(List.of(1, 2, 3), List.of(10, 20, 30)), it -> it.stream().map(number -> number.toString()));
        var expected = List.of("1", "2","3","10","20","30");

        assertEquals(expected, flattenedList);
    }

    @Test
    public void flattenSet() {
        var flattenedList = Mappers.flatten(Set.of(List.of(1, 2, 3), List.of(10, 20, 30)), it -> it.stream().map(number -> number.toString()));
        var expected = Set.of("1", "2","3","10","20","30");

        assertEquals(expected, flattenedList);
    }

}

package neta.mappers;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Mappers {

    public static <T, R> List<R> map(List<T> toMap, Function<T, R> mapper) {
        return mappedStream(toMap, mapper).collect(Collectors.toUnmodifiableList());
    }

    public static <T, R> Set<R> map(Set<T> toMap, Function<T, R> mapper) {
        return mappedStream(toMap, mapper).collect(Collectors.toUnmodifiableSet());
    }

    public static <T, R> R[] map(T[] toMap, Class<R> returnType, Function<T, R> mapper) {
        return Arrays.stream(toMap).map(mapper).toArray(size -> (R[]) Array.newInstance(returnType, size));
    }

    public static <T, R> R[] map(Class<R> returnType, Function<T, R> mapper, T... toMap) {
        return map(toMap, returnType, mapper);
    }

    public static <T, K, V> Map<K, V> associate(Collection<T> toAssociate, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return toAssociate.stream().collect(Collectors.toUnmodifiableMap(keyMapper, valueMapper));
    }

    public static <T, K> Map<K, T> associateBy(Collection<T> toAssociate, Function<T, K> keyMapper) {
        return associate(toAssociate, keyMapper, Function.identity());
    }

    public static <T, V> Map<T, V> associateWith(Collection<T> toAssociate, Function<T, V> valueMapper) {
        return associate(toAssociate, Function.identity(), valueMapper);
    }

    public static <T, K> Map<K, List<T>> groupBy(List<T> toGroup, Function<T, K> groupBy) {
        return toGroup.stream().collect(Collectors.groupingBy(groupBy, Collectors.toUnmodifiableList()));
    }

    public static <T, K> Map<K, Set<T>> groupBy(Set<T> toGroup, Function<T, K> groupBy) {
        return toGroup.stream().collect(Collectors.groupingBy(groupBy, Collectors.toUnmodifiableSet()));
    }

    public static <T, R> List<R> flatten(List<T> toFlatten, Function<T, Stream<R>> mapper) {
        return toFlatten.stream().flatMap(mapper).collect(Collectors.toUnmodifiableList());
    }

    public static <T, R> Set<R> flatten(Set<T> toFlatten, Function<T, Stream<R>> mapper) {
        return toFlatten.stream().flatMap(mapper).collect(Collectors.toUnmodifiableSet());
    }

    public static <T> List<T> filterNotNull(List<T> toFilter) {
        return filteredNotNullStream(toFilter).collect(Collectors.toUnmodifiableList());
    }

    public static <T> Set<T> filterNotNull(Set<T> toFilter) {
        return filteredNotNullStream(toFilter).collect(Collectors.toUnmodifiableSet());
    }

    public static <T, R> List<R> mapNotNull(List<T> toMap, Function<T, R> mapper) {
        return filteredNotNullStream(mappedStream(toMap, mapper)).collect(Collectors.toUnmodifiableList());
    }

    public static <T, R> Set<R> mapNotNull(Set<T> toMap, Function<T, R> mapper) {
        return filteredNotNullStream(mappedStream(toMap, mapper)).collect(Collectors.toUnmodifiableSet());
    }

    private static <T> Stream<T> filteredNotNullStream(Collection<T> toFilter) {
        return filteredNotNullStream(toFilter.stream());
    }

    private static <T> Stream<T> filteredNotNullStream(Stream<T> stream) {
        return stream.filter(it -> it != null);
    }

    private static <T, R> Stream<R> mappedStream(Collection<T> toMap, Function<T, R> mapFunction) {
        return toMap.stream().map(mapFunction);
    }
}
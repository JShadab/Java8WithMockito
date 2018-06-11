package clinic.util;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Tuple<T1, T2> {
    private final T1 v1;
    private final T2 v2;

    public Tuple(T1 v1, T2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public static <K, V> Collector<Tuple<K, V>, ?, Map<K, V>> toMapCollector() {
        return Collectors.toMap(Tuple::getV1, Tuple::getV2);
    }

    public static <T1, T2> Tuple<T1, T2> of(T1 v1, T2 v2) {
        return new Tuple<>(v1, v2);
    }

    public T1 getV1() {
        return v1;
    }

    public T2 getV2() {
        return v2;
    }

    public static <T1, T2> Tuple<T1, T2> of(Map.Entry<T1, T2> entry) {
        return new Tuple<>(entry.getKey(), entry.getValue());
    }

    public static <T> Tuple<T, T> dup(T v) {
        return of(v, v);
    }

    public static <T1, T2>Optional<Tuple<T1, T2>> promoteOption2(Tuple<T1, Optional<T2>> t) {
        return t.getV2().map(v2 -> Tuple.of(t.getV1(), v2));
    }
}

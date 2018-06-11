package clinic.util;

import clinic.util.Tuple;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Util {
    public static <K, VS, VD> Function<Tuple<K, VS>, Tuple<K, VD>>
    mapValues(Function<VS, VD> f) {
        return entry -> new Tuple<>(entry.getV1(), f.apply(entry.getV2()));
    }

    public static <T> Stream<T> optionToStream(Optional<T> optional) {
        return optional.map(Stream::of).orElse(Stream.empty());
    }
}

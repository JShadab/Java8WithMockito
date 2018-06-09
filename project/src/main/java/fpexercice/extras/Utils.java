package fpexercice.extras;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {
    public static <S, D> List<D> map1(Function<S, D> f, List<S> list) {
        ArrayList<D> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            res.add(f.apply(list.get(i)));
        }
        return res;
    }

    public static <S, D> List<D> map2(Function<S, D> f, List<S> list) {
        if (list.size() == 0) {
            return new ArrayList<>();
        } else {
            ArrayList<D> res = new ArrayList<>();
            res.add(f.apply(list.get(0)));
            res.addAll(map2(f, list.subList(1, list.size())));
            return res;
        }
    }

    public static <S, D> LinkedList<D> map3(Function<S, D> f, LinkedList<S> list) {
        if (list == null) {
            return null;
        } else {
            D newValue = f.apply(list.getValue());
            LinkedList<D> newRest = map3(f, list.getNext());
            return new LinkedList<>(newValue, newRest);
        }
    }

    private static <T> void forEach(Consumer<T> f, LinkedList<T> list) {
        if (list != null) {
            f.accept(list.getValue());
            forEach(f, list.getNext());
        }
    }

    public static <T> T[] toArray(LinkedList<T> dest, Function<Integer, T[]> arrayConstructor) {
        int size = getSize(dest);
        T[] array = arrayConstructor.apply(size);
        int[] i = {0};
        Utils.<Tuple<T, Integer>>forEach(t -> array[t.getV2()] = t.getV1(),
                map3(el -> new Tuple<>(el, i[0]++), dest));
        return array;
    }

    public static <T> T[] toArray2(LinkedList<T> dest, Function<Integer, T[]> arrayConstructor) {
        int size = getSize(dest);
        T[] array = arrayConstructor.apply(size);
        map3(
                Utils.<T>zipWithIndex()
                        .andThen(t -> v(array[t.getV2()] = t.getV1())),
                dest);
        return array;
    }

    private static <T> Function<T, Tuple<T, Integer>> zipWithIndex() {
        int[] i = {0};
        return el -> new Tuple<>(el, i[0]++);
    }

    private static <T> Void v(T t) {
        return null;
    }

    private static <T> int getSize(LinkedList<T> dest) {
        if (dest == null) {
            return 0;
        } else {
            return 1 + getSize(dest.getNext());
        }
    }
}

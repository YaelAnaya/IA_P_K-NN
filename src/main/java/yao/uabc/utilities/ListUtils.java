package yao.uabc.utilities;

import yao.uabc.algorithm.KDistance;

import java.util.*;
import java.util.Map.Entry;

/**
 * Esta clase contiene métodos utilies que facilitan el trabajo con colecciones de tipo List.
 * */
public class ListUtils {

    /**
     * Este método obtiene el elemento más común de una lista.
     * @param list La lista de la que se obtendrá el elemento más común.
     * @return El elemento más común de la lista.
     */
    public static <T> T mostCommonItem(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        // Se cuenta la cantidad de veces que aparece cada elemento en la lista.
        for (T t : list) {
            var value = map.get(t);
            map.put(t, (value == null ? 1 : value + 1));
        }
        // Se obtiene el elemento con mayor cantidad de apariciones, para posteriormente retornarlo.
        var max = map.entrySet().stream().max(Entry.comparingByValue()).orElse(null);
        return max != null ? max.getKey() : null;
    }

}

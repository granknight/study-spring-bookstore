package me.xyzlast.bh.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ykyoon on 3/11/14.
 */
public class ShortDistance {
    public static String getShortestPoint(Integer[] points) {
        List<Integer> list = Arrays.asList(points);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2) * -1;
            }
        });

        int minValue = Integer.MAX_VALUE;
        int pos = 0;
        for(int i = 0 ; i < list.size() - 1 ; i++) {
            int currentLength = Math.abs(list.get(i) - list.get(i + 1));
            if(currentLength < minValue) {
                minValue = currentLength;
                pos = i;
            }
        }

        return String.format("(%d, %d)", list.get(pos + 1), list.get(pos));
    }
}

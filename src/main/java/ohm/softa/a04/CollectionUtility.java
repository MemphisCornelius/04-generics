package ohm.softa.a04;

import java.util.Comparator;
import java.util.Iterator;

public abstract class CollectionUtility {

    public static <T> SimpleList<T> sort(SimpleList<T> list, Comparator<T> cmp) {
        if (list.size() > 1) {
            SimpleList<T> left = new SimpleListImpl<>();
            SimpleList<T> right = new SimpleListImpl<>();
            Iterator<T> iterator = list.iterator();
            for (int i = 0; i < list.size() / 2 ; i++) {
                left.add(iterator.next());
            }
            while (iterator.hasNext()) {
                right.add(iterator.next());
            }

            left = sort(left, cmp);
            right = sort(right, cmp);

            return merge(left, right, cmp);
        }
        return list;
    }

    private static <T> SimpleList<T> merge(SimpleList<T> left, SimpleList<T> right, Comparator<T> cmp) {
        SimpleList<T> result = new SimpleListImpl<T>();
        Iterator<T> leftIterator = left.iterator();
        Iterator<T> rightIterator = right.iterator();

        T leftCurrent = leftIterator.next();
        T rightCurrent = rightIterator.next();

        while (leftIterator.hasNext() && rightIterator.hasNext()) {

            if(cmp.compare(leftCurrent, rightCurrent) <= 0) {
                result.add(leftCurrent);
                leftCurrent = leftIterator.next();
            }else {
                result.add(rightCurrent);
                rightCurrent = rightIterator.next();
            }
        }
        while (leftIterator.hasNext()) {
            result.add(leftIterator.next());
        }
        while (rightIterator.hasNext()) {
            result.add(rightIterator.next());
        }

        if(cmp.compare(leftCurrent, rightCurrent) <= 0) {
            result.add(leftCurrent);
            result.add(rightCurrent);
        }else {
            result.add(rightCurrent);
            result.add(leftCurrent);
        }
        return result;
    }
}

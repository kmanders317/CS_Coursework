import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator comp;
    public MaxArrayDeque61B(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int maxInt = 0;
        T maxT = this.get(0);
        Iterator<T> seer = this.iterator();
        Iterator<T> seer2 = this.iterator();
        while (seer.hasNext()) {
            T i = seer.next();
            //T j = seer.next();
            if (comp.compare(i, maxT) > 0) {
                maxT = i;
            }
        }
        return maxT;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxInt = 0;
        T maxT = this.get(0);
        Iterator<T> seer = this.iterator();
        while (seer.hasNext()) {
            T i = seer.next();
            //T j = seer.next();
            if (c.compare(i, maxT) > 0) {
                maxT = i;
            }
        }
        return maxT;
    }

}

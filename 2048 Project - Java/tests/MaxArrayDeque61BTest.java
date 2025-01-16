import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class MinStringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return b.length() - a.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void basicTestMin() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max(new MinStringLengthComparator())).isEqualTo("");
    }

    @Test
    public void basicTestEmpty() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new MinStringLengthComparator());
        assertThat(mad.max()).isEqualTo(null);
    }

    @Test
    public void basicTestInt() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(3);
        assertThat(m.max()).isEqualTo(3);
    }




//    @Test
//    public void notequalToTestBasic() {
//        StringLengthComparator s = new StringLengthComparator();
//        MaxArrayDeque61B<String> lld1 = new MaxArrayDeque61B<>(s);
//
//        lld1.addLast("front");
//        lld1.addLast("middle");
//
//        LinkedListDeque61B<String> lld2 = new LinkedListDeque61B<>();
//
//        lld2.addLast("front");
//        lld2.addLast("middle");
//        lld2.addLast("back");
//
//        assertThat(lld1.equals(lld2)).isEqualTo(false);
//    }
    
}

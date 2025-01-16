import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.ArrayList;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        System.out.println(lld1.toList());
        assertThat(lld1.toList()).containsExactly(1).inOrder();

        lld1.addFirst(2);
        assertThat(lld1.toList()).containsExactly(2, 1).inOrder();

        lld1.addFirst(3);
        assertThat(lld1.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addLast(1);
        assertThat(lld1.toList()).containsExactly(1).inOrder();

        lld1.addLast(2);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();

        lld1.addLast(3);
        assertThat(lld1.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void removeFirstTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(2, 3).inOrder();
    }

    @Test
    public void removeLastTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeLast();

        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
    }

    @Test
    public void removeLastToOne() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.removeLast();

        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void removeLastToEmpty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.removeLast();
        lld1.removeLast();

        assertThat(lld1.toList()).containsExactly().inOrder();
    }

    @Test
    public void removeLastToEmpty2() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast(2);

        assertThat(lld1.toList()).containsExactly(2).inOrder();
    }

    @Test
    public void removeFirstToEmpty2() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.addFirst(2);

        assertThat(lld1.toList()).containsExactly(2).inOrder();
    }

    @Test
    public void emptyTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void notEmptyTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);

        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void getTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeLast();
        System.out.println(lld1.get(0));

        assertThat(lld1.get(0)).isEqualTo(1);
    }

    @Test
    public void sizeTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeLast();

        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void resizeTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7);


        assertThat(lld1.toList()).containsExactly(5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    public void resize2TestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1); //[1]
        lld1.addFirst(2);
        lld1.removeFirst();
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addLast(2);
        lld1.removeLast();
        lld1.addLast(3); //[5, 4, 3, 1, 3]
        lld1.addLast(4); //[5, 4, 3, 1, 3, 4]
        lld1.addLast(5);
        lld1.addLast(6);
        lld1.addLast(7); //[5, 4, 3, 1, 3, 4, 5, 6, 7]
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5); //[5, 4, 3, 5, 4, 3, 1, 3, 4, 5, 6, 7]


        assertThat(lld1.toList()).containsExactly(5, 4, 3, 5, 4, 3, 1, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    public void resizeDownTestBasic() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();


        assertThat(lld1.toList()).containsExactly(2, 1).inOrder();
    }


    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.toList()).containsExactly().inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.toList()).containsExactly().inOrder();
    }

    @Test
    public void get_oob_large() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.get(5)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertThat(lld1.get(-1)).isEqualTo(null);
    }

    @Test
    public void size_after_remove_to_empty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void size_after_remove_from_empty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        lld1.removeFirst();

        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void to_list_empty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<Integer>();

        assertThat(lld1.toList()).containsExactly().inOrder();
    }

    @Test
    public void toStringTestBasic() {
        ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }

    @Test
    public void equalToTestBasic() {
        ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        ArrayDeque61B<String> lld2 = new ArrayDeque61B<>();

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1.equals(lld2)).isEqualTo(true);
    }

    @Test
    public void notequalToTestBasic() {
        ArrayDeque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");

        ArrayDeque61B<String> lld2 = new ArrayDeque61B<>();

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1.equals(lld2)).isEqualTo(false);
    }

    @Test
    public void testIfLinkedEqualsArr() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        ArrayDeque61B<String> lld2 = new ArrayDeque61B<>();

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld2.equals(lld1)).isEqualTo(true);
    }

}

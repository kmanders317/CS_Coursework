import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.ArrayList;

public class LinkedListDeque61BTest {
    @Test
    public void toStringTestBasic() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }

    @Test
    public void equalToTestBasic() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        LinkedListDeque61B<String> lld2 = new LinkedListDeque61B<>();

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1.equals(lld2)).isEqualTo(true);
    }

    @Test
    public void notequalToTestBasic() {
        LinkedListDeque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");

        LinkedListDeque61B<String> lld2 = new LinkedListDeque61B<>();

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

        assertThat(lld1.equals(lld2)).isEqualTo(true);
    }



}

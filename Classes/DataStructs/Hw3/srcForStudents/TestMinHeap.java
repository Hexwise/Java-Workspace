import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestMinHeap {

    private static Task[] array1;
    private static Task[] array2;
    private static Task[] array3;
    private MinHeap<Task> heap1;
    private MinHeap<Task> heap2;

    @Before
    public void setUp() {
        array1 =  new Task[0];
        array2 = new Task[] {
            new Task("build-project", 1220, 20),
            new Task("unit-tests", 1230, 2300),
            new Task("integration-test", 1359, 300),
            new Task("regression-test", 1410, 20)
        };
        array3 = array2.clone();

        heap1 =  new MinHeap<Task>(array1);
        heap2 =  new MinHeap<Task>(array2);
    }

    @Test
    public void testInsert() {
        assertTrue(heap1.isEmpty());
        Task task = new Task("final-checks", 1590, 50);
        heap1.insert(task);
        assertFalse(heap1.isEmpty());
        assertTrue(task.compareTo(heap1.remove()) == 0);
        heap2.insert(task);
        for (int i=0; i<array3.length; i++) {
            assertTrue(heap2.remove().compareTo(array3[i]) == 0);
        }
        assertTrue(task.compareTo(heap2.remove()) == 0);
        assertTrue(heap2.isEmpty());
    }

    @Test
    public void testRemove() {
        assertTrue(heap1.isEmpty());
        Task task = new Task("final-checks", 1590, 50);
        heap1.insert(task);
        assertFalse(heap1.isEmpty());
        assertTrue(task.compareTo(heap1.remove()) == 0);
        assertTrue(heap1.isEmpty());
        heap2.insert(task);
        for (int i=0; i<array3.length; i++) {
            assertTrue(heap2.remove().compareTo(array3[i]) == 0);
        }
        assertTrue(task.compareTo(heap2.remove()) == 0);
        assertTrue(heap2.isEmpty());
        try {
            heap2.remove();
        }
        catch(IllegalArgumentException e) {
            assertTrue(true); // only one type of throw exception
        }
    }

    @Test
    public void testIsEmpty() {
        assertFalse(heap2.isEmpty());
        assertTrue(heap1.isEmpty());
        Task task = new Task("final-checks", 1590, 50);
        heap1.insert(task);
        assertFalse(heap1.isEmpty());
        heap1.remove();
        assertTrue(heap1.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(heap1.isFull());
        assertTrue(heap2.isFull());
    }

    @Test
    public void testToString() {
        String s = new String();
        for (Task task: array3) {
            s.concat(task + " ");
        }
        s.trim();
        assertTrue(heap2.toString().equals(s));
        assertTrue(heap1.toString().equals(new String()));
    }

}
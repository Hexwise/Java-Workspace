import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestUnboundedPriorityQueue {
    
    private static Task[] array1;
    private static Task[] array2;
    private UnboundedPriorityQueue<Task> queue;

    @Before
    public void setUp() {
        queue = new UnboundedPriorityQueue<Task>();
        array1 = new Task[] {
            new Task("build-project", 1220, 20),
            new Task("unit-tests", 1230, 2300),
            new Task("integration-test", 1359, 300),
            new Task("regression-test", 1410, 20)
        };
        array2 = new Task[] {
            new Task("regression-test", 1410, 20),
            new Task("integration-test", 1359, 300),
            new Task("unit-tests", 1230, 2300),
            new Task("build-project", 1220, 20)
        };
   }

    @Test
    public void testEnqueue() {
        for (Task task: array2) {
            queue.enqueue(task);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            assertTrue(queue.dequeue().compareTo(array1[i++]) == 0);
        }
    }

    @Test
    public void testDequeue() {
        for (Task task: array2) {
            queue.enqueue(task);
        }
        int i = 0;
        while (!queue.isEmpty()) {
            assertTrue(queue.dequeue().compareTo(array1[i++]) == 0);
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(array1[0]);
        assertFalse(queue.isEmpty());
    }

}

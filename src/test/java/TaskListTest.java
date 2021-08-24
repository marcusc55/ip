import duke.TaskList;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void add_task_test() {
        TaskList list = new TaskList();
        list.add(new Deadline("return book", LocalDateTime.of(2020, 3, 10, 14, 0)));
        assertEquals(list.get(list.size() - 1).getName(), "return book");
    }

    @Test
    void set_done_test() {
        TaskList list = new TaskList();
        list.add(new Deadline("return book", LocalDateTime.of(2020, 3, 10, 14, 0)));
        list.setDone(0);
        assertEquals(list.get(0).isComplete(), true);
    }
}

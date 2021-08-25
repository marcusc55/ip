package duke;

import duke.task.Task;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n%s\nNow you have %d %s in your list.";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String REMOVE_MESSAGE = "Noted. I've removed this task:\n%s\nNow you have %d %s in your list.";
    private static final String LINE = "____________________________________________________________\n";

    public static void welcomeMessage() {
        formatAndPrint(WELCOME_MESSAGE);
    }

    public static void goodbyeMessage() {
        formatAndPrint(GOODBYE_MESSAGE);
    }

    public static void displayTasks(TaskList list) {
        formatAndPrint(LIST_MESSAGE + list.toString());
    }

    public static void addTaskMessage(Task task, int size) {
        formatAndPrint(String.format(ADD_MESSAGE, task, size, size == 1 ? "task" : "tasks"));
    }

    public static void deleteMessage(Task task, int size) {
        formatAndPrint(String.format(REMOVE_MESSAGE, task, size, size == 1 ? "task" : "tasks"));
    }

    public static void doneMessage(Task task) {
        formatAndPrint(DONE_MESSAGE + task);
    }

    /**
     * Helper function to format output between 2 lines.
     *
     * @param output String to be outputted.
     */
    public static void formatAndPrint(String output) {
        System.out.printf("%s%s\n%s", LINE, output, LINE);
    }
}

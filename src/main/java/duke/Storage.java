package duke;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class represents a Storage object, which saves the user's tasks into a text file.
 */
public class Storage {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final File file;

    /**
     * Constructor for a Storage object.
     *
     * @param path Path of the file where data should be saved.
     * @throws IOException If an I/O error occurs.
     */
    public Storage(String path) throws IOException {
        assert path != null : "Path for file storage cannot be null.";
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    /**
     * Saves a lists of tasks into a text file.
     *
     * @param list List of tasks to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void save(List<Task> list) throws IOException {
        FileWriter writer = new FileWriter(file);
        StringBuilder output = new StringBuilder();
        for (Task t : list) {
            output.append(formatTask(t));
            output.append("\n");
        }
        writer.write(output.toString());
        writer.close();
    }

    /**
     * Creates a list of tasks from a file.
     *
     * @return List of Tasks extracted from file.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException         If task import fails to parse task list.
     */
    public List<Task> readFile() throws FileNotFoundException, DukeException {
        List<Task> output = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            output.add(readTask(sc.nextLine()));
        }
        return output;
    }

    /**
     * Formats a task to be saved in the file.
     * Tasks are formatted in the following way:
     * identifier | completion status | name | date
     * Identifier is a single letter representing the type of task.
     * Completion status is represented by 0 (not complete) or 1 (complete).
     *
     * @param task Task to be formatted.
     * @return String containing the task details.
     */
    private String formatTask(Task task) {
        String identifier = task.getType();
        String completionStatus = task.isComplete() ? "1" : "0";
        String dateString = "";
        if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            LocalDateTime date = d.getDate();
            dateString = date.format(FORMATTER);
        }
        if (task instanceof Event) {
            Event e = (Event) task;
            LocalDateTime date = e.getDate();
            dateString = date.format(FORMATTER);
        }
        return String.join("|", identifier, completionStatus, task.getName(), dateString);
    }

    /**
     * Creates a task from the string provided. String must be in the form:
     * identifier | completion status | name | date
     *
     * @param input String representing the task to be created.
     * @return Task created.
     */
    private Task readTask(String input) throws DukeException {
        String[] splitInput = input.split("\\|", -1);
        String identifier = splitInput[0];
        boolean isComplete = parseInt(splitInput[1]) == 1;
        Task task;
        switch (identifier) {
        case Todo.IDENTIFIER:
            task = new Todo(splitInput[2]);
            break;
        case Deadline.IDENTIFIER:
            task = new Deadline(splitInput[2], parseDate(splitInput[3]));
            break;
        case Event.IDENTIFIER:
            task = new Event(splitInput[2], parseDate(splitInput[3]));
            break;
        default:
            throw new DukeException("Imported task with invalid type.");
        }
        if (isComplete) {
            task.setDone();
        }
        return task;
    }

    /**
     * Parses the date from a string.
     *
     * @param s String representing a date.
     * @return LocalDateTime object representing the date.
     */
    private LocalDateTime parseDate(String s) {
        String[] splitDate = s.split(" ", 2);
        // Split day, month, year
        String[] splitDateElements = splitDate[0].split("/", 3);
        int day = parseInt(splitDateElements[0]);
        int month = parseInt(splitDateElements[1]);
        int year = parseInt(splitDateElements[2]);
        // Split hours and minutes
        int hours = parseInt(splitDate[1].substring(0, 2));
        int mins = parseInt(splitDate[1].substring(2, 4));
        return LocalDateTime.of(year, month, day, hours, mins);
    }
}

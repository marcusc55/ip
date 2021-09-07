package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

public class EventCommand extends Command {
    private static final String COMMAND_WORD = "event";

    /**
     * Returns the command word for an event.
     *
     * @return "event" command representing an event.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Creates an event task and adds it to the task list.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @return Output to be displayed in GUI.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String run(Duke duke, Parser parser) throws DukeException {
        Task task = new Event(parser.getTaskName(), parser.getTaskDate());
        duke.getList().add(task);
        return Ui.addTaskMessage(task, duke.getList().size());
    }
}

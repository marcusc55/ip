package duke.task;

/**
 * This class represents a user's task.
 */
public abstract class Task {
    private final String taskName;
    private boolean isComplete;

    /**
     * Constructor for a task. When tasks are initialised, they are marked as not complete.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isComplete = false;
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return taskName;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void setDone() {
        isComplete = !isComplete;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Returns X if task is completed, " " otherwise.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Gets a single letter representing the type of the task.
     *
     * @return Returns the type of the task.
     */
    public abstract String getType();

    /**
     * Returns a string representation of the task.
     * This displays the completion status of the task along with its name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", taskName);
    }
}

package Duke.Command;

import Duke.Duke;
import Duke.Parser;

public class EndCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) {
        duke.stop();
    }
}
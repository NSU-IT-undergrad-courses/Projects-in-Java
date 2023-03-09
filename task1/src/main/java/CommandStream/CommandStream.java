package CommandStream;

import Commands.Command;

public abstract interface CommandStream {
    public abstract String[] getCommandFromStream();
}

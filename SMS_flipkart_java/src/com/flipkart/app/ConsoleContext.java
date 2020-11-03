package com.flipkart.app;

/**
 * The type Console context.
 */
public class ConsoleContext {
    private Console console = null;

    /**
     * Instantiates a new Console context.
     *
     * @param initialConsole the initial console
     */
    public ConsoleContext(Console initialConsole) {
        this.console = initialConsole;
    }

    /**
     * Sets console.
     *
     * @param console the console
     */
    public void setConsole(Console console) {
        this.console = console;
    }

    /**
     * Execute boolean.
     *
     * @return the boolean
     */
    public boolean execute() {
        if (console != null) {
            console.execute(this);
            return true;
        }

        return false;
    }
}

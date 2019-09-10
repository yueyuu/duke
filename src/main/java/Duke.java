import java.io.IOException;

/**
 * The main class of the project.
 */
public class Duke {

    /**
     * Executes the main bulk of Duke.
     * Deduces if the user did not enter a description for the command.
     *
     * @param list the list containing the user's task.
     * @param parser the Parser object that will process the user's input.
     * @throws IOException if parser.parse(list) fails.
     */
    public static void executeDuke(TaskList list, Parser parser) throws IOException{
        while (true) {
            parser.readInput();
            try {
                parser.parse(list);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printEmptyDescription(parser.command);
            }
        }
    }

    /**
     * Executes the whole of Duke.
     * Creates the Ui, TaskList and Parser object for this Duke to run.
     *
     * @param args the array that stores the input from the command line.
     * @throws IOException if a TaskList object cannot be created and if the executeDuke method fails.
     */
    public static void main(String[] args) throws IOException{
        Ui.startup();
        TaskList list = new TaskList();
        Parser parser = new Parser();

        executeDuke(list, parser);
    }

}

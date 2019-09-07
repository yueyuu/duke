import java.io.IOException;

public class Duke {

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

    public static void main(String[] args) throws IOException {
        Ui.startup();
        TaskList list = new TaskList();
        Parser parser = new Parser();

        executeDuke(list, parser);
    }

}

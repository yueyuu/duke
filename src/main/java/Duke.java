import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        Ui.startup();
        TaskList list = new TaskList();
        Parser parser = new Parser();


        while (true) {
            parser.readInput();
            try {
                parser.parse(list);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printEmptyDescription(parser.command);
            }
        }

    }

}

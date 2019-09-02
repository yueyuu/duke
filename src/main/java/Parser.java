import java.util.Scanner;

public class Parser {
    //deals with making sense of the user command
    protected String command;
    protected Scanner in;

    public Parser() {
        this.in = new Scanner(System.in); //setting up to read in input from user
    }

    public String[] readInput() {
        String input = in.nextLine().trim(); // read in input (the whole line)
        return input.split(" ", 2); //extract out the command word
    }
    public void parse() {

    }
}

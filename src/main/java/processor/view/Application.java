package processor.view;

import processor.controller.Controller;
import processor.SearchQuery;
import processor.view.utils.ExecuteComponent;
import processor.view.utils.PrettyPrinter;

import java.util.Scanner;
import java.util.function.Function;

/**
 * Application Class Provides A CMD UI.
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     Controller
 * @see     SearchQuery
 * @see     ExecuteComponent
 */

public abstract class Application {
    protected Function<String[], SearchQuery> parser;
    protected Controller controller = new Controller();
    protected Scanner scanner = new Scanner(System.in);
    protected ExecuteComponent executeComponent = new ExecuteComponent();
    protected PrettyPrinter printer;
    private String appName, version;
    private String goodByeMessage;

    public Application(String appName, String version, String goodByeMessage, PrettyPrinter printer) {
        parser = (String[] strings) -> {
          SearchQuery query = new SearchQuery();
          parseInput(strings, query);
          return query;
        };
        this.appName = appName;
        this.version = version;
        this.goodByeMessage = goodByeMessage;
        this.printer = printer;
        executeComponent.put("help", (String... strings) -> showHelp());
        executeComponent.put("exit", (String... strings) -> exit());
    }

    /**
     * Application Starts At This Point
     */
    public void start() {
        initExecutors();
        sayWelcome();
        try {
            run();
        } catch (ExitException e) {
            System.out.println(goodByeMessage);
        }
    }

    private void sayWelcome() {
        System.out.println("Welcome To " + appName + ". v" + version);
        System.out.println("Please Enter \"help\" To See The CommandList\n");
    }

    protected String getUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * @throws ExitException
     */
    public void exit() throws ExitException {
        throw new ExitException();
    }

    public static class ExitException extends RuntimeException {}

    /**
     * This Method Is For Taking Search Input And Creating SearchQuery From It.
     *
     * @param inputs
     * @param query
     */
    public abstract void parseInput(String[] inputs, SearchQuery query);

    public abstract void initExecutors();

    public abstract void run();

    public abstract void showHelp();
}

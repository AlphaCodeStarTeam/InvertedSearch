package vc.view;

import vc.controller.Controller;
import vc.SearchQuery;
import vc.view.utils.ExecuteComponent;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Application {
    private String appName, version;
    Function<String[], SearchQuery> parser;
    protected Controller controller = new Controller();
    protected Scanner scanner = new Scanner(System.in);
    protected ExecuteComponent executerComponent = new ExecuteComponent();

    public Application(String appName, String version, String goodByeMessage) {
        parser = (String[] strings) -> {
          SearchQuery query = new SearchQuery();
          parseInput(strings, query);
          return query;
        };
        this.appName = appName;
        this.version = version;
        executerComponent.put("help", (String... strings) -> showHelp());
        executerComponent.put("exit", (String... strings) -> exit());
        initExecutors();
        sayWelcome();

        try {
            run();
        } catch (ExitException e) {
            System.out.println(goodByeMessage);
        }
    }

    public abstract void initExecutors();

    public abstract void run();

    public abstract void parseInput(String[] inputs, SearchQuery query);

    protected String getUserInput() {
        return scanner.nextLine().trim();
    }

    private void sayWelcome() {
        System.out.println("Welcome To " + appName + ". v" + version);
        System.out.println("Please Enter \"help\" To See The CommandList\n");
    }

    public abstract void showHelp();

    public void exit() throws ExitException {
        throw new ExitException();
    }

    public static class ExitException extends RuntimeException {
        public ExitException() {
        }

        public ExitException(String message) {
            super(message);
        }
    }
}

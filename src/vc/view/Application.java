package vc.view;

import vc.controller.Controller;
import vc.SearchQuery;
import vc.view.utils.ExecuteComponent;

import java.util.Scanner;
import java.util.function.Function;

public abstract class Application {
    protected Function<String[], SearchQuery> parser;
    protected Controller controller = new Controller();
    protected Scanner scanner = new Scanner(System.in);
    protected ExecuteComponent executeComponent = new ExecuteComponent();
    private String appName, version;
    private String goodByeMessage;

    public Application(String appName, String version, String goodByeMessage) {
        parser = (String[] strings) -> {
          SearchQuery query = new SearchQuery();
          parseInput(strings, query);
          return query;
        };
        this.appName = appName;
        this.version = version;
        this.goodByeMessage = goodByeMessage;
        executeComponent.put("help", (String... strings) -> showHelp());
        executeComponent.put("exit", (String... strings) -> exit());
        start();
    }

    private void start() {
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

    public void exit() throws ExitException {
        throw new ExitException();
    }

    public static class ExitException extends RuntimeException {}

    public abstract void parseInput(String[] inputs, SearchQuery query);

    public abstract void initExecutors();

    public abstract void run();

    public abstract void showHelp();
}

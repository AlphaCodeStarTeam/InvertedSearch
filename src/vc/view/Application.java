package vc.view;

import vc.controller.Controller;
import vc.SearchQuery;

import java.util.Scanner;
import java.util.function.Function;

public abstract class Application {
    private String appName, version;
    Function<String, SearchQuery> parser;
    protected Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);

    public Application(String appName, String version) {
        parser = (String str) -> {
          SearchQuery query = new SearchQuery();
          parseInput(str, query);
          return query;
        };
        this.appName = appName;
        this.version = version;
        sayWelcome();
    }

    public abstract void run();

    public abstract void parseInput(String input, SearchQuery query);

    protected String getUserInput() {
        return scanner.nextLine().trim();
    }

    private void sayWelcome() {
        System.out.println("Welcome To " + appName + ". v" + version);
    }

    public abstract void showHelp();
}

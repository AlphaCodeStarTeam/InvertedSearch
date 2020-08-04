package vc.view;

import vc.controller.Controller;
import vc.SearchQuery;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Executer {
    void execute(String... inputs);
}

public abstract class Application {
    private String appName, version;
    Function<String[], SearchQuery> parser;
    protected Controller controller = new Controller();
    protected Scanner scanner = new Scanner(System.in);
    protected HashMap<String, Executer> executers;

    public Application(String appName, String version) {
        parser = (String[] strings) -> {
          SearchQuery query = new SearchQuery();
          parseInput(strings, query);
          return query;
        };
        this.appName = appName;
        this.version = version;
        executers = new HashMap<>();
        executers.put("help", (String... strings) -> showHelp());
        executers.put("exit", (String... strings) -> exit());
        initExecutors();
        sayWelcome();
    }

    public abstract void initExecutors();

    public abstract void run();

    public abstract void parseInput(String[] inputs, SearchQuery query);

    protected String getUserInput() {
        return scanner.nextLine().trim();
    }

    protected void findExecutor(String input) {
        for (String commandRegex : executers.keySet()) {
            Matcher matcher = getMatcher(commandRegex, input);
            if(matcher.matches()) {
                executers.get(commandRegex).execute(getMatcherGroupsAsArray(matcher));
                return;
            }
        }

        System.err.println("Invalid Command");
    }

    protected String[] getMatcherGroupsAsArray(Matcher matcher) {
        String[] strings = new String[matcher.groupCount()];
        for (int i = 0; i < matcher.groupCount(); i++) {
            strings[i] = matcher.group(i);
        }
        return strings;
    }

    protected Matcher getMatcher(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
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

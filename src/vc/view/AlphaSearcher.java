package vc.view;

import vc.SearchQuery;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaSearcher extends Application {
    private static final String appName = "Alpha-Searcher", version = "2.0";
    private static final String GOODBYE_MESSAGE = "Goodbye From " + appName + " Team";

    public AlphaSearcher() {
        super(appName, version, GOODBYE_MESSAGE);
    }

    @Override
    public void initExecutors() {
        executerComponent.put("^search( \\S+)+$", this::search);
        executerComponent.put("^view (\\S+)$", this::viewDoc);
    }

    private void viewDoc(String... strings) {
        String context = controller.getDoc(strings[0]);
        System.out.println("Context : " + context);
    }

    private void search(String[] strings) {
        HashSet<String> resultSet = controller.executeQuery(parser.apply(strings));
        System.out.println("Result: " + resultSet);
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(appName + "> ");
            executerComponent.findExecutor(getUserInput());
        }
    }

    @Override
    public void parseInput(String[] inputs, SearchQuery query) {
        for (String word : inputs) {
            word = word.trim();
            char starter = word.charAt(0);
            switch (starter) {
                case '+':
                    query.addPos(word.substring(1));
                    break;
                case '-':
                    query.addNeg(word.substring(1));
                    break;
                default:
                    query.addNorm(word);
            }
        }
    }

    @Override
    public void showHelp() {
        System.out.println("Commands :");
        System.out.println("\tsearch $context (#all)");
        System.out.println("\tview $DocID");
        System.out.println("\thelp");
        System.out.println("\texit");
    }
}

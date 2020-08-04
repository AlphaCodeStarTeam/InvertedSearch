package vc.view;

import vc.SearchQuery;

import java.util.HashSet;

public class AlphaSearcher extends Application {
    private static final String appName = "Alpha-Searcher", version = "2.0";

    public AlphaSearcher() {
        super(appName, version);
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(appName + "> ");
            HashSet<String> resultSet = controller.executeQuery(parser.apply(getUserInput()));
            System.out.println("Result: " + resultSet);
        }
    }

    @Override
    public void parseInput(String input, SearchQuery query) {
        for (String word : input.split(" ")) {
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

    }
}

package processor.view;

import processor.SearchQuery;
import processor.view.utils.AlphaPrinter;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;

public class AlphaSearcher extends Application {
    private static final String appName = "Alpha-Searcher", version = "2.0";
    private static final String GOODBYE_MESSAGE = "Goodbye From " + appName + " Team";

    public AlphaSearcher() {
        super(appName, version, GOODBYE_MESSAGE, new AlphaPrinter());
    }

    @Override
    public void parseInput(String[] inputs, SearchQuery query) {
        for (String word : inputs) {
            word = word.trim().toLowerCase();
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
    public void initExecutors() {
        executeComponent.put("^search( \\S+)+$", this::search);
        executeComponent.put("^view (\\S+)( #all)?$", this::viewDoc);
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(appName + "> ");
            executeComponent.findExecutor(getUserInput());
        }
    }

    @Override
    public void showHelp() {
        System.out.println("Commands :");
        System.out.println("\tsearch $context (#all)");
        System.out.println("\tview $DocID (#all)");
        System.out.println("\thelp");
        System.out.println("\texit");
    }

    private void viewDoc(String[] strings) {
        try {
            String context = controller.getDoc(strings[0]);
            System.out.println(strings.length != 1 ? printer.printContext("Doc(" + strings[0] + ")", context) : printer.printModifiedWords("Doc(" + strings[0] + ")", context, 10));
        } catch (FileNotFoundException e) {
            System.out.println("DocID Is Not Valid!");
        }
    }

    private void search(String[] strings) {
        boolean isAll = strings[strings.length - 1].trim().equals("#all");
        if(isAll) {
            strings = Arrays.copyOfRange(strings, 0, strings.length - 1);
        }
        SearchQuery query = parser.apply(strings);
        HashSet<String> resultSet = controller.executeQuery(query);
        String header = "Your Search Includes: \n" +
                "Must Include Words: " + query.getNorms() + "\n" +
                "Include Words: " + query.getPoss() + "\n" +
                "Exclude Words: " + query.getNegs();
        System.out.println(printer.printModifiedLines(header, Arrays.asList(resultSet.toArray(new String[0])), isAll ? resultSet.size() : 3));
    }
}

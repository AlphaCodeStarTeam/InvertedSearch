package processor.view.utils;

import processor.SearchQuery;
import processor.controller.invertedmap.TextSearcher;

import java.util.List;

/**
 * This Class Is For PrettyPrinting Output.
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     StringBuilder
 */

public abstract class PrettyPrinter {
    protected StringBuilder stringBuilder;

    /**
     * This Method Takes Lines With A Header And Prints Them In A Defined Procedure
     *
     * @param header
     * @param lines
     * @param board
     * @return
     */
    public abstract String printModifiedLines(String header, List<String> lines, int board);

    /**
     * This Method Takes Context With A Header And Prints Them In A Defined Procedure
     *
     * @param header
     * @param context
     * @param board
     * @return
     */
    public abstract String printModifiedWords(String header, String context, int board);

    /**
     * This Method Prints Context With Its Header Normally
     *
     * @param header
     * @param context
     * @return
     */
    public String printContext(String header, String context) {
        stringBuilder = new StringBuilder(header + ": \n");
        stringBuilder.append(context);
        return stringBuilder.toString();
    }
}

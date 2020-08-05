package processor.view.utils;

import java.util.List;

public abstract class PrettyPrinter {
    protected StringBuilder stringBuilder;

    public abstract String printModifiedLines(String header, List<String> lines, int board);

    public abstract String printModifiedWords(String header, String context, int board);

    public String printContext(String header, String context) {
        stringBuilder = new StringBuilder(header + ": \n");
        stringBuilder.append(context);
        return stringBuilder.toString();
    }
}

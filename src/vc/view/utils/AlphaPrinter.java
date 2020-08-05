package vc.view.utils;

import java.util.List;

public class AlphaPrinter extends PrettyPrinter {
    @Override
    public String printModifiedLines(String header, List<String> lines, int board) {
        stringBuilder = new StringBuilder(header + "\n\n" + lines.size() + " Case(s) Found As Follows: \n");
        if(lines.size() == 0) {
            stringBuilder.append("No Result Found\n");
            return stringBuilder.toString();
        }
        if(lines.size() <= board + 1) {
            for (int i = 0; i < lines.size(); i++) {
                stringBuilder.append("\tDoc" + (i + 1) + ": " + lines.get(i) + "\n");
            }
        } else {
            for (int i = 0; i < board; i++) {
                stringBuilder.append("\tDoc" + (i + 1) + ": " + lines.get(i) + "\n");
            }
            stringBuilder.append("\t\t.\n\t\t.\n\t\t.\n");
            stringBuilder.append("\tDoc" + lines.size() + ": " + lines.get(lines.size() - 1) + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String printModifiedWords(String header, String context, int board) {
        stringBuilder = new StringBuilder(header + " Summery: \n");
        for (int i = 0; i < context.toCharArray().length; i++) {
            if(context.charAt(i) == ' ') {
                board--;
                if(board == 0)
                    break;
                stringBuilder.append(" ");
                for (; i < context.toCharArray().length && context.charAt(i) == ' '; i++);
            }
            stringBuilder.append(context.charAt(i));
        }
        stringBuilder.append("...");
        return stringBuilder.toString();
    }
}

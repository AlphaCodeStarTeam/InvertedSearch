package processor.view.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AlphaPrinterTest {
    private AlphaPrinter alphaPrinter = new AlphaPrinter();

    @Test
    public void printModifiedLinesTest() {
        String header = "**** You UnitTest";
        List<String> list = Arrays.asList("**** YOU UnitTest Creator",
                "**** YOU UnitTest Developer",
                "**** YOU UnitTest Idea Bringer",
                "**** YOU UnitTest Tester. TellMe, Huh, TellMe. How The **** Did You Test The ****ing UnitTest????????");
        int board = 2;
        String expected = "**** You UnitTest\n" +
                "\n" +
                "4 Case(s) Found As Follows: \n" +
                "\tDoc1: **** YOU UnitTest Creator\n" +
                "\tDoc2: **** YOU UnitTest Developer\n" +
                "\t\t.\n" +
                "\t\t.\n" +
                "\t\t.\n" +
                "\tDoc4: **** YOU UnitTest Tester. TellMe, Huh, TellMe. How The **** Did You Test The ****ing UnitTest????????\n";
        Assert.assertEquals(expected, alphaPrinter.printModifiedLines(header, list, board));

        board = 5;
        expected = "**** You UnitTest\n" +
                "\n" +
                "4 Case(s) Found As Follows: \n" +
                "\tDoc1: **** YOU UnitTest Creator\n" +
                "\tDoc2: **** YOU UnitTest Developer\n" +
                "\tDoc3: **** YOU UnitTest Idea Bringer\n" +
                "\tDoc4: **** YOU UnitTest Tester. TellMe, Huh, TellMe. How The **** Did You Test The ****ing UnitTest????????\n";
        Assert.assertEquals(expected, alphaPrinter.printModifiedLines(header, list, board));

        list = Arrays.asList();
        expected = "**** You UnitTest\n" +
                "\n" +
                "0 Case(s) Found As Follows: \n" +
                "No Result Found\n";
        Assert.assertEquals(expected, alphaPrinter.printModifiedLines(header, list, board));

//        System.out.println("output : \"" + alphaPrinter.printModifiedLines(header, list, board) + "\"");
    }

    @Test
    public void printContextTest() {
        String header = "**** You KentBeck";
        String context = "Kent Beck (born 1961) is an American software engineer and the creator of extreme programming,[1] a software development methodology that eschews rigid formal specification for a collaborative and iterative design process. Beck was one of the 17 original signatories of the Agile Manifesto,[1] the founding document for agile software development. Extreme and Agile methods are closely associated with Test-Driven Development (TDD), of which Beck is perhaps the leading proponent.\n" +
                "\n" +
                "Beck pioneered software design patterns, as well as the commercial application of Smalltalk. He wrote the SUnit unit testing framework for Smalltalk, which spawned the xUnit series of frameworks, notably JUnit for Java, which Beck wrote with Erich Gamma. Beck popularized CRC cards with Ward Cunningham, the inventor of the wiki.\n" +
                "\n" +
                "He lives in San Francisco, California and worked at social media company Facebook.[2] Kent has worked at Gusto since April 2019.";
        int board = 10;
        String expected = "**** You KentBeck Summery: \n" +
                "Kent Beck (born 1961) is an American software engineer and...";
        Assert.assertEquals(expected, alphaPrinter.printModifiedWords(header, context, board));
        expected = "**** You KentBeck: \n" +
                "Kent Beck (born 1961) is an American software engineer and the creator of extreme programming,[1] a software development methodology that eschews rigid formal specification for a collaborative and iterative design process. Beck was one of the 17 original signatories of the Agile Manifesto,[1] the founding document for agile software development. Extreme and Agile methods are closely associated with Test-Driven Development (TDD), of which Beck is perhaps the leading proponent.\n" +
                "\n" +
                "Beck pioneered software design patterns, as well as the commercial application of Smalltalk. He wrote the SUnit unit testing framework for Smalltalk, which spawned the xUnit series of frameworks, notably JUnit for Java, which Beck wrote with Erich Gamma. Beck popularized CRC cards with Ward Cunningham, the inventor of the wiki.\n" +
                "\n" +
                "He lives in San Francisco, California and worked at social media company Facebook.[2] Kent has worked at Gusto since April 2019.";
        Assert.assertEquals(expected, alphaPrinter.printContext(header, context));
    }

}

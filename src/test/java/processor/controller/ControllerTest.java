package processor.controller;

import model.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processor.SearchQuery;
import processor.controller.invertedmap.TextSearcher;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerTest {
    Controller controller;
    SearchQuery query;


    public void initController() {
        controller = new Controller();
        query = new SearchQuery();
        query.addNorms("hello", "is", "she", "there", "verify");
        query.addPoss("sususu", "verify");
        query.addNegs("death", "love");
    }

    @Test
    public void searchQueryTest() {
        initController();
        String[] result = controller.executeQuery(query).toArray(new String[0]);
        String[] expected = new String[]{"58108", "58966", "59315"};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void fileInterrupt() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FileReader.setDocFolderPath("Mi Pan Su Su Sum");
        TextSearcher textSearcher = new TextSearcher();
        Method method = TextSearcher.class.getDeclaredMethod("addDocToMap", String.class);
        method.setAccessible(true);
        method.invoke(textSearcher, "yum yum yum");
        FileReader.setDocFolderPath("Documents\\");
    }

    @Test
    public void getDocTes() throws FileNotFoundException {
        initController();
        String docID = "57110";
        String expected = "I have a 42 yr old male friend, misdiagnosed as havin osteopporosis for two years, who recently found out that hi illness is the rare Gaucher's disease.Gaucher's disease symptoms include: brittle bones (he lost 9 inches off his hieght); enlarged liver and spleen; interna bleeding; and fatigue (all the time). The problem (in Type 1) i attributed to a genetic mutation where there is a lack of th enzyme glucocerebroside in macrophages so the cells swell up This will eventually cause deathEnyzme replacement therapy has been successfully developed an approved by the FDA in the last few years so that those patient administered with this drug (called Ceredase) report a remarkabl improvement in their condition. Ceredase, which is manufacture by biotech biggy company--Genzyme--costs the patient $380,00 per year. Gaucher\\'s disease has justifyably been called \"the mos expensive disease in the world\"NEED INFOI have researched Gaucher's disease at the library but am relyin on netlanders to provide me with any additional information**news, stories, report**people you know with this diseas**ideas, articles about Genzyme Corp, how to get a hold o   enough money to buy some, programs available to help wit   costs**Basically ANY HELP YOU CAN OFFEThanks so very muchDeborah\n";
        Assertions.assertEquals(expected, controller.getDoc(docID));
    }
}

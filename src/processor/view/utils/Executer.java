package processor.view.utils;


/**
 * This Interface Is For Storing Executing Methods Of An Application.
 *
 * @author  Sepehr Kianian
 * @author  Ashkan Khademian
 * @see     FunctionalInterface
 * @see     processor.view.Application
 */

@FunctionalInterface
public interface Executer {
    void execute(String... inputs);
}

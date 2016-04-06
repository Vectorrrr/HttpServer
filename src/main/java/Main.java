import loader.PageLoader;
import loader.PropertyLoader;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */

public class Main {
    public static void main(String[] args){
        System.out.println( PropertyLoader.property("page.found"));
        System.out.println(PageLoader.getPage("rootPage.txt"));
    }
}

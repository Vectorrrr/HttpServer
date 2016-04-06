package loader;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Gladush Ivan
 * @since 06.04.16.
 */
//todo talk about make this class a page holder in future and discouse all situation
public class PageLoader {
    private static final Logger log=Logger.getLogger(PageLoader.class);
    public static String getPage(String pageName) {
        StringBuilder sb = new StringBuilder();
        System.out.println(ClassLoader.getSystemResourceAsStream(pageName));
        try (BufferedReader br = new BufferedReader(new FileReader(ClassLoader.getSystemResource(pageName).getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error(String.format("I can't download page %s because %s", pageName, e.getMessage()));
        }
        return sb.toString();
    }
}

package holder;

import loader.PageLoader;
import model.WebPage;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The class contains a URL of the page and
 * its contents, when referring to a class with
 * a request to obtain the page, the class checks its
 * existence and presence in it updated. If the page is
 * not there or it downloads updates for its new, otherwise
 * it returns the current version
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class PageHolder {
    private static final Logger log = Logger.getLogger(PageHolder.class);

    /**
     * MAP contains key-value pairs where
     * the key serves the page URL, as the
     * value of its contents
     */
    private static Map<String, WebPage> pages = new HashMap<>();

    public static String getPage(String url) {
        WebPage webPage = pages.get(url);
        System.out.println("Page lastModifiedFIle: "+PageLoader.lastModifiedFile(url)+" Web page last ChangesDate " );
        if (webPage != null && webPage.lastChangesDate() >= PageLoader.lastModifiedFile(url)) {
            return pages.get(url).pageContent();
        }
        log.info(String.format(UPDATE_PAGE_INFORMATION, url));
        pages.put(url, new WebPage(PageLoader.getPage(url)));
        return pages.get(url).pageContent();
    }

    private static final String UPDATE_PAGE_INFORMATION = "Updated information on the page with the URL %s";
}

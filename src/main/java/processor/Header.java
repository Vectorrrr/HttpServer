package processor;

import static loader.PropertyLoader.property;

/**
 * Enum contains components of the response header
 * @author Gladush Ivan
 * @since 06.04.16.
 */
//todo make property
public enum Header {
    PAGE_FOUND(property("page.found")),
    REDIRECT_TO_ROOT_LOCATION(property("redirect.to.root.location")),
    REDIRECT_TO_MAIN_LOCATION(property("redirect.to.main.location")),
    HTTP_OK(property("http.ok")),
    SET_COOKIE(property("set.cookie")),
    CONTENT_LENGTH(property("content.length")),
    CLOSE_CONNECTION(property("close.connection")),
    HTTP_NOT_FOUND(property("http.not.found"));

    Header(String header) {
        this.header = header;
    }

    private String header;

    @Override
    public String toString() {
        return header;
    }
}

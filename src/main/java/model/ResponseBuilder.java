package model;

/**
 * @author Gladush Ivan
 * @since 06.04.16.
 */
public class ResponseBuilder {
    private static final String NEW_LINE = "\n\r";
    private StringBuilder header = new StringBuilder();
    private StringBuilder body = new StringBuilder();

    public ResponseBuilder addHeader(String header) {
        this.header.append(header).append(NEW_LINE);
        return this;
    }

    public ResponseBuilder addBody(String body) {
        this.body.append(body);
        return this;
    }

    public String build() {
        return header.append(NEW_LINE).append(body).toString();
    }
}

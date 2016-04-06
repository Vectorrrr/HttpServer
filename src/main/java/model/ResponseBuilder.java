package model;

import processor.Header;

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
    public ResponseBuilder addHeader(Header header){
        return addHeader(header.toString());
    }
    public ResponseBuilder addHeader(Header header,int i){
        return addHeader(String.format(header.toString(),i));
    }
    public ResponseBuilder addBody(String body) {
        this.body.append(body);
        return this;
    }

    public String build() {
        System.out.println(header);
        return header.append(NEW_LINE).append(body).toString();
    }
}

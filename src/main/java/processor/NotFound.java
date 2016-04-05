package processor;

import model.Request;

/**
 * @author Gladush Ivan
 * @since 05.04.16.
 */
public class NotFound  implements  PageProcessor{
    private String header="HTTP/1.1 404 NotFound\n"+
            "Set-Cookie:session=-1;";

    @Override
    public String doRequest(Request request) {
        return header;
    }
}

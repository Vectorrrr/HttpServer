package processor;

import model.Request;
import model.ResponseBuilder;
import static processor.Header.*;
/**
 * Home page on which users enter at
 * the first entrance to the site
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class RootPageProcess implements PageProcessor {
   private  String body = "<html>\n" +
           "<head><title>Root Page</title></head>\n" +
           "<body>\n" +
           "\n" +
           "<h2>Welcome. I think your name is Roman!!!</h2>\n" +
           "<h2>If you want to log in, click here<a href=\"http://localhost:8080/Login\">Login in</a></h2>\n" +
           "\n" +
           "</body>\n" +
           "</html>";

    public RootPageProcess(){}
    @Override
    public String doRequest(Request request) {
        return new ResponseBuilder().addHeader(HTTP_OK).addHeader(CONTENT_LENGTH,body.length()).
                addHeader(SET_COOKIE,-1).addHeader(CLOSE_CONNECTION).addBody(body).build();
    }
}

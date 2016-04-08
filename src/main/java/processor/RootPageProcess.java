package processor;

import holder.PageHolder;
import loader.PropertyLoader;
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
   private  String body = PageHolder.getPage(PropertyLoader.property("root.page"));

    public RootPageProcess(){}
    @Override
    public String doRequest(Request request) {
        return new ResponseBuilder().addHeader(HTTP_OK).addHeader(CONTENT_LENGTH,body.length()).
                addHeader(SET_COOKIE,-1).addHeader(CLOSE_CONNECTION).addBody(body).build();
    }
}

package processor;

import model.Request;
import server.session.SessionHolder;

/**
 * Home page of the site that allows the user to log out
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class MainProcessor implements PageProcessor {
    private String body="<html>\n" +
            "<head><title>Main Page</title></head>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Welcome %s. In the main form!</h2>\n" +
            "<h2>If you want to see your name, click here<a href=\"http://localhost:8080/Login\">Your name</a></h2>\n" +
            "<h2>If you want exit click this link <a href=\"http://localhost:8080/Exit\">Exit</a></h2>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
    private String authoraizeHeader="HTTP/1.1 200 OK\r\n" +
            "Server: VanyaServer/2016\r\n" +
            "Content-Type: text/html\r\n" +
            "Set-Cookie:session=%d;\r\n"+
            "Content-Length:  %d \r\n" +
            "Connection: close\r\n\r\n %s";

    private String redirectHeader="HTTP/1.1 302 Found\n" +
            "Location: http://localhost:8080";

    @Override
    public String doRequest(Request request) {
        int id=request.getSessionId();
        if  (SessionHolder.containsSession(id)) {
            String bodyResponce= String.format(body,request.getUserInformation());
            return String.format(authoraizeHeader,id,  body.length(),bodyResponce);
        }
            return String.format(redirectHeader);
    }
}

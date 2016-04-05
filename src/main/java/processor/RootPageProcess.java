package processor;

import model.Request;

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
           "<h2>If you want to log in, click here<a href=\"http://localhost:8080/Login\">Visit W3Schools.com!</a></h2>\n" +
           "\n" +
           "</body>\n" +
           "</html>";

    private String header="HTTP/1.1 200 OK\r\n" +
            "Server: VanyaServer/2016\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length:  %d \r\n" +
            "Set-Cookie: session=%d;\r\n"+
            "Connection: close\r\n\r\n %s";
    public RootPageProcess(){}
    @Override
    public String doRequest(Request request) {
        System.out.println("Request get session id:" + request.getSessionId());
        return String.format(header, body.length(), request.getSessionId(),body);
    }
}

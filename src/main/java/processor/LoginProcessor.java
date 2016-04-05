package processor;

import model.Request;
import model.TypeRequest;
import model.User;
import server.session.SessionHolder;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class LoginProcessor implements  PageProcessor{
    private String body="<html>\n" +
            "<head><title>Login Page</title></head>\n" +
            "<body>\n" +
            "\n" +
            "<form method=\"post\"><h2>Enter your information</h2>\n" +
            "    <p>Name:<input type=\"text\" name=\"name\" required autocomplete=\"off\"></p>\n" +
            "    <p>Surname: <input type=\"text\" name=\"surname\" required autocomplete=\"off\"></p>\n" +
            "    <input type=\"submit\" value=\"Submit\">\n" +
            "</form>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    private String anonimUserGet ="HTTP/1.1 200 OK\r\n" +
            "Server: VanyaServer/2016\r\n" +
            "Content-Type: text/html\r\n" +
            "Set-Cookie: session=-1\r\n"+
            "Content-Length:  %d \r\n" +
            "Connection: close\r\n\r\n %s";



    private String headerPost="HTTP/1.1 302 Found\r\n" +
            "Location: http://localhost:8080/Main\r\n"+
            "Set-Cookie: session=%d;";

    @Override
    public String doRequest(Request request) {
        if (TypeRequest.GET.equals(request.typeRequest())) {
            return doGet(request);
        }
        return doPost(request);
    }

    private String doGet(Request request) {
        int sesId=request.getSessionId();
        if(SessionHolder.containsSession(sesId)){
            return String.format(headerPost,sesId);
        }

        return String.format(anonimUserGet,body.length(),body);
    }

    private String doPost(Request request) {
        User user = createUser(request);
        int id=SessionHolder.addSession(user);
        return String.format(headerPost,id);
    }

    private User createUser(Request request) {
        return new User(request.getParameter("name"),request.getParameter("surname"));
    }

}

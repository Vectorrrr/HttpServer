package model;

import processor.RequestProcessor;
import server.session.Session;
import server.session.SessionHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class Request {
    private String header;
    private String url;
    private TypeRequest typeRequest;
    private Map<String, String> parameters = new HashMap<>();
    private Session session;

    public Request(TypeRequest typeRequest, String header) {
        System.out.println(header);
        this.typeRequest = typeRequest;
        this.header = header;
        this.url = RequestProcessor.getURL(header);
        this.session=initSession(header);
    }

    private Session initSession(String header) {
        return SessionHolder.getSession(header);
    }

    public void addValue(String key, String value) {
        parameters.put(key, value);
    }

    public String getParameter(String key){
        return parameters.get(key);
    }

    public TypeRequest typeRequest() {
        return typeRequest;
    }

    public String getUrl() {
        return this.url;
    }

    public int getSessionId() {
        return session.getId();
    }

    public String getUserInformation(){
        return session.userName()+" "+session.userSurname();
    }

    @Override
    public String toString() {
        return parameters.toString();
    }

}

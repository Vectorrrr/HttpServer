package model;

import org.apache.log4j.Logger;
import processor.RequestProcessor;
import holder.session.Session;
import holder.session.SessionHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that holds data about
 * the request URL, query type, as well as options
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class Request {
    private static final Logger log= Logger.getLogger(Request.class);
    private String url;
    private TypeRequest typeRequest;
    private Map<String, String> parameters = new HashMap<>();
    private Session session;

    public Request(TypeRequest typeRequest, String header) {
        log.info("Header request "+header);
        this.typeRequest = typeRequest;
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

}

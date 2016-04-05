package processor;

import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.TypeRequest;

/**
 * The class allows you to receive a
 * variety of information from the request header
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class RequestProcessor {
    private static final Logger log= Logger.getLogger(RequestProcessor.class);
    private static final String REGEX_FOR_SEARCH_TYPE_REQUEST="([A-Z]+(?=\\s))";
    private static final String REGEX_FOR_GET_URL="((?<= )/(.*)(?= HTTP))";
    private static final String ROOT_PAGE_URL = "/";
    private static final String REGEX_FOR_SESSION_FROM_COOKIE ="((?<=session=)((-){0,1}[0-9]+))";
    private static final String EMPTY_STRING = "";

    public static TypeRequest getTypeRequest(String request){
        Matcher matcher = Pattern.compile(REGEX_FOR_SEARCH_TYPE_REQUEST).matcher(request);
        if(matcher.find()){
            return convertToTypeRequst(matcher.group(1));
        }
        log.warn(CANT_FIND_REQUEST);
        return TypeRequest.UNKNOWN;
    }

    private static TypeRequest convertToTypeRequst(String nameType) {
        for(TypeRequest tr:TypeRequest.values()){
            if(tr.typeRequest().equals(nameType)){
                return tr;
            }
        }
        return TypeRequest.UNKNOWN;
    }

    public static String getURL(String request){
        Matcher matcher = Pattern.compile(REGEX_FOR_GET_URL).matcher(request);
        if(matcher.find()){
            return matcher.group(1);
        }
        log.warn(DONT_FIND_URL);
        return ROOT_PAGE_URL;

    }
    public static String getSessionFromCookies(String reqest){
        Matcher matcher= Pattern.compile(REGEX_FOR_SESSION_FROM_COOKIE).matcher(reqest);
        if(matcher.find()){
            return matcher.group(1);
        }
        return EMPTY_STRING;
    }
    private static final String CANT_FIND_REQUEST = "I can't find a request type. Check correct you regexp. I return a unknown type";
    private static final String DONT_FIND_URL = "URL doesn't contains url. Method return a root page URL";
}

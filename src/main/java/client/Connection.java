package client;

import model.Request;
import model.TypeRequest;
import org.apache.log4j.Logger;
import processor.RequestProcessor;
import server.session.SessionHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class Connection  implements AutoCloseable {
    private static final Logger log = Logger.getLogger(Connection.class);
    public static final String EXCEPTION_READ_SOCKET = "I can't read socket, because %s %s";
    public static final String EMPTY_STRING = "";
    public static final String INCORRECT_PARAMETER_IN_CONTENT = "Incorrect parameter in content %s";
    public static final String CANT_SEND_RESPONSE = "Can't send response client because %s";
    private static final String CONTENT_HEADER = "Content-Length: ";

    private SessionHolder sessionHolder;
    private Socket socket;
    private Request request = null;

    private BufferedReader reader;
    private OutputStream writer;

    public Connection(Socket socket) {
        this.socket = socket;
        this.sessionHolder = sessionHolder;
    }

    public Request getRequest() {
        try {
            if (request == null) {
                createReader();
                parseContentRequest(readContent(readHeader()));
            }
        } catch (IOException e) {
            log.error(String.format(EXCEPTION_READ_SOCKET, e.getMessage()));
            throw new IllegalStateException(String.format(EXCEPTION_READ_SOCKET, e.getMessage(), MANUAL_DECIDE_PROBLEM));
        }
        return request;
    }

    /**
     * returns whether a response has been sent or not
     */
    public boolean writeResponse(String s) {
        System.out.println(s);
        try {
            createWriter();
            writer.write(s.getBytes());
        } catch (IOException e) {
            log.error(String.format(CANT_SEND_RESPONSE, e.getMessage()));
            return false;
        }
        return true;
    }

    private void createWriter() throws IOException {
        if (writer == null) {
            writer = socket.getOutputStream();
        }
    }

    /**
     * Method parse content and add this content in request
     */
    private void parseContentRequest(String content) {
        for (String parameter : content.split("&")) {
            String[] keyValue = parameter.split("=");
            if (keyValue.length != 2) {
                log.warn(String.format(INCORRECT_PARAMETER_IN_CONTENT, parameter));
            } else {
                request.addValue(keyValue[0], keyValue[1]);
            }
        }
    }

    private String readContent(int contentLength) throws IOException {
        StringBuilder body = new StringBuilder(EMPTY_STRING);
        for (int i = 0; i < contentLength; i++) {
            body.append((char) reader.read());
        }
        return body.toString();
    }

    /**
     * Read header request and init the Request without parameter
     * return a content length from header
     */
    private int readHeader() throws IOException {
        StringBuilder head = new StringBuilder();
        String line;
        line = reader.readLine();
        TypeRequest type = RequestProcessor.getTypeRequest(line);
        int contentLength = 0;
        do {
            head.append('\n' + line);
            if (TypeRequest.POST.equals(type) &&
                line.startsWith(CONTENT_HEADER)) {
                contentLength = Integer.parseInt(line.split(" ")[1]);
            }
        } while (!EMPTY_STRING.equals(line = reader.readLine()));

        request = new Request(type, head.toString());
        return contentLength;
    }

    private void createReader() throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }


    public static final String MANUAL_DECIDE_PROBLEM = "The error due to the fact that the socket can" +
            "not be used to read the data. Check the premature closure of the socket, or that " +
            "your application does not close the socket. Perhaps the client is disconnected.";


}

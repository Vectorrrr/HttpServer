package client;

import model.Request;
import org.apache.log4j.Logger;
import processor.PageProcessor;
import holder.ProcessorHolder;

/**
 * This class models a processing
 * request to the server
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class Client implements Runnable {
    private final Logger log = Logger.getLogger(Client.class);
    private static final String INCORRECT_CLOSE = "Incorrect close connection. May be client close socket in browser %s";

    private Connection connection;
    private ProcessorHolder processorHolder;

    public Client(Connection connection, ProcessorHolder processorHolder) {
        log.info("New client");
        this.connection = connection;
        this.processorHolder = processorHolder;
    }

    @Override
    public void run() {
        try {
            Request request = connection.getRequest();
            PageProcessor pageProcessor = processorHolder.getProcessor(request.getUrl());
            connection.writeResponse(pageProcessor.doRequest(request));
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(String.format(INCORRECT_CLOSE, e.getMessage()));
        }
    }
}

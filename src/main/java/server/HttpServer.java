package server;

import client.Client;
import client.Connection;
import org.apache.log4j.Logger;
import utils.CheckerCorrectScheme;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Gladush Ivan
 * @since 28.03.16.
 */
public class HttpServer implements  Runnable {
    private static final Logger log = Logger.getLogger(HttpServer.class);
    public static final String EXCEPTION_WHEN_SERVER_WORK = "Exception when sever work. Server stopped. %s";
    private static final Executor executor = Executors.newFixedThreadPool(10);
    private ProcessorHolder processorHolder;
    private int port;

    public HttpServer(ProcessorHolder processorHolder, int port) {
        this.processorHolder = processorHolder;
        this.port = port;
    }


    public static void main(String[] args) {
        new Thread(new HttpServer(initProcessorHolder(args), 8080)).start();
    }

    private static String getFileName(String[] args) {
        String pathToFile;
        switch (args.length) {
            case 0:
                pathToFile = ClassLoader.getSystemResource("configuration.xml").getFile();
                break;
            case 1:
                pathToFile = ClassLoader.getSystemResource(args[0]).getFile();
                break;
            default:
                pathToFile = ClassLoader.getSystemResource("configuration.xml").getFile();
                log.error(INCORRECT_START);
        }
        return pathToFile;
    }

    private static ProcessorHolder initProcessorHolder(String[] args) {
        String pathToScheme = ClassLoader.getSystemResource("configurationSchem.xsd").getFile();
        String pathToFile = getFileName(args);
        if (!CheckerCorrectScheme.isCorrectFile(pathToFile, pathToScheme)) {
            log.error(INCORRECT_START);
        }

        return ProcessorHolder.initProcessHolder(pathToFile);
    }


    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket client = serverSocket.accept();
                executor.execute(new Client(new Connection(client), processorHolder));
                log.info(ADD_CLIENT);
            }
        } catch (Exception e) {
            log.error(String.format(EXCEPTION_WHEN_SERVER_WORK, e.getMessage()));
            return;
        }
    }


    private static final String ADD_CLIENT = "Add new client socket";
    private static final String INCORRECT_START = "Incorrect file to work.The server was not running." +
            " Check that your schema " +
            "file or check the availability of the scheme";


}

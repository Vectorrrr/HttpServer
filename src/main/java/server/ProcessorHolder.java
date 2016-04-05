package server;

import model.Bean;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import processor.NotFound;
import processor.PageProcessor;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.*;


/**
 * @author Gladush Ivan
 * @since 29.03.16.
 */
public class ProcessorHolder {
    private static final String ROOT_PAGES = "/";
    private static final Logger log = Logger.getLogger(ProcessorHolder.class);
    public static final String EXCEPTION_CREATE_PROCESSOR_HOLDER = "I can't create file %s";
    public static final String EXCEPTION_CREATE_FILE = "I can't create new instance of class. Check exist it file. %s";
    private List<Bean> processors = new ArrayList<>();

    /**
     * It contains a URL and path processors who process this URL
     */
    private ProcessorHolder(File f) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler = new SaxHandler();
        parser.parse(f, handler);
    }

    /**
     * Scheme must comply with the scheme for the
     * application, in the case of incorrect circuit
     * correctness return result not possible to
     * guarantee
     */
    public static ProcessorHolder initProcessHolder(String pathToXml) {
        try {
            return new ProcessorHolder(new File(pathToXml));
        } catch (Exception e) {
            log.error(String.format(EXCEPTION_CREATE_PROCESSOR_HOLDER, e.getMessage()));
            throw new IllegalArgumentException(String.format(EXCEPTION_CREATE_PROCESSOR_HOLDER, e.getMessage()));
        }
    }

    public PageProcessor getProcessor(String processor) {
        for (Bean pr : processors) {
            if (pr.getUrl().equals(processor)) {
                try {
                    System.out.println(1);
                    return (PageProcessor) Class.forName(pr.getClassPath()).newInstance();
                } catch (Exception e) {
                    log.error(String.format(EXCEPTION_CREATE_FILE, e.getMessage()));
                    throw new IllegalArgumentException(String.format(EXCEPTION_CREATE_FILE, e.getMessage()));
                }
            }
        }
        return new NotFound();
    }

    private final class SaxHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attrs) {
            if ("bean".equals(qName)) {
                processors.add(new Bean(attrs.getValue("url"), attrs.getValue("classPath")));
            }
        }

    }
}

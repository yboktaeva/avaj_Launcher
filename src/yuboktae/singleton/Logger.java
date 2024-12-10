package yuboktae.singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logger {
    private static Logger logger;
    private PrintWriter writer;

    private Logger(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        this.writer = new PrintWriter(fw);
    }

    public static Logger getLogger(String filename) {
        if (logger == null) {
            try {
                logger = new Logger(filename);
            } catch (IOException e) {
            }
        }
        return logger;
    }

    public void log(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
            writer = null;
            logger = null;
        }
    }
}

package kiosk;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public static Logger logger;
    private static FileHandler fileHandler;

    /**
     * initialize the logger, create log file if not exist.
     * @param fileName Name and path of the log file.
     * @throws IOException
     */
    public static void initLog(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        fileHandler = new FileHandler(fileName, false);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
    }
}

package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerClass {

    private static final Logger LOGGER =Logger.getLogger("IceaLogger"); //crea el logger

    static {
        try {
            FileHandler fh = new FileHandler("icea.log", true); //donde se guardan
            fh.setFormatter(new SimpleFormatter()); //formATO legible

            LOGGER.addHandler(fh); //guardar los archivos dentro del logger
            LOGGER.setUseParentHandlers(false); //para que no salgann por consola

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() { //para coger el logegr desde cualquier clase
        return LOGGER;
    }
}
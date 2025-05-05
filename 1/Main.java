import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        System.out.println("logger1 e logger2 são a mesma instância? " + (logger1 == logger2));
        
        logger1.log("Esta é uma mensagem de log do logger1");
        logger2.log("Esta é uma mensagem de log do logger2");
        
        System.out.println("Logs gravados com sucesso! Verifique o arquivo log.txt");
    }
    
    static class Logger {
        private static Logger instance;
        
        private static final String LOG_FILE = "log.txt";
        
        private Logger() {
            try {
                FileWriter fw = new FileWriter(LOG_FILE, false);
                fw.write("=== LOG INICIADO EM " + getCurrentDateTime() + " ===\n");
                fw.close();
                System.out.println("Logger inicializado.");
            } catch (IOException e) {
                System.err.println("Erro ao inicializar o Logger: " + e.getMessage());
            }
        }
        
        public static synchronized Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }
        
        public void log(String message) {
            try {
                FileWriter fw = new FileWriter(LOG_FILE, true);
                fw.write("[" + getCurrentDateTime() + "] " + message + "\n");
                fw.close();
            } catch (IOException e) {
                System.err.println("Erro ao escrever no log: " + e.getMessage());
            }
        }
        
        private String getCurrentDateTime() {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return now.format(formatter);
        }
    }
}
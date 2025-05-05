import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Gerenciador de Configuração Singleton ===");
        
        System.out.println("\n-- Parte 1 do programa (ex: Tela de Login) --");
        Config config1 = Config.getInstance();
        config1.setConfig("idioma", "pt-BR");
        config1.setConfig("modoEscuro", "false");
        config1.setConfig("volume", "75");
        
        mostrarConfiguracoes(config1);
        
        System.out.println("\n-- Parte 2 do programa (ex: Tela de Configurações) --");
        Config config2 = Config.getInstance();
        
        System.out.println("config1 e config2 são a mesma instância? " + (config1 == config2));
        
        config2.setConfig("idioma", "en-US");
        config2.setConfig("modoEscuro", "true");
        
        mostrarConfiguracoes(config2);
        
        System.out.println("\n-- Parte 3 do programa (ex: Reprodutor de Mídia) --");
        Config config3 = Config.getInstance();
        
        config3.setConfig("volume", "50");
        
        mostrarConfiguracoes(config3);
    }
    
    private static void mostrarConfiguracoes(Config config) {
        System.out.println("Configurações atuais:");
        System.out.println("- Idioma: " + config.getConfig("idioma"));
        System.out.println("- Modo Escuro: " + config.getConfig("modoEscuro"));
        System.out.println("- Volume: " + config.getConfig("volume"));
    }
    
    static class Config {
        private static Config instance;
        
        private Map<String, String> configuracoes;
        
        private Config() {
            System.out.println("Inicializando o Gerenciador de Configuração");
            configuracoes = new HashMap<>();
        }
        
        public static synchronized Config getInstance() {
            if (instance == null) {
                instance = new Config();
            }
            return instance;
        }
        
        public void setConfig(String chave, String valor) {
            configuracoes.put(chave, valor);
            System.out.println("Configuração atualizada: " + chave + " = " + valor);
        }
        
        public String getConfig(String chave) {
            return configuracoes.getOrDefault(chave, "");
        }
    }
}
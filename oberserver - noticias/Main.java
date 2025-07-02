import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String noticia);
}

class CanalDeNoticias {
    private List<Observer> observadores;
    private String ultimaNoticia;
    
    public CanalDeNoticias() {
        this.observadores = new ArrayList<>();
    }
    
    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
        System.out.println("✓ Observador adicionado ao canal");
    }
    
    public void removerObservador(Observer observador) {
        observadores.remove(observador);
        System.out.println("✗ Observador removido do canal");
    }
    
    private void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.update(ultimaNoticia);
        }
    }
    
    public void publicarNoticia(String noticia) {
        System.out.println("\n📢 NOVA NOTÍCIA PUBLICADA: " + noticia);
        System.out.println("----------------------------------------");
        this.ultimaNoticia = noticia;
        notificarObservadores();
    }
}
class UsuarioApp implements Observer {
    private String nome;
    
    public UsuarioApp(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void update(String noticia) {
        System.out.println("📱 [APP - " + nome + "] Notificação push recebida: " + noticia);
    }
}
class UsuarioEmail implements Observer {
    private String nome;
    private String email;
    
    public UsuarioEmail(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    @Override
    public void update(String noticia) {
        System.out.println("📧 [EMAIL - " + nome + "] Enviando para " + email + ": " + noticia);
    }
}
public class Main {
    public static void main(String[] args) {
        CanalDeNoticias canalEsportes = new CanalDeNoticias();
        
        System.out.println("=== SISTEMA DE NOTÍCIAS - PADRÃO OBSERVER ===\n");
        
        UsuarioApp usuario1 = new UsuarioApp("João");
        UsuarioApp usuario2 = new UsuarioApp("Maria");
        UsuarioEmail usuario3 = new UsuarioEmail("Pedro", "pedro@email.com");
        UsuarioEmail usuario4 = new UsuarioEmail("Ana", "ana@email.com");
        
        System.out.println("1. Adicionando observadores:");
        canalEsportes.adicionarObservador(usuario1);
        canalEsportes.adicionarObservador(usuario2);
        canalEsportes.adicionarObservador(usuario3);
        canalEsportes.adicionarObservador(usuario4);
        
        canalEsportes.publicarNoticia("Brasil vence a Argentina por 3x1!");
        
        System.out.println("\n2. Removendo Maria do canal:");
        canalEsportes.removerObservador(usuario2);
        
        canalEsportes.publicarNoticia("Neymar marca gol histórico na Champions League!");
        
        System.out.println("\n3. Adicionando novo observador:");
        UsuarioApp usuario5 = new UsuarioApp("Carlos");
        canalEsportes.adicionarObservador(usuario5);
        
        canalEsportes.publicarNoticia("Copa do Mundo 2026: Brasil é favorito nas apostas!");
        
        System.out.println("\n4. Criando novo canal de tecnologia:");
        CanalDeNoticias canalTech = new CanalDeNoticias();
        canalTech.adicionarObservador(usuario1);
        canalTech.adicionarObservador(usuario3);
        
        canalTech.publicarNoticia("Nova versão do Java 23 é lançada com recursos revolucionários!");
    }
}
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


interface Observer {
    void update(String mensagem, String remetente);
    String getNome();
}

class Mensagem {
    private String conteudo;
    private String remetente;
    private LocalDateTime horario;
    
    public Mensagem(String conteudo, String remetente) {
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.horario = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("[%s] %s: %s", 
            horario.format(formatter), remetente, conteudo);
    }
}

class ChatRoom {
    private String nome;
    private List<Observer> usuarios;
    private List<Mensagem> historico;
    
    public ChatRoom(String nome) {
        this.nome = nome;
        this.usuarios = new ArrayList<>();
        this.historico = new ArrayList<>();
        System.out.println("💬 Sala de chat '" + nome + "' criada!");
    }
    

    public void registrarUsuario(Observer usuario) {
        usuarios.add(usuario);
        System.out.println("✅ " + usuario.getNome() + " entrou na sala '" + nome + "'");
        notificarTodos("entrou na sala", usuario.getNome());
    }
    

    public void removerUsuario(Observer usuario) {
        if (usuarios.remove(usuario)) {
            System.out.println("❌ " + usuario.getNome() + " saiu da sala '" + nome + "'");
            notificarTodos("saiu da sala", usuario.getNome());
        }
    }
    

    public void enviarMensagem(String mensagem, String remetente) {
        System.out.println("\n📤 " + remetente + " enviou: " + mensagem);
        
        Mensagem msg = new Mensagem(mensagem, remetente);
        historico.add(msg);
        
        notificarTodos(mensagem, remetente);
    }
    

    private void notificarTodos(String mensagem, String remetente) {
        for (Observer usuario : usuarios) {
            if (!usuario.getNome().equals(remetente) || 
                mensagem.equals("entrou na sala") || 
                mensagem.equals("saiu da sala")) {
                usuario.update(mensagem, remetente);
            }
        }
    }
    

    public void exibirHistorico() {
        System.out.println("\n📜 HISTÓRICO DA SALA '" + nome + "':");
        System.out.println("=====================================");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma mensagem ainda.");
        } else {
            for (Mensagem msg : historico) {
                System.out.println(msg);
            }
        }
        System.out.println("=====================================\n");
    }
    

    public int getNumeroUsuarios() {
        return usuarios.size();
    }
    

    public void listarUsuariosOnline() {
        System.out.println("\n👥 Usuários online na sala '" + nome + "': " + usuarios.size());
        for (Observer usuario : usuarios) {
            System.out.println("  • " + usuario.getNome());
        }
    }
}

class UsuarioChat implements Observer {
    private String nome;
    private boolean silenciado;
    
    public UsuarioChat(String nome) {
        this.nome = nome;
        this.silenciado = false;
    }
    
    @Override
    public void update(String mensagem, String remetente) {
        if (!silenciado) {
            if (mensagem.equals("entrou na sala") || mensagem.equals("saiu da sala")) {
                System.out.println("  🔔 " + nome + " foi notificado: " + 
                    remetente + " " + mensagem);
            } else {
                System.out.println("  📨 " + nome + " recebeu: [" + mensagem + "] de " + 
                    remetente);
            }
        }
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    

    public void alternarSilencio() {
        silenciado = !silenciado;
        System.out.println(nome + (silenciado ? " silenciou" : " ativou") + 
            " as notificações");
    }
    

    public void enviarMensagem(ChatRoom sala, String mensagem) {
        sala.enviarMensagem(mensagem, nome);
    }
}

// Classe principal para demonstração
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CHAT - PADRÃO OBSERVER ===\n");
        
        // Criar salas de chat
        ChatRoom salaGeral = new ChatRoom("Geral");
        ChatRoom salaTech = new ChatRoom("Tecnologia");
        
        // Criar usuários
        UsuarioChat alice = new UsuarioChat("Alice");
        UsuarioChat bob = new UsuarioChat("Bob");
        UsuarioChat carlos = new UsuarioChat("Carlos");
        UsuarioChat diana = new UsuarioChat("Diana");
        
        // Cenário 1: Usuários entrando na sala geral
        System.out.println("\n--- Usuários entrando na sala ---");
        salaGeral.registrarUsuario(alice);
        salaGeral.registrarUsuario(bob);
        salaGeral.registrarUsuario(carlos);
        
        // Listar usuários online
        salaGeral.listarUsuariosOnline();
        
        // Cenário 2: Enviando mensagens
        System.out.println("\n--- Conversação no chat ---");
        alice.enviarMensagem(salaGeral, "Olá pessoal! Como estão?");
        bob.enviarMensagem(salaGeral, "Oi Alice! Tudo bem e você?");
        carlos.enviarMensagem(salaGeral, "E aí galera! Acabei de chegar");
        
        // Cenário 3: Novo usuário entra
        System.out.println("\n--- Novo usuário chegando ---");
        salaGeral.registrarUsuario(diana);
        diana.enviarMensagem(salaGeral, "Oi gente! Sou nova aqui 😊");
        
        // Cenário 4: Usuário saindo
        System.out.println("\n--- Usuário saindo ---");
        salaGeral.removerUsuario(bob);
        
        // Cenário 5: Mensagem após saída
        alice.enviarMensagem(salaGeral, "Tchau Bob! Foi bom falar com você");
        
        // Exibir histórico
        salaGeral.exibirHistorico();
        
        // Cenário 6: Segunda sala de chat
        System.out.println("\n--- Segunda sala: Tecnologia ---");
        salaTech.registrarUsuario(alice);
        salaTech.registrarUsuario(carlos);
        
        alice.enviarMensagem(salaTech, "Alguém aqui programa em Java?");
        carlos.enviarMensagem(salaTech, "Eu! Estou estudando padrões de design");
        alice.enviarMensagem(salaTech, "Legal! Observer é muito útil");
        
        // Cenário 7: Demonstrar silenciar notificações
        System.out.println("\n--- Silenciando notificações ---");
        carlos.alternarSilencio();
        alice.enviarMensagem(salaTech, "Carlos, você ainda está aí?");
        
        // Listar usuários e exibir histórico da sala tech
        salaTech.listarUsuariosOnline();
        salaTech.exibirHistorico();
        
        // Status final
        System.out.println("\n--- Status Final ---");
        System.out.println("Sala Geral tem " + salaGeral.getNumeroUsuarios() + " usuários");
        System.out.println("Sala Tecnologia tem " + salaTech.getNumeroUsuarios() + " usuários");
    }
}
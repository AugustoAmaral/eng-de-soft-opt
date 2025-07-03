import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    interface Comando {
        void executar();

        void desfazer();

        String getDescricao();
    }

    static class Luz {
        private String localizacao;
        private boolean ligada = false;

        public Luz(String localizacao) {
            this.localizacao = localizacao;
        }

        public void ligar() {
            ligada = true;
            System.out.println("Luz " + localizacao + " ligada");
        }

        public void desligar() {
            ligada = false;
            System.out.println("Luz " + localizacao + " desligada");
        }

        public boolean isLigada() {
            return ligada;
        }
    }

    static class TV {
        private String marca;
        private boolean ligada = false;
        private int canal = 1;

        public TV(String marca) {
            this.marca = marca;
        }

        public void ligar() {
            ligada = true;
            System.out.println("TV " + marca + " ligada");
        }

        public void desligar() {
            ligada = false;
            System.out.println("TV " + marca + " desligada");
        }

        public void mudarCanal(int canal) {
            this.canal = canal;
            System.out.println("TV " + marca + " mudou para canal " + canal);
        }

        public boolean isLigada() {
            return ligada;
        }
    }

    static class ArCondicionado {
        private String local;
        private boolean ligado = false;
        private int temperatura = 23;

        public ArCondicionado(String local) {
            this.local = local;
        }

        public void ligar() {
            ligado = true;
            System.out.println("Ar condicionado " + local + " ligado");
        }

        public void desligar() {
            ligado = false;
            System.out.println("Ar condicionado " + local + " desligado");
        }

        public void setTemperatura(int temp) {
            this.temperatura = temp;
            System.out.println("Ar condicionado " + local + " ajustado para " + this.temperatura + "°C");
        }

        public boolean isLigado() {
            return ligado;
        }
    }

    static class ComandoLigarLuz implements Comando {
        private Luz luz;

        public ComandoLigarLuz(Luz luz) {
            this.luz = luz;
        }

        @Override
        public void executar() {
            luz.ligar();
        }

        @Override
        public void desfazer() {
            luz.desligar();
        }

        @Override
        public String getDescricao() {
            return "Ligar luz";
        }
    }

    static class ComandoDesligarLuz implements Comando {
        private Luz luz;

        public ComandoDesligarLuz(Luz luz) {
            this.luz = luz;
        }

        @Override
        public void executar() {
            luz.desligar();
        }

        @Override
        public void desfazer() {
            luz.ligar();
        }

        @Override
        public String getDescricao() {
            return "Desligar luz";
        }
    }

    static class ComandoLigarTV implements Comando {
        private TV tv;

        public ComandoLigarTV(TV tv) {
            this.tv = tv;
        }

        @Override
        public void executar() {
            tv.ligar();
        }

        @Override
        public void desfazer() {
            tv.desligar();
        }

        @Override
        public String getDescricao() {
            return "Ligar TV";
        }
    }

    static class ComandoDesligarTV implements Comando {
        private TV tv;

        public ComandoDesligarTV(TV tv) {
            this.tv = tv;
        }

        @Override
        public void executar() {
            tv.desligar();
        }

        @Override
        public void desfazer() {
            tv.ligar();
        }

        @Override
        public String getDescricao() {
            return "Desligar TV";
        }
    }

    static class ComandoMudarCanal implements Comando {
        private TV tv;
        private int canalNovo;
        private int canalAnterior;

        public ComandoMudarCanal(TV tv, int canal) {
            this.tv = tv;
            this.canalNovo = canal;
        }

        @Override
        public void executar() {
            canalAnterior = tv.canal;
            tv.mudarCanal(canalNovo);
        }

        @Override
        public void desfazer() {
            tv.mudarCanal(canalAnterior);
        }

        @Override
        public String getDescricao() {
            return "Mudar para canal " + canalNovo;
        }
    }

    static class ComandoLigarAr implements Comando {
        private ArCondicionado ar;

        public ComandoLigarAr(ArCondicionado ar) {
            this.ar = ar;
        }

        @Override
        public void executar() {
            ar.ligar();
        }

        @Override
        public void desfazer() {
            ar.desligar();
        }

        @Override
        public String getDescricao() {
            return "Ligar ar condicionado";
        }
    }

    static class ComandoMacro implements Comando {
        private List<Comando> comandos;
        private String descricao;

        public ComandoMacro(String descricao) {
            this.descricao = descricao;
            this.comandos = new ArrayList<>();
        }

        public void adicionar(Comando comando) {
            comandos.add(comando);
        }

        @Override
        public void executar() {
            System.out.println("Executando macro: " + descricao);
            for (Comando comando : comandos) {
                comando.executar();
            }
        }

        @Override
        public void desfazer() {
            System.out.println("Desfazendo macro: " + descricao);

            for (int i = comandos.size() - 1; i >= 0; i--) {
                comandos.get(i).desfazer();
            }
        }

        @Override
        public String getDescricao() {
            return "Macro: " + descricao;
        }
    }

    static class ComandoNulo implements Comando {
        @Override
        public void executar() {
            System.out.println("Botão não programado");
        }

        @Override
        public void desfazer() {

        }

        @Override
        public String getDescricao() {
            return "Comando vazio";
        }
    }

    static class ControleRemoto {
        private Comando[] botoes;
        private Stack<Comando> historico;
        private final int NUMERO_BOTOES = 3;

        public ControleRemoto() {
            botoes = new Comando[NUMERO_BOTOES];
            historico = new Stack<>();

            for (int i = 0; i < NUMERO_BOTOES; i++) {
                botoes[i] = new ComandoNulo();
            }
        }

        public void configurarBotao(int numeroBotao, Comando comando) {
            if (numeroBotao >= 0 && numeroBotao < NUMERO_BOTOES) {
                botoes[numeroBotao] = comando;
                System.out.println("Botão " + (numeroBotao + 1) + " configurado: " + comando.getDescricao());
            } else {
                System.out.println("Número de botão inválido!");
            }
        }

        public void pressionarBotao(int numeroBotao) {
            if (numeroBotao >= 0 && numeroBotao < NUMERO_BOTOES) {
                System.out.println("\n>>> Pressionando botão " + (numeroBotao + 1));
                Comando comando = botoes[numeroBotao];
                comando.executar();

                if (!(comando instanceof ComandoNulo)) {
                    historico.push(comando);
                }
            } else {
                System.out.println("Número de botão inválido!");
            }
        }

        public void desfazer() {
            if (!historico.isEmpty()) {
                System.out.println("\n>>> Desfazendo último comando");
                Comando ultimoComando = historico.pop();
                ultimoComando.desfazer();
            } else {
                System.out.println("\n>>> Nada para desfazer");
            }
        }

        public void mostrarHistorico() {
            System.out.println("\n=== HISTÓRICO DE COMANDOS ===");
            if (historico.isEmpty()) {
                System.out.println("Histórico vazio");
            } else {
                List<Comando> tempList = new ArrayList<>(historico);
                for (int i = 0; i < tempList.size(); i++) {
                    System.out.println((i + 1) + ". " + tempList.get(i).getDescricao());
                }
            }
            System.out.println("===========================");
        }

        public void mostrarConfiguracaoBotoes() {
            System.out.println("\n=== CONFIGURAÇÃO DOS BOTÕES ===");
            for (int i = 0; i < NUMERO_BOTOES; i++) {
                System.out.println("Botão " + (i + 1) + ": " + botoes[i].getDescricao());
            }
            System.out.println("==============================");
        }
    }

    // Método main para demonstração
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DO PADRÃO COMMAND ===\n");

        // Criando os receivers
        Luz luzSala = new Luz("da sala");
        TV tvSamsung = new TV("Samsung");
        ArCondicionado arSala = new ArCondicionado("da sala");

        // Criando comandos
        Comando ligarLuzSala = new ComandoLigarLuz(luzSala);
        Comando desligarLuzSala = new ComandoDesligarLuz(luzSala);
        Comando ligarTV = new ComandoLigarTV(tvSamsung);
        Comando desligarTV = new ComandoDesligarTV(tvSamsung);
        Comando mudarCanal5 = new ComandoMudarCanal(tvSamsung, 5);
        Comando ligarAr = new ComandoLigarAr(arSala);

        // Criando um comando macro
        ComandoMacro modoFilme = new ComandoMacro("Modo Filme");
        modoFilme.adicionar(desligarLuzSala);
        modoFilme.adicionar(ligarTV);
        modoFilme.adicionar(mudarCanal5);
        modoFilme.adicionar(ligarAr);

        // Criando o controle remoto
        ControleRemoto controle = new ControleRemoto();

        // Configurando os botões
        System.out.println("1. Configurando o controle remoto:");
        controle.configurarBotao(0, ligarLuzSala);
        controle.configurarBotao(1, ligarTV);
        controle.configurarBotao(2, modoFilme);

        controle.mostrarConfiguracaoBotoes();

        // Testando os botões
        System.out.println("\n2. Testando os botões:");
        controle.pressionarBotao(0); // Liga luz
        controle.pressionarBotao(1); // Liga TV

        controle.mostrarHistorico();

        // Desfazendo comandos
        System.out.println("\n3. Testando desfazer:");
        controle.desfazer(); // Desliga TV
        controle.desfazer(); // Desliga luz
        controle.desfazer(); // Nada para desfazer

        // Testando o comando macro
        System.out.println("\n4. Testando comando macro:");
        controle.pressionarBotao(2); // Executa modo filme

        controle.mostrarHistorico();

        // Reconfigurando botões
        System.out.println("\n5. Reconfigurando botões:");
        controle.configurarBotao(0, desligarLuzSala);
        controle.configurarBotao(1, desligarTV);

        controle.mostrarConfiguracaoBotoes();

        // Mais testes
        System.out.println("\n6. Mais comandos:");
        controle.pressionarBotao(0); // Desliga luz
        controle.pressionarBotao(1); // Desliga TV

        // Desfazendo o macro
        System.out.println("\n7. Desfazendo o comando macro:");
        controle.desfazer(); // Desfaz desligar TV
        controle.desfazer(); // Desfaz desligar luz
        controle.desfazer(); // Desfaz o macro completo

        controle.mostrarHistorico();

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }

}
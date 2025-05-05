public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Contador Global de Instâncias ===");
        
        System.out.println("Criando primeira instância (a)...");
        InstanciaUnica a = InstanciaUnica.getInstance();
        System.out.println("Total de requisições após criar a: " + a.getTotalRequisicoes());
        
        System.out.println("\nCriando segunda instância (b)...");
        InstanciaUnica b = InstanciaUnica.getInstance();
        System.out.println("Total de requisições após criar b: " + b.getTotalRequisicoes());
        
        System.out.println("\nVerificando se a e b são a mesma instância:");
        System.out.println("a == b? " + (a == b));
        
        System.out.println("\nCriando terceira instância (c)...");
        InstanciaUnica c = InstanciaUnica.getInstance();
        System.out.println("Total de requisições após criar c: " + c.getTotalRequisicoes());
        
        System.out.println("\nVerificando o contador em todas as referências:");
        System.out.println("Contador em a: " + a.getTotalRequisicoes());
        System.out.println("Contador em b: " + b.getTotalRequisicoes());
        System.out.println("Contador em c: " + c.getTotalRequisicoes());
    }
    
    static class InstanciaUnica {
        private static InstanciaUnica instance;
        
        private static int totalRequisicoes = 0;
        
        private InstanciaUnica() {
            System.out.println("Construtor da InstanciaUnica foi chamado");
        }
        
        public static synchronized InstanciaUnica getInstance() {
            totalRequisicoes++;
            System.out.println("Requisição #" + totalRequisicoes + " para obter instância");
            
            if (instance == null) {
                instance = new InstanciaUnica();
            }
            
            return instance;
        }
        
        public int getTotalRequisicoes() {
            return totalRequisicoes;
        }
    }
}
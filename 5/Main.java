import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Registro de Protótipos ===");
        
        RegistroDePrototipos registro = new RegistroDePrototipos();
        
        registro.adicionarPrototipo("retangulo", new Retangulo(0, 0, "Vermelho", 100, 50));
        registro.adicionarPrototipo("circulo", new Circulo(0, 0, "Azul", 30));
        
        Forma retangulo1 = registro.clonar("retangulo");
        retangulo1.setX(10);
        retangulo1.setY(20);
        retangulo1.setCor("Verde");
        
        Forma circulo1 = registro.clonar("circulo");
        circulo1.setX(30);
        circulo1.setCor("Amarelo");
        
        Forma retangulo2 = registro.clonar("retangulo");
        Forma circulo2 = registro.clonar("circulo");
        
        System.out.println("\n--- Protótipos originais no registro ---");
        System.out.println("Retângulo protótipo:");
        ((Retangulo)registro.getPrototipo("retangulo")).exibir();
        
        System.out.println("\nCírculo protótipo:");
        ((Circulo)registro.getPrototipo("circulo")).exibir();
        
        System.out.println("\n--- Clones modificados ---");
        System.out.println("Retângulo clone 1 (modificado):");
        retangulo1.exibir();
        
        System.out.println("\nCírculo clone 1 (modificado):");
        circulo1.exibir();
        
        System.out.println("\n--- Clones sem modificação ---");
        System.out.println("Retângulo clone 2:");
        retangulo2.exibir();
        
        System.out.println("\nCírculo clone 2:");
        circulo2.exibir();
    }
    
    static class RegistroDePrototipos {
        private Map<String, Forma> prototipos = new HashMap<>();
        
        public void adicionarPrototipo(String nome, Forma forma) {
            prototipos.put(nome, forma);
        }
        
        public Forma getPrototipo(String nome) {
            return prototipos.get(nome);
        }
        
        public Forma clonar(String nome) {
            Forma prototipo = prototipos.get(nome);
            if (prototipo != null) {
                return prototipo.clonar();
            }
            return null;
        }
    }
    
    static abstract class Forma {
        private int x;
        private int y;
        private String cor;
        
        public Forma(int x, int y, String cor) {
            this.x = x;
            this.y = y;
            this.cor = cor;
        }
        
        public abstract Forma clonar();
        
        public void exibir() {
            System.out.println("Posição: (" + x + ", " + y + ")");
            System.out.println("Cor: " + cor);
        }
        
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        
        public int getY() { return y; }
        public void setY(int y) { this.y = y; }
        
        public String getCor() { return cor; }
        public void setCor(String cor) { this.cor = cor; }
    }
    
    static class Retangulo extends Forma {
        private int largura;
        private int altura;
        
        public Retangulo(int x, int y, String cor, int largura, int altura) {
            super(x, y, cor);
            this.largura = largura;
            this.altura = altura;
        }
        
        @Override
        public Forma clonar() {
            return new Retangulo(getX(), getY(), getCor(), largura, altura);
        }
        
        @Override
        public void exibir() {
            System.out.println("Tipo: Retângulo");
            super.exibir();
            System.out.println("Largura: " + largura);
            System.out.println("Altura: " + altura);
        }
    }
    
    static class Circulo extends Forma {
        private int raio;
        
        public Circulo(int x, int y, String cor, int raio) {
            super(x, y, cor);
            this.raio = raio;
        }
        
        @Override
        public Forma clonar() {
            return new Circulo(getX(), getY(), getCor(), raio);
        }
        
        @Override
        public void exibir() {
            System.out.println("Tipo: Círculo");
            super.exibir();
            System.out.println("Raio: " + raio);
        }
    }
}
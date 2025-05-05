public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Padrão Prototype ===");
        
        Triangulo triangulo = new Triangulo(10, 20, "Vermelho", 30, 40, 50);
        System.out.println("\nTriângulo original:");
        triangulo.exibir();
        
        Triangulo trianguloClone = (Triangulo) triangulo.clonar();
        System.out.println("\nTriângulo clonado (antes da alteração):");
        trianguloClone.exibir();
        
        trianguloClone.setX(15);
        trianguloClone.setCor("Azul");
        trianguloClone.setLado3(60);
        System.out.println("\nTriângulo clonado (após alteração):");
        trianguloClone.exibir();
        
        System.out.println("\nTriângulo original (verificando que não foi alterado):");
        triangulo.exibir();
        
        Elipse elipse = new Elipse(30, 40, "Verde", 100, 50);
        System.out.println("\nElipse original:");
        elipse.exibir();
        
        Elipse elipseClone = (Elipse) elipse.clonar();
        System.out.println("\nElipse clonada (antes da alteração):");
        elipseClone.exibir();
        
        elipseClone.setY(45);
        elipseClone.setCor("Amarelo");
        elipseClone.setRaioMenor(30);
        System.out.println("\nElipse clonada (após alteração):");
        elipseClone.exibir();
        
        System.out.println("\nElipse original (verificando que não foi alterada):");
        elipse.exibir();
        
        // Demonstrando polimorfismo com a classe Circulo
        System.out.println("\n=== Demonstração de Polimorfismo com Círculo ===");
        Figura circulo = new Circulo(50, 50, "Roxo", 75);
        System.out.println("\nCírculo tratado como Figura:");
        circulo.exibir();
        
        Figura circuloClone = circulo.clonar();
        System.out.println("\nClone do círculo (antes da alteração):");
        circuloClone.exibir();
        
        circuloClone.setX(60);
        circuloClone.setCor("Laranja");
        ((Circulo)circuloClone).setRaio(85);
        System.out.println("\nClone do círculo (após alteração):");
        circuloClone.exibir();
    }
    
    static abstract class Figura {
        private int x;
        private int y;
        private String cor;
        
        public Figura(int x, int y, String cor) {
            this.x = x;
            this.y = y;
            this.cor = cor;
        }
        
        public abstract Figura clonar();
        
        public void exibir() {
            System.out.println("Posição: (" + x + ", " + y + ")");
            System.out.println("Cor: " + cor);
        }
        
        public int getX() {
            return x;
        }
        
        public void setX(int x) {
            this.x = x;
        }
        
        public int getY() {
            return y;
        }
        
        public void setY(int y) {
            this.y = y;
        }
        
        public String getCor() {
            return cor;
        }
        
        public void setCor(String cor) {
            this.cor = cor;
        }
    }
    
    static class Triangulo extends Figura {
        private int lado1;
        private int lado2;
        private int lado3;
        
        public Triangulo(int x, int y, String cor, int lado1, int lado2, int lado3) {
            super(x, y, cor);
            this.lado1 = lado1;
            this.lado2 = lado2;
            this.lado3 = lado3;
        }
        
        @Override
        public Figura clonar() {
            return new Triangulo(getX(), getY(), getCor(), lado1, lado2, lado3);
        }
        
        @Override
        public void exibir() {
            System.out.println("Tipo: Triângulo");
            super.exibir();
            System.out.println("Lado 1: " + lado1);
            System.out.println("Lado 2: " + lado2);
            System.out.println("Lado 3: " + lado3);
        }
        
        public int getLado1() {
            return lado1;
        }
        
        public void setLado1(int lado1) {
            this.lado1 = lado1;
        }
        
        public int getLado2() {
            return lado2;
        }
        
        public void setLado2(int lado2) {
            this.lado2 = lado2;
        }
        
        public int getLado3() {
            return lado3;
        }
        
        public void setLado3(int lado3) {
            this.lado3 = lado3;
        }
    }
    
    static class Elipse extends Figura {
        private int raioMaior;
        private int raioMenor;
        
        public Elipse(int x, int y, String cor, int raioMaior, int raioMenor) {
            super(x, y, cor);
            this.raioMaior = raioMaior;
            this.raioMenor = raioMenor;
        }
        
        @Override
        public Figura clonar() {
            return new Elipse(getX(), getY(), getCor(), raioMaior, raioMenor);
        }
        
        @Override
        public void exibir() {
            System.out.println("Tipo: Elipse");
            super.exibir();
            System.out.println("Raio Maior: " + raioMaior);
            System.out.println("Raio Menor: " + raioMenor);
        }
        
        public int getRaioMaior() {
            return raioMaior;
        }
        
        public void setRaioMaior(int raioMaior) {
            this.raioMaior = raioMaior;
        }
        
        public int getRaioMenor() {
            return raioMenor;
        }
        
        public void setRaioMenor(int raioMenor) {
            this.raioMenor = raioMenor;
        }
    }
    
    static class Circulo extends Figura {
        private int raio;
        
        public Circulo(int x, int y, String cor, int raio) {
            super(x, y, cor);
            this.raio = raio;
        }
        
        @Override
        public Figura clonar() {
            return new Circulo(getX(), getY(), getCor(), raio);
        }
        
        @Override
        public void exibir() {
            System.out.println("Tipo: Círculo");
            super.exibir();
            System.out.println("Raio: " + raio);
        }
        
        public int getRaio() {
            return raio;
        }
        
        public void setRaio(int raio) {
            this.raio = raio;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Prototype com Deep Copy ===");
        
        Autor autorOriginal = new Autor("Augusto Amaral", "augusto@email.com");
        Documento docOriginal = new Documento("Tópicos Especiais em Engenharia de Software III: Padrões de Projeto", autorOriginal);
        
        System.out.println("\n--- Documento Original ---");
        docOriginal.exibir();
        
        Documento docClone = docOriginal.clonar();
        
        System.out.println("\n--- Documento Clonado (antes da modificação) ---");
        docClone.exibir();
        
        docClone.setTitulo("Clone dos Tópicos Especiais em Engenharia de Software III");
        
        docClone.getAutor().setNome("Estefanio Carlos Jr.");
        docClone.getAutor().setEmail("estefanio@email.com");
        
        System.out.println("\n--- Documento Clonado (após modificação) ---");
        docClone.exibir();
        
        System.out.println("\n--- Documento Original (verificando que não foi alterado) ---");
        docOriginal.exibir();
        
        System.out.println("\n--- Teste de Referências ---");
        System.out.println("Os documentos são objetos diferentes: " + (docOriginal != docClone));
        System.out.println("Os autores são objetos diferentes: " + (docOriginal.getAutor() != docClone.getAutor()));
    }
    
    static class Autor {
        private String nome;
        private String email;
        
        public Autor(String nome, String email) {
            this.nome = nome;
            this.email = email;
        }
        
        public Autor clonar() {
            return new Autor(this.nome, this.email);
        }
        
        public String getNome() {
            return nome;
        }
        
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
    }
    
    static class Documento {
        private String titulo;
        private Autor autor;
        
        public Documento(String titulo, Autor autor) {
            this.titulo = titulo;
            this.autor = autor;
        }
        
        public Documento clonar() {
            Autor autorClone = this.autor.clonar();
            
            return new Documento(this.titulo, autorClone);
        }
        
        public void exibir() {
            System.out.println("Título: " + titulo);
            System.out.println("Autor: " + autor.getNome());
            System.out.println("Email: " + autor.getEmail());
        }
        
        public String getTitulo() {
            return titulo;
        }
        
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        
        public Autor getAutor() {
            return autor;
        }
        
        public void setAutor(Autor autor) {
            this.autor = autor;
        }
    }
}
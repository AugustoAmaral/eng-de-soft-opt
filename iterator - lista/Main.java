import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    static class MinhaLista implements Iterable<String> {
        private List<String> nomes;

        public MinhaLista() {
            nomes = new ArrayList<>();
        }

        @Override
        public Iterator<String> iterator() {
            return new IteradorNormal();
        }

        public Iterator<String> iteratorReverso() {
            return new IteradorReverso();
        }

        private class IteradorNormal implements Iterator<String> {
            private int indiceAtual = 0;

            @Override
            public boolean hasNext() {
                return indiceAtual < nomes.size();
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Não há mais elementos!");
                }
                return nomes.get(indiceAtual++);
            }
        }

        private class IteradorReverso implements Iterator<String> {
            private int indiceAtual = nomes.size() - 1;

            @Override
            public boolean hasNext() {
                return indiceAtual >= 0;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Não há mais elementos!");
                }
                return nomes.get(indiceAtual--);
            }
        }

        public void setNome(int indice, String nome) {
            if (indice >= 0 && indice < nomes.size()) {
                nomes.set(indice, nome);
            } else {
                throw new IndexOutOfBoundsException("Índice inválido: " + indice);
            }
        }

        public String getNome(int indice) {
            if (indice >= 0 && indice < nomes.size()) {
                return nomes.get(indice);
            } else {
                throw new IndexOutOfBoundsException("Índice inválido: " + indice);
            }
        }

        public void adicionarNome(String nome) {
            nomes.add(nome);
        }

        public boolean removerNome(String nome) {
            return nomes.remove(nome);
        }

        public String removerPorIndice(int indice) {
            if (indice >= 0 && indice < nomes.size()) {
                return nomes.remove(indice);
            } else {
                throw new IndexOutOfBoundsException("Índice inválido: " + indice);
            }
        }

        public int tamanho() {
            return nomes.size();
        }

        public boolean estaVazia() {
            return nomes.isEmpty();
        }
    }

    public static void main(String[] args) {
        MinhaLista lista = new MinhaLista();

        System.out.println("=== DEMONSTRAÇÃO DO PADRÃO ITERATOR COM LISTA DINÂMICA ===\n");

        System.out.println("1. Lista inicial (tamanho: " + lista.tamanho() + "):");

        System.out.println("Adicionando 5 nomes iniciais.");
        lista.adicionarNome("Ana");
        lista.adicionarNome("Bruno");
        lista.adicionarNome("Carlos");
        lista.adicionarNome("Diana");
        lista.adicionarNome("Eduardo");

        for (String nome : lista) {
            System.out.println("   - " + nome);
        }

        System.out.println("\n2. Adicionando novos nomes:");
        lista.adicionarNome("Fernanda");
        lista.adicionarNome("Gabriel");
        System.out.println("   Tamanho após adições: " + lista.tamanho());
        for (String nome : lista) {
            System.out.println("   - " + nome);
        }

        System.out.println("\n3. Iteração reversa:");
        Iterator<String> itReverso = lista.iteratorReverso();
        while (itReverso.hasNext()) {
            System.out.println("   - " + itReverso.next());
        }

        System.out.println("\n4. Modificando alguns nomes:");
        lista.setNome(1, "Beatriz");
        lista.setNome(3, "Daniel");

        System.out.println("   Lista modificada:");
        for (String nome : lista) {
            System.out.println("   - " + nome);
        }

        System.out.println("\n5. Removendo elementos:");
        String removido = lista.removerPorIndice(2);
        System.out.println("   Removido por índice (2): " + removido);

        boolean removeuPorNome = lista.removerNome("Gabriel");
        System.out.println("   Removeu 'Gabriel': " + removeuPorNome);

        System.out.println("   Lista após remoções (tamanho: " + lista.tamanho() + "):");
        for (String nome : lista) {
            System.out.println("   - " + nome);
        }

        System.out.println("\n6. Uso manual do iterator (primeiros 3 elementos):");
        Iterator<String> itManual = lista.iterator();
        for (int i = 0; i < 3 && itManual.hasNext(); i++) {
            System.out.println("   Elemento " + (i + 1) + ": " + itManual.next());
        }

        System.out.println("\n7. Testando lista vazia:");
        MinhaLista listaVazia = new MinhaLista();
        // Remove todos os elementos
        while (!listaVazia.estaVazia()) {
            listaVazia.removerPorIndice(0);
        }
        System.out.println("   Lista está vazia: " + listaVazia.estaVazia());

        try {
            Iterator<String> itVazia = listaVazia.iterator();
            if (itVazia.hasNext()) {
                itVazia.next();
            } else {
                System.out.println("   Iterator da lista vazia não tem elementos");
            }
        } catch (NoSuchElementException e) {
            System.out.println("   Exceção capturada: " + e.getMessage());
        }

        System.out.println("\n8. Tentando acessar além do limite:");
        try {
            Iterator<String> itExcecao = lista.iterator();
            while (true) {
                System.out.println("   - " + itExcecao.next());
            }
        } catch (NoSuchElementException e) {
            System.out.println("   Exceção capturada: " + e.getMessage());
        }

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}
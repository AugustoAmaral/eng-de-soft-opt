import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

interface CalculoFreteStrategy {
    double calcular(Pedido pedido);

    String getDescricao();
}

class Pedido {
    private double valorTotal;
    private double distanciaKm;
    private int quantidadeItens;
    private boolean clienteVip;
    private LocalTime horaPedido;

    public Pedido(double valorTotal, double distanciaKm, int quantidadeItens, boolean clienteVip) {
        this.valorTotal = valorTotal;
        this.distanciaKm = distanciaKm;
        this.quantidadeItens = quantidadeItens;
        this.clienteVip = clienteVip;
        this.horaPedido = LocalTime.now();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public boolean isClienteVip() {
        return clienteVip;
    }

    public LocalTime getHoraPedido() {
        return horaPedido;
    }
}

class FreteNormal implements CalculoFreteStrategy {
    private static final double TAXA_POR_KM = 2.50;
    private static final double TAXA_MINIMA = 5.00;

    @Override
    public double calcular(Pedido pedido) {
        double frete = pedido.getDistanciaKm() * TAXA_POR_KM;
        return Math.max(frete, TAXA_MINIMA);
    }

    @Override
    public String getDescricao() {
        return "Frete Normal (R$ 2,50/km, mínimo R$ 5,00)";
    }
}

class FreteExpresso implements CalculoFreteStrategy {
    private static final double TAXA_BASE = 10.00;
    private static final double TAXA_POR_KM = 4.00;

    @Override
    public double calcular(Pedido pedido) {
        return TAXA_BASE + (pedido.getDistanciaKm() * TAXA_POR_KM);
    }

    @Override
    public String getDescricao() {
        return "Frete Expresso (R$ 10,00 + R$ 4,00/km) - Entrega em 30min";
    }
}

class FreteGratis implements CalculoFreteStrategy {
    @Override
    public double calcular(Pedido pedido) {
        if (pedido.isClienteVip() || pedido.getValorTotal() >= 100.00) {
            return 0.00;
        }

        return new FreteNormal().calcular(pedido);
    }

    @Override
    public String getDescricao() {
        return "Frete Grátis (VIP ou compras acima de R$ 100)";
    }
}

class FretePorVolume implements CalculoFreteStrategy {
    private static final double TAXA_POR_ITEM = 1.50;
    private static final double TAXA_POR_KM = 1.00;

    @Override
    public double calcular(Pedido pedido) {
        double fretePorItens = pedido.getQuantidadeItens() * TAXA_POR_ITEM;
        double fretePorDistancia = pedido.getDistanciaKm() * TAXA_POR_KM;
        return fretePorItens + fretePorDistancia;
    }

    @Override
    public String getDescricao() {
        return "Frete por Volume (R$ 1,50/item + R$ 1,00/km)";
    }
}

class FreteNoturno implements CalculoFreteStrategy {
    private static final double DESCONTO_NOTURNO = 0.7;

    @Override
    public double calcular(Pedido pedido) {
        LocalTime horaLimite = LocalTime.of(20, 0);
        double freteBase = new FreteNormal().calcular(pedido);

        if (pedido.getHoraPedido().isAfter(horaLimite)) {
            return freteBase * DESCONTO_NOTURNO;
        }
        return freteBase;
    }

    @Override
    public String getDescricao() {
        return "Frete Noturno (30% de desconto após 20h)";
    }
}

class SistemaDelivery {
    private CalculoFreteStrategy estrategiaFrete;
    private List<String> historico;

    public SistemaDelivery() {
        this.estrategiaFrete = new FreteNormal();
        this.historico = new ArrayList<>();
    }

    public void setEstrategiaFrete(CalculoFreteStrategy estrategia) {
        this.estrategiaFrete = estrategia;
        System.out.println("✅ Estratégia alterada para: " + estrategia.getDescricao());
    }

    public double calcularFrete(Pedido pedido) {
        double frete = estrategiaFrete.calcular(pedido);
        String registro = String.format("Pedido R$ %.2f | %.1fkm | %d itens | Frete: R$ %.2f (%s)",
                pedido.getValorTotal(),
                pedido.getDistanciaKm(),
                pedido.getQuantidadeItens(),
                frete,
                estrategiaFrete.getDescricao());
        historico.add(registro);
        return frete;
    }

    public void exibirHistorico() {
        System.out.println("\n📋 HISTÓRICO DE CÁLCULOS DE FRETE:");
        System.out.println("=".repeat(80));
        for (String registro : historico) {
            System.out.println(registro);
        }
        System.out.println("=".repeat(80));
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE DELIVERY - PADRÃO STRATEGY ===\n");

        // Criar o sistema
        SistemaDelivery delivery = new SistemaDelivery();

        // Criar alguns pedidos de exemplo
        Pedido pedido1 = new Pedido(45.00, 5.0, 3, false); // Pedido normal
        Pedido pedido2 = new Pedido(120.00, 8.0, 5, false); // Pedido alto valor
        Pedido pedido3 = new Pedido(80.00, 3.0, 2, true); // Cliente VIP
        Pedido pedido4 = new Pedido(200.00, 15.0, 10, false); // Pedido grande

        // Cenário 1: Frete Normal
        System.out.println("🚚 CENÁRIO 1: Calculando com Frete Normal");
        System.out.println("Pedido 1 - Frete: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido1)));

        // Cenário 2: Mudando para Frete Expresso
        System.out.println("\n🚀 CENÁRIO 2: Cliente com pressa");
        delivery.setEstrategiaFrete(new FreteExpresso());
        System.out.println("Pedido 1 - Frete Expresso: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido1)));

        // Cenário 3: Frete Grátis
        System.out.println("\n🎁 CENÁRIO 3: Verificando Frete Grátis");
        delivery.setEstrategiaFrete(new FreteGratis());
        System.out.println("Pedido 3 (VIP) - Frete: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido3)));
        System.out.println("Pedido 2 (>R$100) - Frete: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido2)));
        System.out.println("Pedido 1 (normal) - Frete: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido1)));

        // Cenário 4: Frete por Volume (para muitos itens)
        System.out.println("\n📦 CENÁRIO 4: Pedido com muitos itens");
        delivery.setEstrategiaFrete(new FretePorVolume());
        System.out.println("Pedido 4 (10 itens) - Frete: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedido4)));

        // Cenário 5: Comparação de estratégias
        System.out.println("\n📊 CENÁRIO 5: Comparando todas as estratégias para o mesmo pedido");
        Pedido pedidoTeste = new Pedido(75.00, 10.0, 4, false);

        // Array de todas as estratégias
        CalculoFreteStrategy[] estrategias = {
                new FreteNormal(),
                new FreteExpresso(),
                new FreteGratis(),
                new FretePorVolume(),
                new FreteNoturno()
        };

        System.out.println("Pedido: R$ 75,00 | 10km | 4 itens | Não-VIP\n");
        for (CalculoFreteStrategy estrategia : estrategias) {
            delivery.setEstrategiaFrete(estrategia);
            double valor = delivery.calcularFrete(pedidoTeste);
            System.out.println(String.format("  • %-40s R$ %.2f",
                    estrategia.getDescricao() + ":", valor));
        }

        // Exibir histórico completo
        delivery.exibirHistorico();

        // Cenário 6: Estratégia dinâmica baseada em condições
        System.out.println("\n🤖 CENÁRIO 6: Seleção automática de estratégia");
        Pedido pedidoAutomatico = new Pedido(150.00, 2.0, 8, false);

        // Lógica para escolher estratégia automaticamente
        if (pedidoAutomatico.getValorTotal() >= 100 || pedidoAutomatico.isClienteVip()) {
            delivery.setEstrategiaFrete(new FreteGratis());
        } else if (pedidoAutomatico.getQuantidadeItens() > 5) {
            delivery.setEstrategiaFrete(new FretePorVolume());
        } else if (pedidoAutomatico.getDistanciaKm() < 3) {
            delivery.setEstrategiaFrete(new FreteExpresso());
        } else {
            delivery.setEstrategiaFrete(new FreteNormal());
        }

        System.out.println("Frete calculado automaticamente: R$ " +
                String.format("%.2f", delivery.calcularFrete(pedidoAutomatico)));
    }
}
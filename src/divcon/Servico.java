package divcon;

/**
 * A classe {@code Servico} encapsula os serviços aos quais as pessoas
 * usuárias da conta coletiva podem fazer uso e desejarem pagar
 * Podem ser serviços como intenet, supermercado, água, luz, etc
 */
public class Servico {
    private String nome;
    private Float custo;
    private final int LIMITECHARS = 40;

    /**
     * Aloca um novo objeto {@code Servico}, com um nome e custo inicial
     * @param nome O nome do {@code Servico}. Ex: "Supermercado"
     * @param custo O custo inicial do {@code Servico}. Ex: 109.99
     */
    public Servico(String nome, Float custo) {
        this.nome = nome;
        this.custo = custo;
    }

    /**
     * Verifica se esse serviço já foi pago.
     * @return {@code boolean}, que se for {@code true} é porque o serviço
     * foi pago, caso contrário {@code false}.
     */
    public boolean estaPago() {
        return custo == 0f;
    }

    /**
     * Traz uma string contendo um pequeno sumário da serviço, 
     * como nome e o valor ainda a pagarf
     * @return
     */
    public String getInfoFormatada() {
        return String.format("%" + -LIMITECHARS + "s", nome) + "R$" + getCusto();
    }

    public String getNome() {
        return nome;
    }

    public Float getCusto() {
        return custo;
    }
    
    public void setCusto(Float custo){
        this.custo = custo;
    }
}

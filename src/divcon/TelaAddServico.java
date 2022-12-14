package divcon;

public class TelaAddServico extends Tela {
    private String nomeServico;

    /**
	 * Cria a janela de criar novos serviços
     * @param appDivCon : acesso as funções do aplicativo
	 */
    public TelaAddServico(DivCon appDivCon) {
        super(appDivCon, "Nome do novo serviço:", "Custo do novo serviço:", "Adicionar", "Cancelar");
    }

    @Override
    protected void adicionaElemento() {
        String nomeNovoServico = getFieldUm();
        nomeServico = nomeNovoServico;
        String custoNovoServico = getFieldDois();
        if (Checadora.checaPar(nomeNovoServico, custoNovoServico)) {
            //Adiciona um novo serviço no aplicativo
            appDivCon.addServico(nomeNovoServico, Float.valueOf(custoNovoServico));
            this.setVisible(false);
            limparCampos();
        }
        else {
            lblInfo.setText("Nome ou custo do serviço inválidos.");
        }
    }

    public String getNomeServicoCriado(){
        return nomeServico;
    }
}

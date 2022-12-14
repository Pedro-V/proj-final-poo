package divcon;

import javax.swing.*;

public class TelaCriarConta extends Tela {
	private JPanel painelContas;
	private JFrame janelaPrincipal;
	private JLabel lblInfoSaldo;
	private TelaAddParticipante telaAddParticipante;

	/**
	 * Cria a janela de criar novas contas
     * @param appDivCon : acesso as funções do aplicativo
     * @param painelContas : painel da janela principal que mostra infos das contas
     * @param janelaPrincipal : janela principal do app
	 * @param telaAddParticipante : a janela de adicionar um novo participante na conta
	 * @param lblInfoSaldo : uma label com informações do saldo do participante
	 */
	public TelaCriarConta(DivCon appDivCon, JPanel painelContas, JFrame janelaPrincipal, TelaAddParticipante telaAddParticipante, JLabel lblInfoSaldo) {
		super(appDivCon, "Digite o nome da conta:", "Digite a descrição:", "Adicionar", "Cancelar");
		this.painelContas = painelContas;
		this.janelaPrincipal = janelaPrincipal;
		this.telaAddParticipante = telaAddParticipante;
		this.lblInfoSaldo = lblInfoSaldo;
	}
	
    /**
	 * Cria a conta,
     * passa o que foi digitado nos campos para o aplicativo.
     * Cria o painel com as informações da conta
	 */
	protected void adicionaElemento() {
		if (Checadora.stringOk(getFieldUm())){
			if (appDivCon.getParticipanteLogado().getConta(getFieldUm()) != null) {
				lblInfo.setText("Uma conta com esse nome já existe no seu perfil.");
				return;
			}
			ContaColetiva novaConta = new ContaColetiva(getFieldUm(), getFieldDois());

			PainelConta pnlNovaConta = new PainelConta(novaConta, telaAddParticipante, appDivCon, lblInfoSaldo);
			painelContas.add(pnlNovaConta);
		
			//A nova conta é salva dentro do hashmap do usuario logado
			appDivCon.cadastrarConta(novaConta);
        	//Redesenha a janela principal, "atualiza"
			janelaPrincipal.revalidate();

			limparCampos();

        	//Torna esta janela invisivel após a criação da conta
			this.setVisible(false); 
		} else {
			lblInfo.setText("O nome da conta está vazio ou contém caracteres ilegais.");
		}
	}
}
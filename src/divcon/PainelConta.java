package divcon;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * A classe {@code PainelConta} representa um painel com informações de uma 
 * conta coletiva. Por meio dela é possível ter acesso aos membros da conta,
 * realizar pagamentos, adicionar membros e serviços.
 */
public class PainelConta extends JPanel {
	
	private ContaColetiva conta;
	private JTextField txtFieldValor;
	private TelaAddParticipante telaAddParticipante;
	private DivCon appDivCon;
	private JDialog telaDetalhes;
	private DefaultListModel<String> demoList;
	private JList<String> listaServicos;
	private JLabel lblInfoSaldo;
	private DefaultTableModel tableModel;

	/**
	 * Um painel para mostrar as informações da conta.
	 * @param conta, a conta atual que vai ser criado o painel de detalhes
	 * @param telaAddParticipante, a tela para ser possível adicionar novos participantes
	 * @param appDivCon, a parte funcional do aplicativo
	 * @param lblInfoSaldo, uma label que mostra o saldo individual do participante
	 */
	public PainelConta(ContaColetiva conta, TelaAddParticipante telaAddParticipante, DivCon appDivCon, JLabel lblInfoSaldo) {
		this.conta = conta;
		this.appDivCon = appDivCon;
		this.telaAddParticipante = telaAddParticipante;
		this.lblInfoSaldo = lblInfoSaldo;
		
		setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {80, 310, 80};
		gridBagLayout.rowHeights = new int[] {50};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
		
		JLabel lblNomeConta = new JLabel(conta.getNomeConta());
		lblNomeConta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNomeConta = new GridBagConstraints();
		gbc_lblNomeConta.anchor = GridBagConstraints.WEST;
		gbc_lblNomeConta.fill = GridBagConstraints.VERTICAL;
		gbc_lblNomeConta.insets = new Insets(0, 0, 0, 5);
		gbc_lblNomeConta.gridx = 0;
		gbc_lblNomeConta.gridy = 0;
		add(lblNomeConta, gbc_lblNomeConta);
		
		JLabel lblDescricao = new JLabel(conta.getDescricaoConta());
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDescricao = new GridBagConstraints();
		gbc_lblDescricao.anchor = GridBagConstraints.WEST;
		gbc_lblDescricao.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescricao.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescricao.gridx = 1;
		gbc_lblDescricao.gridy = 0;
		add(lblDescricao, gbc_lblDescricao);
		
		JButton btnVerDetalhes = new JButton("Ver detalhes");
		btnVerDetalhes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnVerDetalhes = new GridBagConstraints();
		gbc_btnVerDetalhes.gridx = 2;
		gbc_btnVerDetalhes.gridy = 0;
		add(btnVerDetalhes, gbc_btnVerDetalhes);
		btnVerDetalhes.addActionListener(e -> criarTelaDetalhes());
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		 
        lblNomeConta.setBorder(border);
		Dimension d = new Dimension((int)getMaximumSize().getWidth(), 50);
        this.setMaximumSize(d);
	}
	
	/**
	 * Criação do JDialog (por meio da ação do botão "ver detalhes"),
	 * que mostra os detalhes da conta (usuários, serviços, saldo, etc.) 
	 */
	private void criarTelaDetalhes() {
		telaDetalhes = new JDialog();
		telaDetalhes.setBounds(0, 0, 900, 500);
		telaDetalhes.setLocationRelativeTo(null);
		appDivCon.logaConta(conta.getNomeConta());
			
		JPanel panelCenter = new JPanel();
		telaDetalhes.getContentPane().add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] {30, 200, 200, 30};
		gbl_panelCenter.rowHeights = new int[] {40, 40, 30, 40};
		gbl_panelCenter.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0};
		gbl_panelCenter.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		panelCenter.setLayout(gbl_panelCenter);
			
		JLabel lblParticipantes = new JLabel("Participantes:");
		lblParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblParticipantes = new GridBagConstraints();
		gbc_lblParticipantes.anchor = GridBagConstraints.WEST;
		gbc_lblParticipantes.insets = new Insets(0, 0, 5, 5);
		gbc_lblParticipantes.gridx = 1;
		gbc_lblParticipantes.gridy = 0;
		panelCenter.add(lblParticipantes, gbc_lblParticipantes);
			
		JLabel lblServicos = new JLabel("Serviços a pagar:");
		lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblServicos = new GridBagConstraints();
		gbc_lblServicos.anchor = GridBagConstraints.WEST;
		gbc_lblServicos.insets = new Insets(0, 0, 5, 5);
		gbc_lblServicos.gridx = 2;
		gbc_lblServicos.gridy = 0;
		panelCenter.add(lblServicos, gbc_lblServicos);
			
		JScrollPane scrollPaneParticipantes = new JScrollPane();
		GridBagConstraints gbc_scrollPaneParticipantes = new GridBagConstraints();
		gbc_scrollPaneParticipantes.gridheight = 2;
		gbc_scrollPaneParticipantes.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneParticipantes.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneParticipantes.gridx = 1;
		gbc_scrollPaneParticipantes.gridy = 1;
		panelCenter.add(scrollPaneParticipantes, gbc_scrollPaneParticipantes);
		
		JScrollPane scrollPaneServicos = new JScrollPane();
		GridBagConstraints gbc_scrollPaneServicos = new GridBagConstraints();
		gbc_scrollPaneServicos.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneServicos.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneServicos.gridx = 2;
		gbc_scrollPaneServicos.gridy = 1;
		panelCenter.add(scrollPaneServicos, gbc_scrollPaneServicos);
		
		//Criação de uma JList que armazena os serviços podendo ser selecionados por um simples clique
		demoList = new DefaultListModel<>();
		listaServicos = new JList<>(demoList);
		listaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneServicos.setViewportView(listaServicos);
		
		//TextField que mostra o valor do serviço selecionado pela JList
		txtFieldValor = new JTextField();
		txtFieldValor.setText("Valor: ");
		txtFieldValor.setEditable(false);
		txtFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GridBagConstraints gbc_txtFieldValor = new GridBagConstraints();
		gbc_txtFieldValor.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldValor.gridx = 2;
		gbc_txtFieldValor.gridy = 2;
		panelCenter.add(txtFieldValor, gbc_txtFieldValor);
		txtFieldValor.setColumns(10);
		
		//Botão para adicionar participante
		JButton btnAddParticipante = new JButton("Adicionar participante");
		btnAddParticipante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAddParticipante = new GridBagConstraints();
		gbc_btnAddParticipante.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddParticipante.gridx = 1;
		gbc_btnAddParticipante.gridy = 3;
		panelCenter.add(btnAddParticipante, gbc_btnAddParticipante);
		
		//Botão para adicionar serviço
		JButton btnAddServico = new JButton("Adicionar serviço");
		btnAddServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAddServico = new GridBagConstraints();
		gbc_btnAddServico.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddServico.gridx = 2;
		gbc_btnAddServico.gridy = 3;
		panelCenter.add(btnAddServico, gbc_btnAddServico);
		
		JPanel panelSouth = new JPanel();
		telaDetalhes.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSouth = new GridBagLayout();
		gbl_panelSouth.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSouth.rowHeights = new int[]{0, 0};
		gbl_panelSouth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelSouth.setLayout(gbl_panelSouth);
		
		//Botão para pagar serviço
		JButton btnPagarServico = new JButton("Pagar serviço");
		btnPagarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnPagarServico = new GridBagConstraints();
		gbc_btnPagarServico.anchor = GridBagConstraints.EAST;
		gbc_btnPagarServico.gridx = 12;
		gbc_btnPagarServico.gridy = 0;
		panelSouth.add(btnPagarServico, gbc_btnPagarServico);
		
		JPanel panelNorth = new JPanel();
		telaDetalhes.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		//Label que exibe informação da conta(nome e descrição)
		JLabel lblConta = new JLabel(conta.getInfoFormatada());
		lblConta.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelNorth.add(lblConta);
	
		/**
		 * Para cada serviço na lista de serviços da conta é adicionado
		 * na JList, fazendo com que a lista mostre todos os serviços dessa conta
		 */
		for(Servico servico : conta.getServicos().values()) {
			demoList.addElement(servico.getNome());
		}
		
		//Ação a ser realizada com o serviço selecionado
		listaServicos.addListSelectionListener(e -> mudarValor(listaServicos.getSelectedValue()));
		btnPagarServico.addActionListener(e -> pagaServico(listaServicos.getSelectedValue()));
		btnAddServico.addActionListener(e -> addServico());
		btnAddParticipante.addActionListener(e -> adicionarParticipanteNaConta());
		
		tableModel = new DefaultTableModel(new String[]{"Nome", "Saldo"}, 0);
		
		JTable table = new JTable(tableModel);
		table.setCellSelectionEnabled(false);       				//As células não podem ser selecionadas
		table.setDefaultEditor(Object.class, null); 				//Edição das células desativadas
		table.getColumnModel().getColumn(0).setPreferredWidth(100); //Alterado o tamanho
		table.getColumnModel().getColumn(1).setPreferredWidth(40);  //Alterado o tamanho
		table.getTableHeader().setReorderingAllowed(false);         //Impedir reordenação(movimentação) das colunas
		scrollPaneParticipantes.setViewportView(table);
		attTable();
		telaDetalhes.setVisible(true);
	}

	/**
	 * Método que mostra o valor do serviço selecionado na JList
	 * @param servicoSelecionado o serviço selecionado na JList,
	 * esse método seta o texto da textfield com o custo do serviço
	 */
	private void mudarValor(String servicoSelecionado) {
		try{
			Float custo = conta.getServicos().get(servicoSelecionado).getCusto();
			txtFieldValor.setText("Valor: R$ " + custo.toString());
		} catch (Exception e){
			txtFieldValor.setText("");
		}
	}

	/**
	 * Método para realizar o pagamento de um serviço
	 * @param servicoSelecionado o serviço selecionado na JList,
	 */
	private void pagaServico(String servicoSelecionado) {
		TelaPagamento telaPagamento = new TelaPagamento(appDivCon, servicoSelecionado);
		telaPagamento.setVisible(true);
		//Se o serviço selecionar for nulo quer dizer que ele já foi pago totalmente
		if(conta.getServico(servicoSelecionado) == null) {
			//Remove o item que foi totalmente pago
			int index = listaServicos.getSelectedIndex();
			demoList.remove(index);
		} else {
			//Limpa a seleção da JList
			listaServicos.clearSelection();
		}
		
		//Destrói a tela de pagamento
		telaPagamento.dispose();
		
		// Atualizamos o texto do saldo individual que é mostrado na DivConGUI
		Participante participanteLogado = appDivCon.getParticipanteLogado();
		lblInfoSaldo.setText("Saldo R$: " + participanteLogado.getSaldoIndividual());
		attTable();
	}

	//Adicionar um novo serviço na conta
	private void addServico() {
		TelaAddServico telaAddServico = new TelaAddServico(appDivCon);
		telaAddServico.setVisible(true);
		String servico = telaAddServico.getNomeServicoCriado();
		
		//O novo serviço é adicionado na JList
		demoList.addElement(servico);
	}

	//Adicionar um novo participante na conta
	private void adicionarParticipanteNaConta(){
		telaAddParticipante.setContaAtual(conta);
		telaAddParticipante.attComboBox();
		telaAddParticipante.limparCampos();
		telaAddParticipante.setVisible(true);

		//A tabela é atualizada
		attTable();
	}
	
	/**
	 * Atualiza a tabela, primeiro apaga a tabela inteira e depois refaz por meio de um laço
	 * Atualiza a linha do saldo total
	 */
	private void attTable() {
		tableModel.setRowCount(0);
		for(Participante part : conta.getParticipantes().values()){
			String[] row = {part.getNome(), part.getSaldoIndividual().toString()};
			tableModel.addRow(row);
		}
		tableModel.addRow(new String[]{"Saldo total", conta.calculaSaldoTotal().toString()});
		
	}
}
package estacionamento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public class GerenciarEstacionamento {

	public static Estacionamento estacio = new Estacionamento();
	
	public static List<Estacionamento> e = new ArrayList<>();

	static Acessos[] ga = new Acessos[0];

	public GerenciarEstacionamento() {
		
		e = new LinkedList<Estacionamento>();
		estacio = new Estacionamento();
	}

	private float totalContratante;

	public GerenciarEstacionamento(float totalContratante) {
		this.totalContratante = totalContratante;
	}

	public float getTotalContratante() {
		return totalContratante;
	}

	public void setTotalContratante(float totalContratante) {
		this.totalContratante = totalContratante;
	}

	public static void cadrastrarEstacionamento()
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException, ObjetoNaoEncontradoException {
		String tipo = JOptionPane.showInputDialog("Informe o tipo do Estacionamento");
		if (e.size() > 0) {
			for (Estacionamento es : e) {
				if (es.getTipoDeEstacionamento().equals(tipo)) {
					throw new ValorAcessoInvalidoException();
				}
			}
		}

		String capa = JOptionPane.showInputDialog("Informe a Capacidade de Vagas");
		String valo = JOptionPane.showInputDialog("Informe o Valor Da Fração");
		String hrcheia = JOptionPane.showInputDialog("Informe o Valor Do Desconto sobre a Hora Cheia");
		String dia = JOptionPane.showInputDialog("Informe o Valor da Diaria Diurna");
		String not = JOptionPane.showInputDialog("Informe a Porcentagem da Diaria Noturna");
		String txcontra = JOptionPane.showInputDialog("Informe a Taxa do Contratante");
		String taxamensal = JOptionPane.showInputDialog("Informe a Taxa Fixa Mensal");
		String hrabrir = JOptionPane.showInputDialog("Informe a Hora de Abrir : (HH:mm)");
		String hrfechar = JOptionPane.showInputDialog("Informe a Hora de Fechar : (HH:mm)");
		
		int capacidade = Integer.parseInt(capa);
		int valor = Integer.parseInt(valo);
		float horacheia = Float.parseFloat(hrcheia);
		int diaria = Integer.parseInt(dia);
		float noturno = Float.parseFloat(not);
		int mensal = Integer.parseInt(taxamensal);
		float contra = Float.parseFloat(txcontra);
		
		try {
			estacio = Estacionamento.criarEstacionamento(tipo, hrabrir, hrfechar, capacidade, horacheia, contra, diaria,
					noturno, mensal, valor);
		} catch (DescricaoEmBrancoException u) {
			u.printStackTrace();
		} catch (ValorAcessoInvalidoException u) {
			u.printStackTrace();
		}finally {
			int opcao = JOptionPane.showConfirmDialog(null, "Gostaria de refazer o cadastro?");
			if (opcao == JOptionPane.YES_OPTION) {
					GerenciarAcessos.cadrastrarAcesso();
			}
		}
		
		e.add(estacio);

	}

	public void ligarAcesso(Acessos a) {
		Acessos temp[] = new Acessos[ga.length + 1];
		for (int i = 0; i < ga.length; i++) {
			temp[i] = ga[i];
		}
		temp[ga.length] = a;

		ga = temp;
	}
	
	public static Estacionamento buscarEstacionamento() throws ObjetoNaoEncontradoException, DescricaoEmBrancoException {		
		try {
			String tipo = JOptionPane.showInputDialog("Digite o tipo de Estacionamento: ");
			
			if(tipo == null) {
				throw new DescricaoEmBrancoException();
			}
			
			if (e.size() > 0) {
				for (Estacionamento es : e) {
					String tipo_ = es.getTipoDeEstacionamento();
					if (tipo_.equals(tipo)) {
						estacio = es;
					}
				}
			} else {
				throw new ObjetoNaoEncontradoException();
			}
		
		} catch (DescricaoEmBrancoException u) {
			u.printStackTrace();

		} catch (ObjetoNaoEncontradoException u) {
			u.printStackTrace();
		} 
		
		return estacio;
		}
	

	public static float calcularTotalEstacionamento() {
		float contr = 0.0f;
			
		for (Acessos a : GerenciarAcessos.acs) {
				contr += a.calcularContratante();
		}
		return contr;
	}

	public static void relatorio(Estacionamento estacio) throws ObjetoNaoEncontradoException {
		String resposta = "Tipo : " + estacio.getTipoDeEstacionamento() + "\n" +
						  "Capacidade : " + estacio.getCapacidade() + "\n" +
						  "Hora de abrir : " + estacio.getHoraDeAbrir() + "\n" +
						  "Hora de fechar : " + estacio.getHoraDeFechar() + "\n" +
						  "Desconto sobre hora heia : " + estacio.getDescontoHora() + "%\n" +
						  "Taxa do contratante : " + estacio. getContratante() + "%\n" +
						  "Valor da diária diurna : R$" + estacio.getTaxaDiaria() + "\n" +
						  "Taxa da diária noturna  : " + estacio.getTaxaNoturno() + "%\n" +
						  "Taxa fixa mensal : R$" + estacio.getTaxaFixaMensal() + "\n" +
						  "Valor da fração : R$" + estacio.getValorFracao() + "\n\n" +
						  "Total arrecadado: R$" + calcularTotalEstacionamento();
	
		JOptionPane.showMessageDialog(null, resposta);
		
	}
}

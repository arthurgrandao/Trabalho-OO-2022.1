package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Estacionamento {

	private String tipoDeEstacionamento = "",
			horaDeAbrir = "", horaDeFechar = "";

	private int capacidade = 0;

	protected float descontoHora = 0.0f, contratante = 0.0f , taxaNoturno = 0.0f;

	protected int taxaDiaria = 0, taxaFixaMensal = 0, valorFracao = 0;

	public Estacionamento(String tipoDeEstacionamento, String horaDeAbrir, String horaDeFechar, int capacidade,
			float descontoHora, float contratante, int taxaDiaria, float taxaNoturno,
			int taxaFixaMensal, int valorFracao) {

		this.capacidade = capacidade;
		this.horaDeAbrir = horaDeAbrir;
		this.horaDeFechar = horaDeFechar;
		this.tipoDeEstacionamento = tipoDeEstacionamento;
		this.descontoHora = descontoHora;
		this.contratante = contratante;
		this.taxaDiaria = taxaDiaria;
		this.taxaNoturno = taxaNoturno;
		this.taxaFixaMensal = taxaFixaMensal;
		this.valorFracao = valorFracao;
	}

	public Estacionamento() {
	}

	public float getContratante() {
		return contratante;
	}

	public void setContratante(float contratante) {
		this.contratante = contratante;
	}

	public String getHoraDeAbrir() {
		return horaDeAbrir;
	}

	public void setHoraDeAbrir(String horaDeAbrir) {
		this.horaDeAbrir = horaDeAbrir;
	}

	public float getDescontoHora() {
		return descontoHora;
	}

	public void setDescontoHora(float descontoHora) {
		this.descontoHora = descontoHora;
	}

	public int getTaxaDiaria() {
		return taxaDiaria;
	}

	public void setTaxaDiaria(int taxaDiaria) {
		this.taxaDiaria = taxaDiaria;
	}

	public float getTaxaNoturno() {
		return taxaNoturno;
	}

	public void setTaxaNoturno(float taxaNoturno) {
		this.taxaNoturno = taxaNoturno;
	}

	public int getTaxaFixaMensal() {
		return taxaFixaMensal;
	}

	public void setTaxaFixaMensal(int taxaFixaMensal) {
		this.taxaFixaMensal = taxaFixaMensal;
	}

	public int getValorFracao() {
		return valorFracao;
	}

	public void setValorFracao(int valorFracao) {
		this.valorFracao = valorFracao;
	}

	public void setTipoDeEstacionamento(String tipoDeEstacionamento) {
		this.tipoDeEstacionamento = tipoDeEstacionamento;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public void setHoraDeFechar(String horaDeFechar) {
		this.horaDeFechar = horaDeFechar;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public String getHoraDeFechar() {
		return horaDeFechar;
	}

	public String getTipoDeEstacionamento() {
		return tipoDeEstacionamento;
	}

	public static Estacionamento criarEstacionamento(String tipoDeEstacionamento, String horaDeAbrir,
			String horaDeFechar, int capacidade, float descontoHora, float contratante, int taxaDiaria,
			float taxaNoturno, int taxaFixaMensal, int valorFracao)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		
			if (tipoDeEstacionamento.equalsIgnoreCase("") || horaDeAbrir.equalsIgnoreCase("")
				|| horaDeFechar.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (capacidade <= 0 || descontoHora <= 0 || contratante <= 0 || taxaDiaria <= 0 || taxaNoturno <= 0
				|| taxaFixaMensal <= 0 || valorFracao <= 0) {
			throw new ValorAcessoInvalidoException();		
		}

		try {
			if (horaDeAbrir.length() > 5 || horaDeFechar.length() > 5 || horaDeAbrir.length() < 5 || horaDeFechar.length() < 5) {
				throw new ValorAcessoInvalidoException();
			}
		} catch (ValorAcessoInvalidoException u) {
			u.printStackTrace();
		}
		
		try {
			Integer.parseInt(horaDeAbrir.substring(0,2) + horaDeFechar.substring(3,5));
		
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		
		if (Integer.parseInt(horaDeAbrir.substring(0,2)) >= 24 || (Integer.parseInt(horaDeAbrir.substring(0,2)) == 24 && Integer.parseInt(horaDeAbrir.substring(3,5)) != 0)) {
				throw new ValorAcessoInvalidoException();
		}
		
		if (Integer.parseInt(horaDeAbrir.substring(3,5)) >= 60) {
			throw new ValorAcessoInvalidoException();
		}
		if (Integer.parseInt(horaDeFechar.substring(0,2)) >= 24 || (Integer.parseInt(horaDeFechar.substring(0,2)) == 24 && Integer.parseInt(horaDeFechar.substring(3,5)) != 0)) {
			throw new ValorAcessoInvalidoException();
		}
		
		if (Integer.parseInt(horaDeFechar.substring(3,5)) >= 60) {
			throw new ValorAcessoInvalidoException();
		}
		

		Estacionamento estacio = new Estacionamento(tipoDeEstacionamento, horaDeAbrir, horaDeFechar, capacidade,
				descontoHora, contratante, taxaDiaria, taxaNoturno, taxaFixaMensal, valorFracao);
		return estacio;
	}
}

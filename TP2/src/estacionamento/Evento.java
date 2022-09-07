package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Evento extends GerenciarAcessos {

	private String inicioEvento,
			fimEvento, nomeEvento;

	protected int taxaFixaEve;
	
	boolean eEvento;

	public Evento(String inicioEvento, String fimEvento, int taxaFixaEve , boolean eEvento , String nomeEvento) {
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public boolean getEEvento() {
		return eEvento;
	}

	public void setEEvento(boolean eEvento) {
		this.eEvento = eEvento;
	}

	public String getInicioEvento() {
		return inicioEvento;
	}

	public void setInicioEvento(String inicioEvento) {
		this.inicioEvento = inicioEvento;
	}

	public String getFimEvento() {
		return fimEvento;
	}

	public void setFimEvento(String fimEvento) {
		this.fimEvento = fimEvento;
	}

	public int getTaxaFixaEve() {
		return taxaFixaEve;
	}

	public void setTaxaFixaEve(int taxaFixaEve) {
		this.taxaFixaEve = taxaFixaEve;
	}

	public static void criarEvento(String inicioEvento, String fimEvento, int taxaFixaEve, boolean eEvento , String nomeEvento)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (inicioEvento.equalsIgnoreCase("") || fimEvento.equalsIgnoreCase("")|| nomeEvento.equalsIgnoreCase("")){
			throw new DescricaoEmBrancoException();
		} else if (taxaFixaEve <= 0) {
			throw new ValorAcessoInvalidoException();
		}
	}

	@Override
	public float calcularContratante() {
		Evento e = new Evento(inicioEvento, fimEvento, taxaFixaEve, eEvento , nomeEvento);
		int valor = e.getTaxaFixaEve();
		float contra = (valor * es.contratante) / 100;
		return contra;
	}

	@Override
	public float calcularValor() {
		return e.getTaxaFixaEve();
	}

}

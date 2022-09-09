package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Evento extends Acessos {

	private String inicioEvento,
			fimEvento, nomeEvento;

	protected float taxaFixaEve;

	boolean eEvento;

	public Evento(String placa, String dataEntrada, String dataSaida,
			String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida, boolean evento,
			boolean mensalista, boolean turnos, boolean horasfracao, String inicioEvento,
			String fimEvento, float taxaFixaEve, String nomeEvento) {

		super(placa, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista,
				turnos, horasfracao);
		this.inicioEvento = inicioEvento;
		this.fimEvento = fimEvento;
		this.taxaFixaEve = taxaFixaEve;
		this.nomeEvento = nomeEvento;

	}

	public Evento() {
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

	public float getTaxaFixaEve() {
		return taxaFixaEve;
	}

	public void setTaxaFixaEve(float taxaFixaEve) {
		this.taxaFixaEve = taxaFixaEve;
	}

	public static Evento criarEvento(String placa, String dataEntrada, String dataSaida,
			String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida,
			boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, String nomeEvento,
			String inicioEvento,String fimEvento, float taxaFixaEve)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (inicioEvento.equalsIgnoreCase("") || fimEvento.equalsIgnoreCase("") || nomeEvento.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (taxaFixaEve <= 0) {
			throw new ValorAcessoInvalidoException();
		}

		Evento e = new Evento(placa, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str,
				horaEntrada, horaSaida, evento, mensalista, turnos, horasfracao, inicioEvento, fimEvento, taxaFixaEve,
				nomeEvento);

		return e;
	}

	public float calcularContratante() {
		// Evento e = new Evento(placa, dataEntrada, dataSaida, evento, mensalista,
		// turnos, horasfracao,
		// horaEntrada, horaSaida, inicioEvento, fimEvento, taxaFixaEve, nomeEvento);
		float valor = getTaxaFixaEve();
		float contra = (valor * es.contratante) / 100;
		return contra;
	}

	public float calcularValor() {
		return getTaxaFixaEve();
	}

}

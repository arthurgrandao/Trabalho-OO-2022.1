package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Evento extends Acessos {
	
	private String inicioEvento,
			fimEvento, nomeEvento;

	protected float taxaFixaEve;

	public Evento(String placa, Estacionamento es, String dataEntrada, String dataSaida,
			String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida, boolean evento,
			boolean mensalista, String inicioEvento,
			String fimEvento, float taxaFixaEve, String nomeEvento) {

		super(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista);
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

	public static Evento criarEvento(String placa, Estacionamento es, String dataEntrada, String dataSaida,
			String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida,
			boolean evento, boolean mensalista, String nomeEvento,
			String inicioEvento,String fimEvento, float taxaFixaEve)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (inicioEvento.equalsIgnoreCase("") || fimEvento.equalsIgnoreCase("") || nomeEvento.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (taxaFixaEve <= 0) {
			throw new ValorAcessoInvalidoException();
		}

		Evento e = new Evento(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str,
				horaEntrada, horaSaida, evento, mensalista, inicioEvento, fimEvento, taxaFixaEve,
				nomeEvento);

		return e;
	}

	public float calcularContratante() {
		Estacionamento es = getEstacionamento();

		float contra = (getTaxaFixaEve() * es.getContratante())/100;
		return contra;
	}

	public float calcularValor() {
		return getTaxaFixaEve();
	}

}

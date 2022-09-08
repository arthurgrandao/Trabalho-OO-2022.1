package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Acessos {

	public static Estacionamento es = new Estacionamento();

	protected String placa = "";

	protected String dataEntrada = "";
	protected String dataSaida = "";

	protected int horaEntrada = 0,
			horaSaida = 0;

	protected boolean evento = false,
			mensalista = false,
			turnos = false,
			horasfracao = false;

	public Acessos(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
			int horaEntrada, int horaSaida) {		
		this.placa = placa;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.evento = evento;
		this.mensalista = mensalista;
		this.turnos = turnos;
		this.horasfracao= horasfracao;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
	}

	public Acessos() {
	}

	public int getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(int horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public int getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public boolean isEvento() {
		return evento;
	}

	public void setEvento(boolean evento) {
		this.evento = evento;
	}
	
	public boolean getTurnos() {
		return turnos;
	}

	public void setTurnos(boolean turnos) {
		this.turnos = turnos;
	}

	public boolean getHorasFracao() {
		return horasfracao;
	}

	public void setHorasFracao(boolean horasfracao) {
		this.horasfracao = horasfracao;
	}

	public boolean isMensalista() {
		return mensalista;
	}

	public void setMensalista(boolean mensalista) {
		this.mensalista = mensalista;
	}

	public static Acessos criarAcesso(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
			int horaEntrada, int horaSaida)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Acessos a = new Acessos(placa, dataEntrada, dataSaida, evento, mensalista, turnos, horasfracao, horaEntrada,  horaSaida);
		return a;
	}
}

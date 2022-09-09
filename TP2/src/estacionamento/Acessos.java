package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Acessos {

	public static Estacionamento es = new Estacionamento();

	protected String placa = "";

	protected String dataEntrada = "",
					 dataSaida = "",
					 horaEntrada_str = "",
					 horaSaida_str = "";

	protected int horaEntrada = 0,
			horaSaida = 0;

	protected boolean evento = false,
			mensalista = false,
			turnos = false,
			horasfracao = false;

	public Acessos(String placa, String dataEntrada, String dataSaida, 
				   String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida,
				   boolean evento, boolean mensalista, boolean turnos, boolean horasfracao) {		
		this.placa = placa;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.horaEntrada_str = horaEntrada_str;
		this.horaSaida_str = horaSaida_str;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.evento = evento;
		this.mensalista = mensalista;
		this.turnos = turnos;
		this.horasfracao = horasfracao;
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

	public String getHoraEntrada_str() {
        return horaEntrada_str;
    }

    public String getHoraSaida_str() {
        return horaSaida_str;
    }
	
	public static Acessos criarAcesso(String placa, String dataEntrada, String dataSaida, String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida, boolean evento, boolean mensalista, boolean turnos,
	boolean horasfracao)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Acessos a = new Acessos(placa, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada,  horaSaida,  evento, mensalista, turnos, horasfracao);
		return a;
	}

    
}

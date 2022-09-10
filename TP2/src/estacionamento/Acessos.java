package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Acessos {

	protected Estacionamento es = null;
	
	protected String placa = "";

	protected String dataEntrada = "",
					 dataSaida = "",
					 horaEntrada_str = "",
					 horaSaida_str = "";

	protected int horaEntrada = 0,
			horaSaida = 0;

	protected boolean evento = false,
			mensalista = false;
			//turnos = false,
			//horasfracao = false;

	public Acessos(String placa, Estacionamento es, String dataEntrada, String dataSaida,  
				   String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida,
				   boolean evento, boolean mensalista) {		
		this.placa = placa;
		this.es = es;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.horaEntrada_str = horaEntrada_str;
		this.horaSaida_str = horaSaida_str;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.evento = evento;
		this.mensalista = mensalista;
	}

	public Acessos() {
	}

	public Estacionamento getEstacionamento() {
		return es;
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

	public boolean isMensalista() {
		return mensalista;
	}

	public void setMensalista(boolean mensalista) {
		this.mensalista = mensalista;
	}

	public String getHoraEntrada_str() {
        return horaEntrada_str;
    }

	public void setHoraEntrada_str(String horaEntrada_str) {
		this.horaEntrada_str = horaEntrada_str;
	}

    public String getHoraSaida_str() {
        return horaSaida_str;
    }
	
	public void setHoraSaida_str(String horaSaida_str) {
		this.horaSaida_str = horaSaida_str;
	}

	public boolean isNoturno() {
		boolean noturno = false;
		int confirma = calcularTempo();

		String dataDeEntrada = getDataEntrada();
		String dataDeSaida = getDataSaida();

		if (dataDeEntrada != dataDeSaida && confirma >= 540) {
			noturno = true;
		} else if (dataDeEntrada != dataDeSaida && confirma < 540) {
			noturno = false;
		}
		return noturno;
	}
	
	public int calcularTempo() {
		String dataDeEntrada = getDataEntrada(),
		 	   dataDeSaida = getDataSaida();
		int horaDeEntrada = getHoraEntrada(),
			horaDeSaida = getHoraSaida(),
			temp;

		if ((dataDeEntrada.equals(dataDeSaida))) {
			temp = horaDeSaida - horaDeEntrada;

		} else {
			int dias = Integer.parseInt(dataDeSaida.substring(0,2)) - Integer.parseInt(dataDeEntrada.substring(0,2));

			temp = ((24 * 60) - horaDeEntrada) +  ((dias-1)*(24 * 60)) + horaDeSaida; // 24*60 = Meia-noite
		}

		return temp;
	}

	public float calcularContratante() {
		Estacionamento es = getEstacionamento();
		
		float contra = calcularValor() * (es.getContratante()/100);
		return contra;
	}

	public float calcularValor() {
		Estacionamento es = getEstacionamento();
		int temp = calcularTempo();

		float resultado;
		
		if (temp >= 540) {
			int taxaDiaria = es.getTaxaDiaria();
			float taxaNoturno = es.getTaxaNoturno();

			boolean turn = isNoturno();

			if (turn == false) { // Maior que 9h de estadia
				resultado = (float) taxaDiaria;
			} else {
				resultado = taxaDiaria * taxaNoturno / 100;
			}			
		
		} else {
			int valor = es.getValorFracao();
			float desconto = es.getDescontoHora();
			
			resultado = ((int) temp / 15) * valor;

			if (temp >= 60) {
				return (resultado - (resultado * (((int) temp / 60) * desconto)/100));
			}	
		}
		return resultado;
		
	}
	

	
	public static Acessos criarAcesso(String placa, Estacionamento es,String dataEntrada, String dataSaida, String horaEntrada_str, String horaSaida_str, int horaEntrada, int horaSaida, boolean evento, boolean mensalista)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Acessos a = new Acessos(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada,  horaSaida,  evento, mensalista);
		return a;
	}

    
}

package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class HorasFracao extends Padrao {
	public HorasFracao() {
    }

    public HorasFracao(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
		int horaEntrada, int horaSaida) {
				this.placa = placa;
				this.dataEntrada = dataEntrada;
				this.dataSaida = dataSaida;
				this.evento = evento;
				this.mensalista = mensalista;
				this.turnos = turnos;
				this.horasfracao = horasfracao;
				this.horaEntrada = horaEntrada;
				this.horaSaida = horaSaida;
	}

	public float calcularValor() {
		int temp = calcularTempo();

		int valor = es.getValorFracao();
		float desconto = es.getDescontoHora();

		float resultado = ((int) temp / 15) * valor;

		if (temp >= 60) {
			return (resultado - (resultado * ((int) temp / 60) * desconto));
		}

		return resultado;
	}

	public float calcularContratante() {
		float contra = calcularValor() * es.getContratante();
		return contra;
	}

	@Override
	public int calcularTempo() {
		String dataDeEntrada = getDataEntrada();
		String dataDeSaida = getDataSaida();
		int horaDeEntrada = getHoraEntrada();
		int horaDeSaida = getHoraSaida();
		int temp;

		if (dataDeEntrada != dataDeSaida) {
			temp = ((24 * 60) - horaDeEntrada) + horaDeSaida; // 24*60 = Meia-noite
		} else {
			temp = horaDeSaida - horaDeEntrada;
		}

		return temp;
	}

	public static HorasFracao criaHorasFracao(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
	int horaEntrada, int horaSaida)
	throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		HorasFracao a = new HorasFracao(placa, dataEntrada, dataSaida, evento, mensalista, turnos, horasfracao, horaEntrada, horaSaida);
		return a;
		}
	} 


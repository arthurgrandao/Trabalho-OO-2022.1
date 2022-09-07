package estacionamento;

public class Turnos extends Padrao {

	private boolean noturno;

	public Turnos(boolean noturno) {
		this.noturno = noturno;
	}

	public boolean isNoturno() {
		int confirma = calcularTempo();

		String dataDeEntrada = getDataEntrada();
		String dataDeSaida = getDataSaida();

		if (dataDeEntrada != dataDeSaida && confirma < 540) {
			noturno = true;
		} else if (dataDeEntrada != dataDeSaida && confirma >= 540) {
			noturno = false;
		}
		return noturno;
	}

	public void setNoturno(boolean noturno) {
		this.noturno = noturno;
	}

	public float calcularValor() {
		float resultado;

		int taxaDiaria = es.getTaxaDiaria();
		float taxaNoturno = es.getTaxaNoturno();

		boolean turn = isNoturno();

		if (turn == false) { // Maior que 9h de estadia
			resultado = (float) taxaDiaria;
		} else {
			resultado = (taxaDiaria / 100) * taxaNoturno;
		}
		return resultado;
	}

	public float calcularContratante() {

		float contra = calcularValor() * es.contratante;
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

}

package estacionamento;

public class HorasFracao extends Padrao {
	private float valorTotal;

	public HorasFracao(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
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

	@Override
	public float calcularContratante() {
		float contra = calcularValor() * es.getContratante();
		return contra;
	}

	public int calcularTempo() {
		String dataDeEntrada = a.getDataEntrada();
		String dataDeSaida = a.getDataSaida();
		int horaDeEntrada = a.getHoraEntrada();
		int horaDeSaida = a.getHoraSaida();
		int temp;

		if (dataDeEntrada != dataDeSaida) {
			temp = ((24 * 60) - horaDeEntrada) + horaDeSaida; // 24*60 = Meia-noite
		} else {
			temp = horaDeSaida - horaDeEntrada;
		}

		return temp;
	}

}

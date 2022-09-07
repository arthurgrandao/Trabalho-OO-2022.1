package estacionamento;

public class Mensalista extends Acessos {
	public float calcularContratante() {
		float contra = (es.getTaxaFixaMensal() * es.getContratante()) / 100;
		return contra;
	}

	public float calcularValor() {
		return es.getTaxaFixaMensal();
	}
}

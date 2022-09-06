package estacionamento;

public class Mensalista extends GerenciarAcessos {

	@Override
	public float calcularContratante() {
		float contra = (es.getTaxaFixaMensal() * es.getContratante()) / 100;
		return contra;
	}

	@Override
	public float calcularValor() {
		return es.getTaxaFixaMensal();
	}
}

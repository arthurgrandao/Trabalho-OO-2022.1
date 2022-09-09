package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Mensalista extends Acessos {
	Estacionamento es = null;
	
	public Mensalista(String placa, Estacionamento es,String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
			int horaEntrada, int horaSaida, boolean evento, boolean mensalista) {		
		super(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista);
	}
	
	public Mensalista() {
    }

    public float calcularContratante() {
		//float contra = (es.getTaxaFixaMensal() * es.getContratante()) / 100;
		//return contra;
		return 0.00f;
	}

	public float calcularValor() {
		//return es.getTaxaFixaMensal();
		return 0.00f;
	}

	public static Mensalista criarMensalista(String placa, Estacionamento es, String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
		int horaEntrada, int horaSaida, boolean evento, boolean mensalista)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Mensalista a = new Mensalista(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista);
		return a;
	}
}

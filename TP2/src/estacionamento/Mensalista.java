package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Mensalista extends Acessos {
	public Mensalista(String placa, String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
			int horaEntrada, int horaSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao) {		
		super(placa, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista, turnos, horasfracao);
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

	public static Mensalista criarMensalista(String placa, String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
		int horaEntrada, int horaSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Mensalista a = new Mensalista(placa, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista, turnos, horasfracao);
		return a;
	}
}

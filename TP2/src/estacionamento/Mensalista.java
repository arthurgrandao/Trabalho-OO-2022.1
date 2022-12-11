package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Mensalista extends Acessos {
	
	public Mensalista(String placa, Estacionamento es,String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
			int horaEntrada, int horaSaida, boolean evento, boolean mensalista) {		
		super(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista);
	}
	
	public Mensalista() {
    }

    public float calcularContratante() {

		return 0.00f;
	}
	
	public float calcularValor() {
		return 0.00f;
	}

	public static Mensalista criarMensalista(String placa, Estacionamento es, String dataEntrada, String dataSaida,String horaEntrada_str, String horaSaida_str, 
	int horaEntrada, int horaSaida, boolean evento, boolean mensalista)
	throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("") || (horaEntrada_str.equalsIgnoreCase("")) || (horaSaida_str.equalsIgnoreCase(""))) {
			throw new DescricaoEmBrancoException();
		
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}

		try {
			if (horaEntrada_str.length() > 5 || horaSaida_str.length() > 5 || dataEntrada.length() > 10 || dataSaida.length() > 10) {
				throw new ValorAcessoInvalidoException();
			}
		} catch (ValorAcessoInvalidoException u) {
			u.printStackTrace();
		}

		try {
			Integer.parseInt(horaEntrada_str.substring(0,2) + horaEntrada_str.substring(3,5));
			Integer.parseInt(dataEntrada.substring(6, 10));
			Integer.parseInt(dataSaida.substring(6, 10));
		
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		
		}
		
		if (Integer.parseInt(horaEntrada_str.substring(0,2)) >= 24 || (Integer.parseInt(horaEntrada_str.substring(0,2)) == 24 && Integer.parseInt(horaEntrada_str.substring(3,5)) != 0)) {
			throw new ValorAcessoInvalidoException();
		}
		
		if (Integer.parseInt(horaEntrada_str.substring(3,5)) >= 60) {
			throw new ValorAcessoInvalidoException();
		}

		if (Integer.parseInt(horaSaida_str.substring(0,2)) >= 24 || (Integer.parseInt(horaSaida_str.substring(0,2)) == 24 && Integer.parseInt(horaSaida_str.substring(3,5)) != 0)) {
			throw new ValorAcessoInvalidoException();
		}
		
		if (Integer.parseInt(horaSaida_str.substring(3,5)) >= 60) {
			throw new ValorAcessoInvalidoException();
		}
		
		Integer.parseInt(horaSaida_str.substring(0,2) + horaSaida_str.substring(3,5));

		if (Integer.parseInt(dataEntrada.substring(0,2)) > 31 || (Integer.parseInt(dataEntrada.substring(3,5)) > 12) || (Integer.parseInt(dataEntrada.substring(0,2)) > 29 && Integer.parseInt(dataEntrada.substring(3,5)) == 02)) {
			throw new ValorAcessoInvalidoException();
		}
		
		if (Integer.parseInt(dataSaida.substring(0,2)) > 31 || (Integer.parseInt(dataSaida.substring(3,5)) > 12) || (Integer.parseInt(dataSaida.substring(0,2)) > 29 && Integer.parseInt(dataSaida.substring(3,5)) == 02)) {
			throw new ValorAcessoInvalidoException();
		}
		
		Mensalista a = new Mensalista(placa, es, dataEntrada, dataSaida, horaEntrada_str, horaSaida_str, horaEntrada, horaSaida, evento, mensalista);
		return a;
	}
}

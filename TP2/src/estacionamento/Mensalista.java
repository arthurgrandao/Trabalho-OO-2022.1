package estacionamento;

import exceptions.DescricaoEmBrancoException;
import exceptions.ValorAcessoInvalidoException;

public class Mensalista extends Acessos {
	public Mensalista(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
			int horaEntrada, int horaSaida) {		
		this.placa = placa;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.evento = evento;
		this.mensalista = mensalista;
		this.turnos = turnos;
		this.horasfracao= horasfracao;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
	}
	
	public Mensalista() {
    }

    public float calcularContratante() {
		float contra = (es.getTaxaFixaMensal() * es.getContratante()) / 100;
		return contra;
	}

	public float calcularValor() {
		return es.getTaxaFixaMensal();
	}

	public static Mensalista criarMensalista(String placa, String dataEntrada, String dataSaida, boolean evento, boolean mensalista, boolean turnos, boolean horasfracao, 
			int horaEntrada, int horaSaida)
			throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		if (placa.equalsIgnoreCase("") || dataEntrada.equalsIgnoreCase("") || dataSaida.equalsIgnoreCase("")) {
			throw new DescricaoEmBrancoException();
		} else if (horaEntrada < 0 || horaSaida < 0) {
			throw new ValorAcessoInvalidoException();
		}
		Mensalista a = new Mensalista(placa, dataEntrada, dataSaida, evento, mensalista, turnos, horasfracao, horaEntrada,  horaSaida);
		return a;
	}
}

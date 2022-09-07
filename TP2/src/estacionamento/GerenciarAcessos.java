package estacionamento;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public abstract class GerenciarAcessos {

	public static Acessos a = new Acessos();

	public static GerenciarAcessos g = new GerenciarAcessos();

	public static Estacionamento es = new Estacionamento();

	private static List<Acessos> acs = new ArrayList<>();

	public static Evento e = new Evento();

	public GerenciarAcessos() {
		acs = new LinkedList<Acessos>();
		es = new Estacionamento();
	}

	public abstract float calcularValor();

	public abstract float calcularContratante();

	public static void cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		boolean roda = false;
		do {
			roda = false;

			String placa = JOptionPane.showInputDialog("Informe a Placa do veículo:");
			String dataDeEntrada = JOptionPane.showInputDialog("Informe a Data de Entrada:");
			String dataDeSaida = JOptionPane.showInputDialog("Informe a Data de Saida:");
			a.setPlaca(placa);
			a.setDataEntrada(dataDeEntrada);
			a.setDataSaida(dataDeSaida);
			int evento = JOptionPane.showConfirmDialog(null, "É do tipo Evento ?:");
			if (evento == JOptionPane.YES_OPTION) { // Evento
				e.setEEvento(true);
				String nomeDoEvento = JOptionPane.showInputDialog("Informe o Nome do Evento:");
				String inicioDoEvento = JOptionPane.showInputDialog("Informe a hora do Evento:");
				String saidaDoEvento = JOptionPane.showInputDialog("Informe a saida do Evento:");
				String taxaDoEvento = JOptionPane.showInputDialog("Informe a taxa do Evento:");
				int taxa = Integer.parseInt(taxaDoEvento);
				e.setNomeEvento(nomeDoEvento);
				e.setInicioEvento(inicioDoEvento);
				e.setFimEvento(saidaDoEvento);
				e.setTaxaFixaEve(taxa);
				try {
					Evento.criarEvento(inicioDoEvento, saidaDoEvento, taxa , true ,nomeDoEvento);
				} catch (DescricaoEmBrancoException y) {
				} catch (ValorAcessoInvalidoException y) {
				}
			}

			else if (evento == JOptionPane.NO_OPTION) {
				int mensalista = JOptionPane.showConfirmDialog(null, "É do tipo Mensalista ?");

				if (mensalista == JOptionPane.YES_OPTION) { // Mensalista
					a.setMensalista(true);
					String horaDeEnt = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
					String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");

					// String horaDeEntrada_str = Integer.toString(converterHora(horaDeEnt));
					// String horaDeSaida_str = Integer.toString(converterHora(horaDeSai));

					int horaDeEntrada = converterHora(horaDeEnt);
					int horaDeSaida = converterHora(horaDeSai);

					a.setHoraEntrada(horaDeEntrada);
					a.setHoraSaida(horaDeSaida);

					try {
						a = Acessos.criarAcesso(placa, dataDeEntrada, dataDeSaida, false, true, horaDeEntrada,
								horaDeSaida);

						converterHora(horaDeEnt);
						converterHora(horaDeSai);

					} catch (DescricaoEmBrancoException b) {
					} catch (ValorAcessoInvalidoException b) {
					}
				}

			}

			else { // Padrão
				String horaDeEnt = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
				String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");

				int horaDeEntrada = converterHora(horaDeEnt);
				int horaDeSaida = converterHora(horaDeSai);

				int horaDeAbrir = converterHora(estacio.getHoraDeAbrir());
				int horaDeFechar = converterHora(estacio.getHoraDeFechar());

				a.setHoraEntrada(horaDeEntrada);
				a.setHoraSaida(horaDeSaida);

				if (horaDeEntrada <= horaDeAbrir || horaDeFechar <= horaDeSaida) {
					JOptionPane.showMessageDialog(null, "Horário Inválido.");
					;
					atualizarAcesso();
					roda = true;
				}

				try {
					a = Acessos.criarAcesso(placa, dataDeEntrada, dataDeSaida, false, false, horaDeEntrada,
							horaDeSaida);
				} catch (DescricaoEmBrancoException b) {
				}
			}
		} while (roda == true);
	}
	
	public boolean addAcessos() {
		return acs.add(a);
	}

	public static Acessos buscarAcessos(String placa) throws ObjetoNaoEncontradoException{
		Iterator<Acessos> it = acs.iterator();
		while(it.hasNext()) {
			Acessos a = it.next();
		if (a.getPlaca().equalsIgnoreCase(placa)) {
		}else {
			throw new ObjetoNaoEncontradoException(null);
		}
		}return a;
	}
	
	public static Acessos pesquisarAcessos() throws DescricaoEmBrancoException, ObjetoNaoEncontradoException { //ToString
		String placa = JOptionPane.showInputDialog("Digite a placa ?");
		if(placa == null) {
			throw new DescricaoEmBrancoException();
		}
		Acessos	resposta = buscarAcessos(placa);
		return resposta;
		}


	protected static boolean atualizarAcesso() throws DescricaoEmBrancoException { //Mudar
		boolean roda = false;
		int info = JOptionPane.showConfirmDialog(null, "Gostaria de Atualizar suas Informações ?");
		if (info == JOptionPane.YES_OPTION) {

		} else {
			throw new DescricaoEmBrancoException();
		}
		return roda;
	}

	public static void removerAcessos() throws DescricaoEmBrancoException, ObjetoNaoEncontradoException  {
		a = pesquisarAcessos();
		if(a != null) {
		delAcessos();		
		}
	}
	
	public static boolean delAcessos() {
		return acs.remove(a);
	}
	
	public static int converterHora(String hora) {
		String horari = hora.substring(0, 2);
		String minut = hora.substring(3, 5);
		int horario = Integer.parseInt(horari);
		int minutos = Integer.parseInt(minut);
		return (60 * horario) + minutos;
	}
	
	public static String relatorio() {
		String resposta = "Placa : " + a.getPlaca() + "\n";
		resposta += "Tipo Do Estacionamento : " + estacio.getTipoDeEstacionamento() + "\n";
		if(e.getEEvento() == true){
			resposta += "Nome do Evento : " +  e.getNomeEvento() + "\n";	
		}
		return resposta;
	
	}

}

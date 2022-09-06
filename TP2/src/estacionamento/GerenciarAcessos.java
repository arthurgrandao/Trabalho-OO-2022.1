package estacionamento;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public abstract class GerenciarAcessos {

	public static Acessos a;

	public static GerenciarAcessos g;

	public static Estacionamento estacio, es;

	private static List<Acessos> acs;

	public static Evento e;

	public GerenciarAcessos() {
		acs = new LinkedList<Acessos>();
		es = new Estacionamento();
	}

	public abstract float calcularValor();

	public abstract float calcularContratante();

	public static boolean cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		boolean roda = false;
		do {
			roda = false;

			String placa = JOptionPane.showInputDialog("Informe a Placa do ve�culo:");
			String dataDeEntrada = JOptionPane.showInputDialog("Informe a Data de Entrada:");
			String dataDeSaida = JOptionPane.showInputDialog("Informe a Data de Saida:");
			a.setPlaca(placa);
			a.setDataEntrada(dataDeEntrada);
			a.setDataSaida(dataDeSaida);
			int evento = JOptionPane.showConfirmDialog(null, "� do tipo Evento ?:");
			if (evento == JOptionPane.YES_OPTION) { // Evento
				a.setEvento(true);
				String inicioDoEvento = JOptionPane.showInputDialog("Informe a hora do Evento:");
				String saidaDoEvento = JOptionPane.showInputDialog("Informe a saida do Evento:");
				String taxaDoEvento = JOptionPane.showInputDialog("Informe a taxa do Evento:");
				int taxa = Integer.parseInt(taxaDoEvento);
				e.setInicioEvento(inicioDoEvento);
				e.setFimEvento(saidaDoEvento);
				e.setTaxaFixaEve(taxa);
				try {
					Evento.criarEvento(inicioDoEvento, saidaDoEvento, taxa);
				} catch (DescricaoEmBrancoException y) {
				} catch (ValorAcessoInvalidoException y) {
				}
			}

			else if (evento == JOptionPane.NO_OPTION) {
				int mensalista = JOptionPane.showConfirmDialog(null, "� do tipo Mensalista ?");

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
			boolean respost = acs.add(a);
			return respost;
		} while (roda == true);
	}

	public Acessos findAcessos(String placa) throws ObjetoNaoEncontradoException {
		Acessos achado = null;
		for (Acessos a : acs) {
			if (a.getPlaca().equalsIgnoreCase(placa)) {
				achado = a;
				break;
			} else {
				throw new ObjetoNaoEncontradoException(null);
			}
		}
		return achado;
	}

	protected static boolean atualizarAcesso() throws DescricaoEmBrancoException {
		boolean roda = false;
		int info = JOptionPane.showConfirmDialog(null, "Gostaria de Atualizar suas Informações ?");
		if (info == JOptionPane.YES_OPTION) {

		} else {
			throw new DescricaoEmBrancoException();
		}
		return roda;
	}

	public boolean removerAcesso(Acessos a) throws ObjetoNaoEncontradoException {
		boolean resposta = false;
		if (acs.contains(a)) {
			resposta = acs.remove(a);
		} else {
			throw new ObjetoNaoEncontradoException(null);
		}
		return resposta;
	}

	public static int converterHora(String hora) {
		String horari = hora.substring(0, 2);
		String minut = hora.substring(3, 5);
		int horario = Integer.parseInt(horari);
		int minutos = Integer.parseInt(minut);
		return (60 * horario) + minutos;
	}

}

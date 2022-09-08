package estacionamento;

//import java.util.ArrayList;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public abstract class GerenciarAcessos {

	public static Estacionamento es = new Estacionamento();

	public static Evento eve = new Evento();

	public static Mensalista m = new Mensalista();

	public static Turnos t = new Turnos();

	public static HorasFracao hf = new HorasFracao();

	public static Acessos a = new Acessos();


	private static List<Acessos> acs = new LinkedList<Acessos>();;

	public GerenciarAcessos() {
	}


	public static void cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		boolean roda = false;
		do {
			roda = false;
			Estacionamento estacionamento = null;
			String tipo = JOptionPane.showInputDialog("Informe o tipo de Estacionamento");
			for (Estacionamento es : GerenciarEstacionamento.e) {
				if (es.getTipoDeEstacionamento().equals(tipo)) {
					estacionamento = es;
				}
			}

			String placa = JOptionPane.showInputDialog("Informe a Placa do veiculo:");
			String dataDeEntrada = JOptionPane.showInputDialog("Informe a Data de Entrada:");
			String dataDeSaida = JOptionPane.showInputDialog("Informe a Data de Saida:");
			a.setPlaca(placa);
			a.setDataEntrada(dataDeEntrada);
			a.setDataSaida(dataDeSaida);
			int evento = JOptionPane.showConfirmDialog(null, "E do tipo Evento ?:");
			if (evento == JOptionPane.YES_OPTION) { // Evento
				eve.setEEvento(true);
				String nomeDoEvento = JOptionPane.showInputDialog("Informe o Nome do Evento:");
				String inicioDoEvento = JOptionPane.showInputDialog("Informe a hora do Evento:");
				String saidaDoEvento = JOptionPane.showInputDialog("Informe a saida do Evento:");
				String taxaDoEvento = JOptionPane.showInputDialog("Informe a taxa do Evento:");
				int taxa = Integer.parseInt(taxaDoEvento);

				eve.setNomeEvento(nomeDoEvento);
				eve.setInicioEvento(inicioDoEvento);
				eve.setFimEvento(saidaDoEvento);
				eve.setTaxaFixaEve(taxa);

				Evento.criarEvento(inicioDoEvento, saidaDoEvento, taxa, true, nomeDoEvento);

				acs.add(eve);

				/*
				 * try {
				 * 
				 * 
				 * } catch (DescricaoEmBrancoException y) {
				 * } catch (ValorAcessoInvalidoException y) {
				 * }
				 */
			}

			else if (evento == JOptionPane.NO_OPTION) {
				int mensalista = JOptionPane.showConfirmDialog(null, "E do tipo Mensalista ?");

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

					a = Mensalista.criarMensalista(placa, dataDeEntrada, dataDeSaida, false, true, false, false,
							horaDeEntrada, horaDeSaida);

					acs.add(a);

					/*
					 * try {
					 * 
					 * converterHora(horaDeEnt);
					 * converterHora(horaDeSai);
					 * 
					 * } catch (DescricaoEmBrancoException b) {
					 * } catch (ValorAcessoInvalidoException b) {
					 * }
					 */
				} else { // PadrÃ£o
					String horaDeEnt = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
					String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");

					int horaDeEntrada = converterHora(horaDeEnt);
					int horaDeSaida = converterHora(horaDeSai);

					int horaDeAbrir = converterHora(estacionamento.getHoraDeAbrir());
					int horaDeFechar = converterHora(estacionamento.getHoraDeFechar());

					a.setHoraEntrada(horaDeEntrada);
					a.setHoraSaida(horaDeSaida);

					if (horaDeEntrada <= horaDeAbrir || horaDeFechar <= horaDeSaida) {
						JOptionPane.showMessageDialog(null, "Horario Invalido.");
						;
						atualizarAcesso();
						roda = true;
					}

					if (horaDeSaida - horaDeEntrada >= 540) {
						a = Turnos.criaTurnos(placa, dataDeEntrada, dataDeSaida, false, false, true, false,
								horaDeEntrada, horaDeSaida);
						acs.add(a);
					} else {
						a = HorasFracao.criaHorasFracao(placa, dataDeEntrada, dataDeSaida, false, false, false, true,
								horaDeEntrada, horaDeSaida);
						acs.add(a);
					}

					/*
					 * a = Acessos.criarAcesso(placa, dataDeEntrada, dataDeSaida, false, false,
					 * horaDeEntrada,
					 * horaDeSaida);
					 */

					/*
					 * try {
					 * 
					 * } catch (DescricaoEmBrancoException b) {
					 * }
					 */
				}

			}
		} while (roda == true);
	}

	public boolean addAcessos() {
		return acs.add(a);
	}

	public static Acessos buscarAcessos(String placa) throws ObjetoNaoEncontradoException {
		Acessos n = null;
		if (acs.size() > 0) {
			for (Acessos a : acs) {
				String placa1 = a.getPlaca();
				if (placa1.equals(placa)) {
					n = a;
					return n;
				}
			}
		}
		return n;
	}

	public static Acessos pesquisarAcessos() throws DescricaoEmBrancoException, ObjetoNaoEncontradoException { // ToString
		String placa = JOptionPane.showInputDialog("Digite a placa ?");
		if (placa == null) {
			throw new DescricaoEmBrancoException();
		}
		Acessos resposta = buscarAcessos(placa);

		return resposta;
	}

	protected static boolean atualizarAcesso() throws DescricaoEmBrancoException { // Mudar
		boolean roda = false;
		int info = JOptionPane.showConfirmDialog(null, "Gostaria de Atualizar suas InformaÃ§Ãµes ?");
		if (info == JOptionPane.YES_OPTION) {

		} else {
			throw new DescricaoEmBrancoException();
		}
		return roda;
	}

	public static boolean removerAcessos() throws DescricaoEmBrancoException, ObjetoNaoEncontradoException {
		Acessos a = pesquisarAcessos();

		boolean resposta = false;

		if (acs.contains(a)) {
			resposta = acs.remove(a);
		}

		JOptionPane.showMessageDialog(null, "Remocao concluida!");

		return resposta;


	}

	public static int converterHora(String hora) {
		String horari = hora.substring(0, 2);
		String minut = hora.substring(3, 5);
		int horario = Integer.parseInt(horari);
		int minutos = Integer.parseInt(minut);
		return (60 * horario) + minutos;
	}

	public static void relatorio(Acessos a) {
		Acessos comp = null;
		if (a == comp) {
			JOptionPane.showMessageDialog(null, "Objeto não encontrado.");
		} else {
			String resposta = "Placa : " + a.getPlaca() + "\n";
			resposta += "Tipo Do Estacionamento : " + es.getTipoDeEstacionamento() + "\n";
			if (eve.getEEvento() == true) {
				resposta += "Nome do Evento : " + eve.getNomeEvento() + "\n";
				resposta += "Valor a pagar: " + eve.calcularValor();
			} else if (a.isMensalista() == true) {
				resposta += "Valor a pagar: " + m.calcularValor();
			} else if (t.getTurnos() == true) {
				resposta += "Valor a pagar: " + t.calcularValor();
			} else if (hf.getHorasFracao() == true) {
				resposta += "Valor a pagar: " + hf.calcularValor();
			}

			resposta += "Data de entrada - saída: " + a.getDataEntrada() + " - " + a.getDataSaida() + "\n";
			resposta += "Hora de entrada - saída: " + a.getHoraEntrada() + " - " + a.getHoraSaida() + "\n";

			JOptionPane.showMessageDialog(null, resposta);
			;
		}

	}

}

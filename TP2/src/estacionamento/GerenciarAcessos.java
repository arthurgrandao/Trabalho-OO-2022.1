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

	public static Acessos a = new Acessos();

	private static List<Acessos> acs = new LinkedList<Acessos>();;

	public static void cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		boolean roda = false;
		do {
			roda = false;
			
			Estacionamento estacionamento = null;
			String tipo = JOptionPane.showInputDialog("Informe o tipo de Estacionamento");
			
			for (Estacionamento es : GerenciarEstacionamento.e) {
				if (es.getTipoDeEstacionamento().equals(tipo)) {
					estacionamento = es;
					JOptionPane.showMessageDialog(null, "Estacionamento localizado!");
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

				String horaDeEnt_str = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
				String horaDeSai_str = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");
				String nomeDoEvento = JOptionPane.showInputDialog("Informe o Nome do Evento:");
				String inicioDoEvento = JOptionPane.showInputDialog("Informe o horário de início do Evento:");
				String saidaDoEvento = JOptionPane.showInputDialog("Informe o horário de fim do Evento:");
				String taxaDoEvento = JOptionPane.showInputDialog("Informe a taxa do Evento:");

				float taxaEve = Float.parseFloat(taxaDoEvento);

				Evento eve = Evento.criarEvento(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEnt_str, horaDeSai_str,
						converterHora(horaDeEnt_str), converterHora(horaDeSai_str), true, false, nomeDoEvento, inicioDoEvento, saidaDoEvento,
						taxaEve);

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
					String horaDeEntrada_str = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
					String horaDeSaida_str = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");

					int horaDeEntrada = converterHora(horaDeEntrada_str);
					int horaDeSaida = converterHora(horaDeSaida_str);

					a.setHoraEntrada(horaDeEntrada);
					a.setHoraSaida(horaDeSaida);

					Mensalista m = Mensalista.criarMensalista(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEntrada_str, horaDeSaida_str, horaDeEntrada, horaDeSaida, false, true);

					acs.add(m);

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

					if (horaDeEntrada <= horaDeAbrir || horaDeFechar <= horaDeSaida) {
						JOptionPane.showMessageDialog(null, "Horario Invalido.");
						;
						atualizarAcesso();
						roda = true;
					}
					a = Acessos.criarAcesso(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEnt, horaDeSai, horaDeEntrada, horaDeSaida, false, false);
					acs.add(a);

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
		String placa = JOptionPane.showInputDialog("Digite a placa: ");
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
			Estacionamento es = a.getEstacionamento();

			String resposta = "Placa : " + a.getPlaca() + "\n";

			resposta += "Tipo Do Estacionamento : " + es.getTipoDeEstacionamento() + "\n";
			 
			if (a.isEvento() == true) {
				Evento eve = (Evento) a;
				
				resposta += "Evento : " + eve.getNomeEvento() + "\n";
				resposta += "Valor a pagar: R$" + eve.calcularValor() + "\n";
				resposta += "Valor do contratante: R$" + eve.calcularContratante() + "\n";
			
			} else if (a.isMensalista() == true) {
				Mensalista m = (Mensalista) a;
				
				resposta += "Mensalista\n";
				resposta += "Valor a pagar: R$" + m.calcularValor() + "\n";
				resposta += "Valor do contratante: R$" + m.calcularContratante() + "\n";

			} else {
				resposta += "Valor a pagar: R$" + a.calcularValor() + "\n";
				resposta += "Valor do contratante: R$" + a.calcularContratante() + "\n";
			}
			
			resposta += "Data de entrada - saída: " + a.getDataEntrada() + " - " + a.getDataSaida() + "\n";
			resposta += "Hora de entrada - saída: " + a.getHoraEntrada_str() + " - " + a.getHoraSaida_str() + "\n";

			JOptionPane.showMessageDialog(null, resposta);
			;
		}

	}

}

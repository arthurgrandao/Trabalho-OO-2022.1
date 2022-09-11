package estacionamento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public abstract class GerenciarAcessos {

	public static Acessos a = new Acessos();

	static List<Acessos> acs = new LinkedList<Acessos>();;

	public static void cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
			
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
		
		int evento = JOptionPane.showConfirmDialog(null, "E do tipo Evento ?:");
		if (evento == JOptionPane.YES_OPTION) { // Evento
			Evento eve = new Evento();
			
			String horaDeEnt_str = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
			String horaDeSai_str = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");
			String nomeDoEvento = JOptionPane.showInputDialog("Informe o Nome do Evento:");
			String inicioDoEvento = JOptionPane.showInputDialog("Informe o horário de início do Evento:");
			String saidaDoEvento = JOptionPane.showInputDialog("Informe o horário de fim do Evento:");
			String taxaDoEvento = JOptionPane.showInputDialog("Informe a taxa do Evento:");
			
			float taxaEve = Float.parseFloat(taxaDoEvento);
	
			try {
				eve = Evento.criarEvento(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEnt_str, horaDeSai_str,
					converterHora(horaDeEnt_str), converterHora(horaDeSai_str), true, false, nomeDoEvento, inicioDoEvento, saidaDoEvento, taxaEve);
			
			} catch (DescricaoEmBrancoException y) {
				y.printStackTrace();
			} catch (ValorAcessoInvalidoException y) {
				y.printStackTrace();
			} finally {
				int opcao = JOptionPane.showConfirmDialog(null, "Gostaria de refazer o cadastro?");
				if (opcao == JOptionPane.YES_OPTION) {
					GerenciarAcessos.cadrastrarAcesso();
				}
			}
			
			acs.add(eve);
		}
		else if (evento == JOptionPane.NO_OPTION) {
			
			int mensalista = JOptionPane.showConfirmDialog(null, "E do tipo Mensalista ?");
			if (mensalista == JOptionPane.YES_OPTION) { // Mensalista
				Mensalista m = new Mensalista();
				
				String horaDeEntrada_str = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
				String horaDeSaida_str = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");
				
				try {
					
					int horaDeEntrada = converterHora(horaDeEntrada_str);
					int horaDeSaida = converterHora(horaDeSaida_str);
					
					m = Mensalista.criarMensalista(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEntrada_str, horaDeSaida_str, horaDeEntrada, horaDeSaida, false, true);
				
				
				} catch (DescricaoEmBrancoException b) {
					b.printStackTrace();
				} catch (ValorAcessoInvalidoException b) {
					b.printStackTrace();
				} finally {
					int opcao = JOptionPane.showConfirmDialog(null, "Gostaria de refazer o cadastro?");
					if (opcao == JOptionPane.YES_OPTION) {
						GerenciarAcessos.cadrastrarAcesso();
					}
				}
				
				acs.add(m);
					 
			} else { // Padrão
				String horaDeEnt = JOptionPane.showInputDialog("Informe a hora de Entrada: (HH:mm)");
				String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");
				
				int horaDeEntrada = converterHora(horaDeEnt);
				int horaDeSaida = converterHora(horaDeSai);
				int horaDeAbrir = converterHora(estacionamento.getHoraDeAbrir());
				int horaDeFechar = converterHora(estacionamento.getHoraDeFechar());
				
				if (horaDeEntrada <= horaDeAbrir || horaDeFechar <= horaDeSaida) {
					JOptionPane.showMessageDialog(null, "Horario Invalido.");
					throw new ValorAcessoInvalidoException();
				}
				
				try {
					a = Acessos.criarAcesso(placa, estacionamento, dataDeEntrada, dataDeSaida, horaDeEnt, horaDeSai, horaDeEntrada, horaDeSaida, false, false);
				} catch (DescricaoEmBrancoException b) {
					b.printStackTrace();
				} catch (ValorAcessoInvalidoException v) {
					v.printStackTrace();
				} finally {
					int opcao = JOptionPane.showConfirmDialog(null, "Gostaria de refazer o cadastro?");
					if (opcao == JOptionPane.YES_OPTION) {
						GerenciarAcessos.cadrastrarAcesso();
					}
				}
				
				 acs.add(a);
			}
		}
	}

	public boolean addAcessos() {
		return acs.add(a);
	}

	public static List<Acessos> buscarAcessos() throws ObjetoNaoEncontradoException, DescricaoEmBrancoException {
		String placa = JOptionPane.showInputDialog("Digite a placa: ");
		 
		if (placa == null) {
			throw new DescricaoEmBrancoException();
		}
		
		List<Acessos> acessos = new ArrayList<>();
		
		if (acs.size() > 0) {
			for (Acessos a : acs) {
				String placa1 = a.getPlaca();
				if (placa1.equals(placa)) {
					acessos.add(a);
				}
			}
		}
		return acessos;
	}

	public static Acessos escolherAcesso() throws ObjetoNaoEncontradoException, DescricaoEmBrancoException {
		List<Acessos> acessos = buscarAcessos();
		String resposta = "Acessos: (Placa / Hora de Entrada)\n";
		int i = 1;

		if (acessos.size()>0) {
			for (Acessos a : acessos) {
				resposta +=  i + "- Acesso: ("+a.getPlaca()+" / "+a.getHoraEntrada_str()+")\n";
				i++;
				}
			
			String op = JOptionPane.showInputDialog(resposta);
			int opcao = Integer.parseInt(op);
			
			return acessos.get(opcao-1);
		
		} else {
			throw new ObjetoNaoEncontradoException(null);
		}
	}
	
	public static void atualizarAcesso(Acessos a) throws DescricaoEmBrancoException { // Mudar
		int opcao = 0; 
		do {
			String menu = ""; 
				   menu += "Informe o atributo que deseja alterar: " + '\n'; 
				   menu += "1 - Data de entrada" + '\n';
				   menu += "2 - Data de saída" + '\n';
				   menu += "3 - Hora de entrada" + '\n';
				   menu += "4 - Hora de saída" + '\n';
				   menu += "0 - Sair";

			String strOpcao = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
			case 1: {
				String data = JOptionPane.showInputDialog("Informe a nova data de entrada: [dd/mm/aaaa]");
				a.setDataEntrada(data);
				break;
			}
			case 2: {
				String data = JOptionPane.showInputDialog("Informe a nova data de saída: [dd/mm/aaaa]");
				a.setDataSaida(data);
				break;
			}
			case 3: {
				String hora = JOptionPane.showInputDialog("Informe a nova hora de entrada: [HH:mm]");
				a.setHoraEntrada_str(hora);
				a.setHoraEntrada(converterHora(hora));
				break;
			}
			case 4: {
				String hora = JOptionPane.showInputDialog("Informe a nova hora de saída: [HH:mm]");
				a.setHoraSaida_str(hora);
				a.setHoraSaida(converterHora(hora));
				break;
			}
			default:
				opcao = 0; 
			}

		} while (opcao != 0);
	}

	public static void removerAcessos(Acessos a) throws DescricaoEmBrancoException, ObjetoNaoEncontradoException {
		acs.remove(a);
		JOptionPane.showMessageDialog(null, "Remoção concluída!");
	}

	public static int converterHora(String hora) {
		String horari = hora.substring(0, 2);
		String minut = hora.substring(3, 5);
		int horario = Integer.parseInt(horari);
		int minutos = Integer.parseInt(minut);
		return (60 * horario) + minutos;
	}

	public static void relatorio(List<Acessos> acessos) {
		
		if (acessos.size() == 0) {
			JOptionPane.showMessageDialog(null, "Objeto não encontrado.");
		
		} else {
			for (Acessos a : acessos) {	
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
		}

		}
	}

}

package estacionamento;

//import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

public abstract class GerenciarAcessos {

	public static Estacionamento es = new Estacionamento();

	public static Evento eve = new Evento();

	public static Acessos a = new Acessos();

	//public static GerenciarAcessos g = new GerenciarAcessos();

	private static List<Acessos> acs = new LinkedList<Acessos>();;
	

	public GerenciarAcessos() {
	}
	/* 
	public abstract float calcularValor();

	public abstract float calcularContratante();
	*/
	
	public static void cadrastrarAcesso() throws DescricaoEmBrancoException, ValorAcessoInvalidoException {
		boolean roda = false;
		do {
			roda = false;
			Estacionamento estacionamento = null;
			String tipo = JOptionPane.showInputDialog("Informe o tipo de Estacionamento");
			for (Estacionamento es : GerenciarEstacionamento.e) {
				if(es.getTipoDeEstacionamento().equals(tipo)) {
					 estacionamento = es;
					 }
			}

			String placa = JOptionPane.showInputDialog("Informe a Placa do veÃ­culo:");
			String dataDeEntrada = JOptionPane.showInputDialog("Informe a Data de Entrada:");
			String dataDeSaida = JOptionPane.showInputDialog("Informe a Data de Saida:");
			a.setPlaca(placa);
			a.setDataEntrada(dataDeEntrada);
			a.setDataSaida(dataDeSaida);
			int evento = JOptionPane.showConfirmDialog(null, "Ã‰ do tipo Evento ?:");
			System.out.println(evento);
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
				
				Evento.criarEvento(inicioDoEvento, saidaDoEvento, taxa , true ,nomeDoEvento);

				acs.add(eve);
				
				/* 
				try {
					

				} catch (DescricaoEmBrancoException y) {
				} catch (ValorAcessoInvalidoException y) {
				}*/
			}

			else if (evento == JOptionPane.NO_OPTION) {
				int mensalista = JOptionPane.showConfirmDialog(null, "Ã‰ do tipo Mensalista ?");

				if (mensalista == JOptionPane.YES_OPTION) { // Mensalista
					a.setMensalista(true);
					String horaDeEnt = JOptionPane.showInputDialog("mInforme a hora de Entrada: (HH:mm)");
					String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");

					// String horaDeEntrada_str = Integer.toString(converterHora(horaDeEnt));
					// String horaDeSaida_str = Integer.toString(converterHora(horaDeSai));

					int horaDeEntrada = converterHora(horaDeEnt);
					int horaDeSaida = converterHora(horaDeSai);

					a.setHoraEntrada(horaDeEntrada);
					a.setHoraSaida(horaDeSaida);
					
					a = Acessos.criarAcesso(placa, dataDeEntrada, dataDeSaida, false, true, horaDeEntrada,
								horaDeSaida);
						
					acs.add(a);

					/* 
					try {
						
						converterHora(horaDeEnt);
						converterHora(horaDeSai);

					} catch (DescricaoEmBrancoException b) {
					} catch (ValorAcessoInvalidoException b) {
					}*/
				}else { // PadrÃ£o
					String horaDeEnt = JOptionPane.showInputDialog("pInforme a hora de Entrada: (HH:mm)");
					String horaDeSai = JOptionPane.showInputDialog("Informe a hora de Saida: (HH:mm)");
	
					int horaDeEntrada = converterHora(horaDeEnt);
					int horaDeSaida = converterHora(horaDeSai);
	
					int horaDeAbrir = converterHora(estacionamento.getHoraDeAbrir());
					int horaDeFechar = converterHora(estacionamento.getHoraDeFechar());
					
					a.setHoraEntrada(horaDeEntrada);
					a.setHoraSaida(horaDeSaida);
	
					if (horaDeEntrada <= horaDeAbrir || horaDeFechar <= horaDeSaida) {
						JOptionPane.showMessageDialog(null, "HorÃ¡rio InvÃ¡lido.");
						;
						atualizarAcesso();
						roda = true;
					}
					a = Acessos.criarAcesso(placa, dataDeEntrada, dataDeSaida, false, false, horaDeEntrada,
								horaDeSaida);
					System.out.println(acs.add(a));
					acs.add(a);
					/* 
					try {
						
					} catch (DescricaoEmBrancoException b) {
					}*/
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
		int info = JOptionPane.showConfirmDialog(null, "Gostaria de Atualizar suas InformaÃ§Ãµes ?");
		if (info == JOptionPane.YES_OPTION) {

		} else {
			throw new DescricaoEmBrancoException();
		}
		return roda;
	}

	public static boolean removerAcessos() throws DescricaoEmBrancoException, ObjetoNaoEncontradoException  {
		Acessos a = pesquisarAcessos();
		System.out.println(a);
		System.out.println(acs.isEmpty());
		
		boolean resposta = false;
		
		if (acs.contains(a)) {
			resposta = acs.remove(a);
			System.out.println("remove = " + resposta);
		}
		
		JOptionPane.showMessageDialog(null, "RemoÃ§Ã£o concluÃ­da!");
		
		return resposta;

		/* 
		if(a != null) {
		System.out.println(acs.remove(a));		
		}*/
		
	}
	/*
	public static boolean delAcessos() {
		return 
	}
	*/
	public static int converterHora(String hora) {
		String horari = hora.substring(0, 2);
		String minut = hora.substring(3, 5);
		int horario = Integer.parseInt(horari);
		int minutos = Integer.parseInt(minut);
		return (60 * horario) + minutos;
	}
	
	public static void relatorio(Acessos a) {
		String resposta = "Placa : " + a.getPlaca() + "\n";
		resposta += "Tipo Do Estacionamento : " + es.getTipoDeEstacionamento() + "\n";
		if(eve.getEEvento() == true){
			resposta += "Nome do Evento : " +  eve.getNomeEvento() + "\n";	
		}
		resposta += "Data de entrada - saída: " + a.getDataEntrada()+" - "+a.getDataSaida()+"\n";
		resposta += "Hora de entrada - saída: " + a.getHoraEntrada()+" - "+a.getHoraSaida()+"\n";
	
		JOptionPane.showMessageDialog(null, resposta);;
	
	}

}

package gerenciadora;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

import javax.swing.JOptionPane;

import estacionamento.*;

/*
import estacionamento.Evento;
import estacionamento.GerenciarAcessos;
import estacionamento.GerenciarEstacionamento;
 */

public class Main {
	

	public static void main(String[] args) throws DescricaoEmBrancoException, ValorAcessoInvalidoException, ObjetoNaoEncontradoException {
		
		int opcao = 0; 
		do {
			String menu = ""; 
			menu += "Informe a opcao desejada: " + '\n'; 
			menu += "1 - Cadastrar Estacioamento" + '\n';
			menu += "2 - Pesquisar Estacionamento" + '\n';
			menu += "3 - Cadastrar Acessos" + '\n';
			menu += "4 - Remover Acessos" + '\n';
			menu += "5 - Atualizar Acessos" + '\n';
			menu += "6 - Pesquisar Acessos" + '\n';
			menu += "0 - Sair";

			String strOpcao = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(strOpcao);



			
			switch (opcao) {
			case 1: {
				GerenciarEstacionamento.cadrastrarEstacionamento();
				break;
			}
			case 2: {
				GerenciarEstacionamento.relatorio(GerenciarEstacionamento.pesquisarEstacionamento());
				break;
			}
			case 3: {
				GerenciarAcessos.cadrastrarAcesso();
				break;
			}
			case 4: {
				GerenciarAcessos.removerAcessos(GerenciarAcessos.escolherAcesso());
				break;
			}
			
			case 5: {
				GerenciarAcessos.atualizarAcesso(GerenciarAcessos.escolherAcesso());
				break;
			}

			case 6: {
				GerenciarAcessos.relatorio(GerenciarAcessos.buscarAcessos());
				break;
			}

			
			default:
				opcao = 0; 
			}

		} while (opcao != 0);
	}
	}


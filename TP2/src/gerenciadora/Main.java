package gerenciadora;

import exceptions.DescricaoEmBrancoException;
import exceptions.ObjetoNaoEncontradoException;
import exceptions.ValorAcessoInvalidoException;

import javax.swing.JOptionPane;

import estacionamento.GerenciarAcessos;
import estacionamento.GerenciarEstacionamento;


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
			menu += "5 - Pesquisar Acessos";

			String strOpcao = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(strOpcao);



			
			switch (opcao) {
			case 1: {
				GerenciarEstacionamento.cadrastrarEstacionamento();
				break;
			}
			case 2: {
				GerenciarEstacionamento.pesquisarEstacionamento();
				break;
			}
			case 3: {
				GerenciarAcessos.cadrastrarAcesso();
				break;
			}
			case 4: {
				GerenciarAcessos.removerAcessos();
				break;
			}
			
			case 5: {
				GerenciarAcessos.relatorio(GerenciarAcessos.pesquisarAcessos());
				break;
			}
			
			default:
				opcao = -1; 
			}

		} while (opcao != 0);
	}
	}


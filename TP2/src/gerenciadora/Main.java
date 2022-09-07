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
			menu += "1 - Cadastrar acesso" + '\n';
			menu += "2 - Remover acesso" + '\n';
			menu += "3 - Pesquisar acesso" + '\n';
			menu += "4 - Atualizar acesso" + '\n';
			menu += "0 - Sair";

			String strOpcao = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
			case 1: {
				GerenciarEstacionamento.cadrastrarEstacionamento();
				GerenciarAcessos.cadrastrarAcesso();
				break;
			}
			case 2: {
				GerenciarAcessos.delAcessos();
				break;
			}
			case 3: {
				GerenciarAcessos.pesquisarAcessos();
				GerenciarAcessos.relatorio();
				break;
			}
			/*case 4: {
				atualizarAcessos();
				break;
			}*/
			case 0: {
				break;
			}
			default:
				opcao = -1; 
			}

		} while (opcao != 0);
	}
	}


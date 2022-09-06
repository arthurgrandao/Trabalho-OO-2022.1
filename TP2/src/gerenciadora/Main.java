package gerenciadora;

import exceptions.DescricaoEmBrancoException
import exceptions.ObjetoNaoEncontradoException
import exceptions.ValorAcessoInvalidoException

/*IMPORTANTE!!
	*(Teremos que fazer algumas modificações para funcionar)!!
	*Primeiro, teremos que criar mais dois métodos para quando formos cadastrar, o nosso addEstacionamanto deveria ser o cadastrarEstacionamento
	*e portanto o add é outro método explico melhor amanhã. O mesmo vale para o nosso cadastra caessos. Os  nossos métodos de cadastro deveriam ser os void,
	*os boolean são outros dois métodos, (não acredito que demore para arrumar isso).
	
	*Tudo isso vale para remover também, mas como eu disse não deve ser dificil.
	
	*Teremos que revisar um pouco como fazer o find funcionar
	
	*Isso que está aqui é só um esqueleto como os métodos que serão usados em cada case.
	
	*Como eu disse previamente eu explico melhor amanhã
	
*/

public class Main {

	public static void main(String[] args) {
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
				cadastrarEstacionamento();
				cadastrarAcessos();
				break;
			}
			case 2: {
				removerAcessos();
				break;
			}
			case 3: {
				findAcessos();
				break;
			}
			case 4: {
				atualizarAcessos();
				break;
			}
			case 0: {
				break;
			}
			default:
				opcao = -1; 
			}

		} while (opcao != 0);
	}
	}

}

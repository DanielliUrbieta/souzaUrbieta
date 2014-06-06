package versao4.view;

import java.io.IOException;
//import java.util.ArrayList;




import javax.swing.JOptionPane;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

import versao4.control.DepotControl;
import versao4.model.Depot;



/**
 * @author danielli
 *
 */
public class MainTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */
	@SuppressWarnings("nls")
	public static void main(String[] args) throws IOException,
			NoFilepatternException, GitAPIException {

		Depot newRepo = new Depot();
		DepotControl ctrlRepo = new DepotControl();

		/*
		 * TODO: testes estão sendo feitos para saber se o mvc fez algo parar de
		 * funcionar logo depois voltar para a estrutura de switch cases.
		 */

		// String filename;
		// String message;
		/*
		 * TODO: lembrar de criar variavel na controle ou na model, para
		 * mensagem de commit poderá ser sobrescrita com o set.
		 */


		newRepo.setLocalPath(JOptionPane
				.showInputDialog("Digite o caminho da pasta"));

		// newRepo.setRemotePath("https://github.com/DanielliUrbieta/souzaUrbieta.git");
		// filename = JOptionPane
		// .showInputDialog("Digite o nome do arquivo a ser adicionado:");

		// message = JOptionPane.showInputDialog("Mensagem do commit");
		// newRepo.setDiretoryToCopy("/home/pedro/testeMCV");

		ctrlRepo.init(newRepo);
		// ctrlRepo.clone(newRepo);
		// ctrlRepo.commit(message, newRepo);
		// ctrlRepo.add(filename, newRepo);
		// ctrlRepo.create(newRepo);

	}


}


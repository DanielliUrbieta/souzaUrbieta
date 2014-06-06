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

		int opcao;
		do {
			opcao = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
					+ "1 - Init.\n" + "2 - Create new Repository.\n"
					+ "3 - Add file.\n" + "4 - Remove file.\n"
					+ "5 - Commit.\n" + "6 - Clone Repository.\n"
					+ "7 - Show Status.\n" + "8 - Log.\n"
					+ "9 - Show Branch.\n" + "10 - Delete Branch.\n"
					+ "11 - Create Branch.\n" + "12 - Sair"));
			String localPath = null;
			String filename = null;
			String message = null;
			String remotePath = null;
			String branchName = null;

			switch (opcao) {
			case 1:

				while (localPath == null || localPath.equals(""))
					newRepo.setLocalPath(JOptionPane
							.showInputDialog("Digite o caminho da pasta"));
				ctrlRepo.init(newRepo);

				break;
			case 2:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do novo repositorio");
				// newRepo.create(localPath);
				break;

			case 3:
				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o nome do arquivo a ser adicionado");
				ctrlRepo.add(filename, newRepo);
				break;

			case 4:
				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o caminho do arquivo a ser removido");
				ctrlRepo.removeFile(filename, newRepo);
				break;

			case 5:
				while (message == null || message.equals(""))
					message = JOptionPane
							.showInputDialog("Digite a mensagem do commit");
				ctrlRepo.commit(message, newRepo);
				break;

			case 6:
				/*
				 * obs: testar método clone. o objto Depot possui atributo
				 * DiretoryToCopy que é a string do localpath para ser clonado.
				 * ou seja, aonde a pasta vai ficar.
				 */
				while (localPath == null || localPath.equals(""))
					newRepo.setDiretoryToCopy(JOptionPane
							.showInputDialog("Entre com o endereco da pasta aonde o repositorio a ser clonado ficara"));

				while (remotePath == null || remotePath.equals(""))
					newRepo.setRemotePath(JOptionPane
							.showInputDialog("Entre com o endereço do repositorio a ser clonado"));

				ctrlRepo.clone(newRepo);
				break;

			case 7:
				ctrlRepo.status(newRepo);
				break;

			case 8:
				ctrlRepo.getLogs(newRepo);
				break;

			case 9:
				ctrlRepo.showBranch(newRepo);
				break;

			case 10:

				branchName = JOptionPane
						.showInputDialog("Nome do Branch a se deletado:");
				ctrlRepo.deleteBranch(newRepo, branchName);

				break;

			case 11:
				branchName = JOptionPane
						.showInputDialog("Nome do novo Branch:");
				ctrlRepo.createBranch(newRepo, branchName);
				break;

			default:
				JOptionPane
						.showMessageDialog(null,
								"A opcao invalida.\n Escolha uma opcao invalida para continuar.");

			}
		} while (opcao != 9);

	}
}

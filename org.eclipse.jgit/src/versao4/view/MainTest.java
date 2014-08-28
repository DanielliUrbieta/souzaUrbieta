package versao4.view;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

import selectFilesAndDirectories.*;
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
		Select escolher = new Select();

		int opcao;
		do {
			opcao = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
					+ "1 - Init.\n" + "2 - Create new Repository.\n"
					+ "3 - Add file.\n" + "4 - Remove file.\n"
					+ "5 - Commit.\n" + "6 - Clone Repository.\n"
					+ "7 - Show Status.\n" + "8 - Log.\n"
					+ "9 - Show Branch.\n" + "10 - Delete Branch.\n"
					+ "11 - Create Branch.\n"
 + "12 - Diff Branch.\n"
					+ "13 - Sair"));
			String localPath = null;
			String filename = null;
			String message = null;
			String branchName = null;
			String branch1 = null;
			String branch2 = null;


			switch (opcao) {
			case 1:

				JOptionPane
						.showMessageDialog(null,
								"Escolha o diretório que deseja inicializar o repositório");
				localPath = escolher.chooseDirectory();
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);

				break;
			case 2:

				localPath = JOptionPane
							.showInputDialog("Digite o caminho do novo repositorio");
				newRepo.setLocalPath(localPath);
				ctrlRepo.create(newRepo);


				break;

			case 3:
				JOptionPane.showMessageDialog(null,
						"Escolha o arquivo a adicionar");
				File adicionar = escolher.chooseFile();
				localPath = adicionar.getParent();
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);
				filename = adicionar.getName();
				ctrlRepo.add(filename, newRepo);

				break;

			case 4:
				JOptionPane.showMessageDialog(null,
						"Escolha o arquivo a remover");
				File remover = escolher.chooseFile();
				localPath = remover.getParent();
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);
				filename = remover.getName();
				ctrlRepo.removeFile(filename, newRepo);

				break;

			case 5:
				JOptionPane.showMessageDialog(null,
 "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);

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

				JOptionPane.showMessageDialog(null,
						"Selecione a pasta aonde o repositório será clonado");
				newRepo.setDiretoryToCopy(escolher.chooseDirectory());


					newRepo.setRemotePath(JOptionPane
							.showInputDialog("Entre com o endereço do repositorio a ser clonado"));

				ctrlRepo.clone(newRepo);
				break;


			case 7:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);
				ctrlRepo.status(newRepo);
				break;

			case 8:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);
				ctrlRepo.getLogs(newRepo);
				break;

			case 9:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);
				ctrlRepo.showBranch(newRepo);
				break;

			case 10:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);

				branchName = JOptionPane
						.showInputDialog("Nome do Branch a ser removido:");
				ctrlRepo.deleteBranch(newRepo, branchName);

				break;

			case 11:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);
				branchName = JOptionPane
						.showInputDialog("Nome do novo Branch:");
				ctrlRepo.createBranch(newRepo, branchName);
				break;

			case 12:
				JOptionPane.showMessageDialog(null, "Selecione o repositório");
				newRepo.setLocalPath(escolher.chooseDirectory());
					ctrlRepo.init(newRepo);
				branch1 = JOptionPane
						.showInputDialog("Nome do primeiro Branch:");
				branch2 = JOptionPane
						.showInputDialog("Nome do segundo Branch:");
				ctrlRepo.getDiff(newRepo, branch1, branch2);
				break;

			case 14:
				System.out.println("Agora vai");
			}
		} while (opcao != 13);


	}
}

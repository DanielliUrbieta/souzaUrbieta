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
					+ "13 - Trocar de Repositorio.\n" + "14 - Sair"));
			String localPath = null;
			String filename = null;
			String message = null;
			// String remotePath = null;
			String branchName = null;
			String branch1 = null;
			String branch2 = null;
			boolean initialized = false;

			switch (opcao) {
			case 1:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Digite o caminho da pasta");
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);
				initialized = true;

				break;
			case 2:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do novo repositorio");
				newRepo.setLocalPath(localPath);
				ctrlRepo.create(newRepo);
				initialized = true;

				break;

			case 3:
				//if (initialized == false) {
				/*	localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio a adicionar o arquivo");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o nome do arquivo a ser adicionado");*/
				File adicionar = escolher.chooseFile();
				localPath = adicionar.getParent();
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);
				filename = adicionar.getName();
				ctrlRepo.add(filename, newRepo);

				break;

			case 4:

				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio do arquivo a ser removido");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}

				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o caminho o nome do arquivo a ser removido");
				ctrlRepo.removeFile(filename, newRepo);
				break;

			case 5:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio a receber o commit");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
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


					newRepo.setDiretoryToCopy(JOptionPane
							.showInputDialog("Entre com o endereco da pasta aonde o repositorio a ser clonado ficara"));


					newRepo.setRemotePath(JOptionPane
							.showInputDialog("Entre com o endereço do repositorio a ser clonado"));

				ctrlRepo.clone(newRepo);
				break;


			case 7:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio para exibir o Status");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				ctrlRepo.status(newRepo);
				break;

			case 8:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio para exibir o Log");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				ctrlRepo.getLogs(newRepo);
				break;

			case 9:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio para exibir Branch");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				ctrlRepo.showBranch(newRepo);
				break;

			case 10:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio do Branch a ser removido");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}

				branchName = JOptionPane
						.showInputDialog("Nome do Branch a ser removido:");
				ctrlRepo.deleteBranch(newRepo, branchName);

				break;

			case 11:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio do Branch a ser criado");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				branchName = JOptionPane
						.showInputDialog("Nome do novo Branch:");
				ctrlRepo.createBranch(newRepo, branchName);
				break;
			case 12:
				if (initialized == false) {
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do repositorio para exibir o Diff entren branchs");
					newRepo.setLocalPath(localPath);
					ctrlRepo.init(newRepo);
					initialized = true;
				}
				branch1 = JOptionPane
						.showInputDialog("Nome do primeiro Branch:");
				branch2 = JOptionPane
						.showInputDialog("Nome do segundo Branch:");
				ctrlRepo.getDiff(newRepo, branch1, branch2);
				break;

			case 13:
				localPath = JOptionPane
						.showInputDialog("Digite o caminho do repositorio:");
				newRepo.setLocalPath(localPath);
				ctrlRepo.init(newRepo);
				initialized = true;


			default:
				JOptionPane
						.showMessageDialog(null,
								"A opcao invalida.\n Escolha uma opcao invalida para continuar.");

			}
		} while (opcao != 14);


	}
}

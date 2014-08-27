package selectFilesAndDirectories;


import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;



import javax.swing.JFileChooser;

//import javax.swing.JOptionPane;

/**
 * @author danielli
 *
 */
public class Select {
	/**
	 * @return arquivo selecionado
	 *
	 */
	public File chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
		int retorno = fileChooser.showOpenDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			System.out.println(file.getParent());

			return file;
		} else {
			return null;
		}
	}

	/**
	 * Seleciona um diretorio e devolve seu caminho
	 * 
	 * @return caminho do diretorio
	 */
	public String chooseDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		int retorno = fileChooser.showOpenDialog(null);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			System.out.println(file.getParent());

			return file.getAbsolutePath();
		} else {
			return null;
		}
	}
}

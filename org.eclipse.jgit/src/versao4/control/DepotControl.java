package versao4.control;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import versao4.model.Depot;

/**
 * @author pedro
 *
 */
public class DepotControl implements IDepot<Depot> {

	public void init(Depot myDepot) {

		File dir = new File(myDepot.getLocalPath());
		InitCommand initCommand = Git.init();
		initCommand.setDirectory(dir);

		try {
			myDepot.setGit(initCommand.call());
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("nls")
	public void create(Depot myDepot) throws IOException {
		myDepot.setrepository(new FileRepository(myDepot.getLocalPath() + "/.git"));
		myDepot.getrepository().create();
	}

	public void add(String file, Depot myDepot) throws NoFilepatternException,
			GitAPIException {

		myDepot.getGit().add().addFilepattern(file).call();
		myDepot.setAdded(myDepot.getGit().status().call().getAdded());
		JOptionPane.showMessageDialog(null, myDepot.getAdded());
	}

	public void commit(String message, Depot myDepot) throws GitAPIException {
		RevCommit commit = myDepot.getGit().commit().setMessage(message).call();
		JOptionPane.showMessageDialog(null, commit.getId().getName());

	}

	public void removeFile(String file, Depot myDepot)
			throws NoWorkTreeException, GitAPIException {

		myDepot.getGit().rm().addFilepattern(file).call();
		myDepot.setRemoved(myDepot.getGit().status().call().getUntracked());
		if (myDepot.getRemoved() != null)
			JOptionPane.showMessageDialog(null,
					"File " + file + "successfully removed "); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void clone(Depot myDepot) throws InvalidRemoteException,
			TransportException, GitAPIException, IOException {

		File file = new File(myDepot.getDiretoryToCopy());
		Git.cloneRepository().setURI(myDepot.getRemotePath())
				.setDirectory(file).call();

		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository1 = builder.setGitDir(file).readEnvironment()
				.findGitDir().build();

		JOptionPane.showMessageDialog(null,
				"Having repository: " + repository1.getDirectory()); //$NON-NLS-1$

		repository1.close();
	}

	@SuppressWarnings("nls")
	public void status(Depot myDepot) throws NoWorkTreeException,
			GitAPIException {

		myDepot.setStatus(myDepot.getGit().status().call());

		JOptionPane.showMessageDialog(
				null,
				"Added: " + myDepot.getStatus().getAdded() + "\nChanged"
				+ myDepot.getStatus().getChanged() + "\nConflicting: "
				+ myDepot.getStatus().getConflicting()
				+ "\nConflictingStageState: "
				+ myDepot.getStatus().getConflictingStageState()
						+ "\nIgnoredNotInIndex: "
				+ myDepot.getStatus().getIgnoredNotInIndex() + "\nMissing: "
				+ myDepot.getStatus().getMissing() + "\nModified: "
				+ myDepot.getStatus().getModified() + "\nRemoved: "
				+ myDepot.getStatus().getRemoved() + "\nUntracked: "
				+ myDepot.getStatus().getUntracked() + "\nUntrackedFolders: "
				+ myDepot.getStatus().getUntrackedFolders()
						+ "\nUncommitted Changes"
				+ myDepot.getStatus().getUncommittedChanges());

		// JOptionPane.showInternalMessageDialog(null, "s", title, messageType);
	}

	public void getLogs(Depot myDepot) throws NoHeadException, GitAPIException,
			RevisionSyntaxException, AmbiguousObjectException, IOException {

		Git git1 = myDepot.init1();
		// Repository repository1 = git1.getRepository();
		//ObjectId head = repository1.resolve("HEAD"); //$NON-NLS-1$
		Iterable<RevCommit> log1 = git1.log().call();

		Iterator itr = log1.iterator();

		while (itr.hasNext()) {
			Object element = itr.next();
			RevCommit rev = (RevCommit) itr.next();
			System.out.println(element);
			System.out.println("Author: " + rev.getAuthorIdent().getName()); //$NON-NLS-1$
			System.out.println(rev.getFullMessage());
			System.out.println();
		}

	}

}

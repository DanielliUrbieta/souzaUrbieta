package versao4.control;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;

/**
 * @author peter
 * @param <Depot>
 *
 */
public interface IDepot<Depot> {

	/**
	 * @param myDepot
	 *
	 */
	public void init(Depot myDepot);


	/**
	 * @param myDepot
	 * @throws IOException
	 */
	public void create(Depot myDepot) throws IOException;

	/**
	 * @param file
	 * @param myDepot
	 * @throws NoFilepatternException
	 * @throws GitAPIException
	 * @throws IOException
	 */
	public void add(String file, Depot myDepot) throws NoFilepatternException,
			GitAPIException, IOException;

	/**
	 * @param message
	 * @param myDepot
	 * @throws GitAPIException
	 */
	public void commit(String message, Depot myDepot) throws GitAPIException;

	/**
	 * @param file
	 * @param myDepot
	 * @throws NoWorkTreeException
	 * @throws GitAPIException
	 */
	public void removeFile(String file, Depot myDepot)
			throws NoWorkTreeException, GitAPIException;

	/**
	 * @param myDepot
	 * @throws InvalidRemoteException
	 * @throws TransportException
	 * @throws GitAPIException
	 * @throws IOException
	 */
	public void clone(Depot myDepot) throws InvalidRemoteException,
			TransportException, GitAPIException, IOException;

	/**
	 * @param myDepot
	 * @throws NoWorkTreeException
	 * @throws GitAPIException
	 *
	 */
	public void status(Depot myDepot) throws NoWorkTreeException,
			GitAPIException;

	/**
	 * @param myDepot
	 * @throws NoHeadException
	 * @throws GitAPIException
	 * @throws RevisionSyntaxException
	 * @throws AmbiguousObjectException
	 * @throws IOException
	 * 
	 */
	public void getLogs(Depot myDepot) throws NoHeadException, GitAPIException,
			RevisionSyntaxException, AmbiguousObjectException, IOException;


}

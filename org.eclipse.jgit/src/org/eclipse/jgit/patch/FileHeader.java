import java.text.MessageFormat;
import org.eclipse.jgit.JGitText;
			throw new IllegalArgumentException(MessageFormat.format(JGitText.get().expectedCharacterEncodingGuesses, (getParentCount() + 1)));
			throw new RuntimeException(JGitText.get().cannotConvertScriptToText, ioe);
			throw new IllegalArgumentException(JGitText.get().hunkBelongsToAnotherFile);
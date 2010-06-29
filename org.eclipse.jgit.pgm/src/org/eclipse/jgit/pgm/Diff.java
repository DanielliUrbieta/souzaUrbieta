import org.eclipse.jgit.diff.RawTextIgnoreAllWhitespace;
import org.eclipse.jgit.diff.RawTextIgnoreLeadingWhitespace;
import org.eclipse.jgit.diff.RawTextIgnoreTrailingWhitespace;
import org.eclipse.jgit.diff.RawTextIgnoreWhitespaceChange;
@Command(common = true, usage = "usage_ShowDiffs")
	@Argument(index = 0, metaVar = "metaVar_treeish", required = true)
	@Argument(index = 1, metaVar = "metaVar_treeish", required = true)
	@Option(name = "--", metaVar = "metaVar_port", multiValued = true, handler = PathTreeFilterHandler.class)
	@Option(name = "--ignore-space-at-eol")
	private boolean ignoreWsTrailing;

	@Option(name = "--ignore-leading-space")
	private boolean ignoreWsLeading;

	@Option(name = "-b", aliases = { "--ignore-space-change" })
	private boolean ignoreWsChange;

	@Option(name = "-w", aliases = { "--ignore-all-space" })
	private boolean ignoreWsAll;

			return new RawText(new byte[] {});
		byte[] raw = db.openBlob(id).getCachedBytes();
		if (ignoreWsAll)
			return new RawTextIgnoreAllWhitespace(raw);
		else if (ignoreWsTrailing)
			return new RawTextIgnoreTrailingWhitespace(raw);
		else if (ignoreWsChange)
			return new RawTextIgnoreWhitespaceChange(raw);
		else if (ignoreWsLeading)
			return new RawTextIgnoreLeadingWhitespace(raw);
		else
			return new RawText(raw);
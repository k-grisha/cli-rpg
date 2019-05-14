package gr.prog.clirpg.view;

/**
 * View handler
 */
public interface ViewHandler {
	/**
	 * Handler of command line commands
	 *
	 * @param command command line command
	 * @return next view
	 */
	View dispatchCommand(String command);

	/**
	 * Generator of text view presentation
	 *
	 * @return text view presentation
	 */
	String getTextPresent();
}
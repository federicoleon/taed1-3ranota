package controller;

import service.DirectoryService;
import view.Principal;

public class Main {
	public static void main(String [] args) {
		/*Principal principal = new Principal();
		principal.setLocationRelativeTo(null);
		principal.setExtendedState(Principal.MAXIMIZED_BOTH);
		principal.setVisible(true);
		*/
		DirectoryService directoryService = new DirectoryService();
		directoryService.createNewDirectoryStructure("/Users/fleon/Documents/testing/2014_3rNotaAED1");
	}
}
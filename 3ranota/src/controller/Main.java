package controller;

import view.Principal;

public class Main {
	public static void main(String [] args) {
		Principal principal = new Principal();
		principal.setLocationRelativeTo(null);
		principal.setExtendedState(Principal.MAXIMIZED_BOTH);
		principal.setVisible(true);
	}
}
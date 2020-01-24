import controller.TimerController;
import view.GUI;

public class Main {

	public static void main(String[] args) {

		/**
		 * TODO:
		 * 
		 * Enable:a knappen för den spelare som är aktiv / Disable:a knappen för andra.
		 * 
		 * Visa vilken tidsenhet/på vilken klocka som är aktiv för att
		 * inkrementera/dekrementera tiden.
		 * 
		 * Om markören är i mitten ska båda timer's inkrementeras eller dekrementeras
		 * när man trycker på knappen.
		 * 
		 * Allmäna buggarfixar, kommer fram vid testning.
		 * 
		 */

		TimerController timeController = new TimerController(0, 2, 0);
		new GUI(timeController);
	}
}

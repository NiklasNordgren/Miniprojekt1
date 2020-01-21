import controller.TimerController;
import view.GUI;

public class Main {

	public static void main(String[] args) {

		TimerController timeController = new TimerController();
		new GUI(timeController);
		// timeController.start();

	}
}

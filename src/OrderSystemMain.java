import adapter.presenter.gui;
import adapter.presenter.cli;

public class OrderSystemMain {
	public static void main(String[] args) {
//		gui gui = new gui();
//		gui.startGui();
		cli cli = new cli();
		cli.menuloop();

	}
}

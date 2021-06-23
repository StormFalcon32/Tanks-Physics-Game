import java.awt.EventQueue;

import javax.swing.JFrame;

public class application extends JFrame {

	private static final long serialVersionUID = -6596113243078458151L;

	public application() {
		initUI();
	}

	private void initUI() {
		add(new main());
		setSize(600, 600);
		// JFrame size is 600 x 600, actual game field is 400 * 600
		setResizable(false);
		setTitle("Bomb Squad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				application ex = new application();
				ex.setVisible(true);
			}
		});
	}
}
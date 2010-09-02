package br.com.tg.guiUtil;

import javax.swing.JLabel;

public class StatusBarMessage {

	public static void say(String msg, final int time, final JLabel statusBar) {
 		statusBar.setText(msg);
		Runnable run = new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000 * time);
					statusBar.setText("");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(run).start();
	}
}
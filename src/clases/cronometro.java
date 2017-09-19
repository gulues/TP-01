package clases;

import javax.swing.JOptionPane;

import gui.frmMain;

public class cronometro implements Runnable {
	public static boolean cero;
	static int minutos = 0, segundos = 0, horas = 0, descuento = 20;
	Thread crono;

	public cronometro() {
		crono = new Thread(this);
		crono.start();
	}

	// para restablecer el cronometro a cero
	public void setCero(boolean b) {
		cero = b;
	}

	public void restart() {
		descuento = 20;
	}

	public void run() {
		String hs0 = "0", min0 = "0", seg0 = "0";
		try {
			while (true) {
				if (segundos == 59) {
					segundos = 0;
					minutos++;
				}
				if (minutos == 59) {
					minutos = 0;
					horas++;
				}
				segundos++;
				descuento--;

				if (horas < 10)
					hs0 = "0";
				else
					hs0 = "";
				if (minutos < 10)
					min0 = "0";
				else
					min0 = "";
				if (segundos < 10)
					seg0 = "0";
				else
					seg0 = "";

				String tiempo = hs0 + horas + ":" + min0 + minutos + ":" + seg0
						+ segundos;
				frmMain.lblTiempo.setText(tiempo);
				if (descuento < 10)
					frmMain.lblTiempoActual.setText("0" + descuento + "");
				else
					frmMain.lblTiempoActual.setText(descuento + "");

				if (descuento <= 0) {
					int dialogButton = JOptionPane
							.showConfirmDialog(
									null,
									"Perdiste!!! el tiempo se agoto, ¿intentas de nuevo?",
									"PERDISTE", 0);
					if (dialogButton == JOptionPane.YES_OPTION) {
						frmMain.set_puntos(0);
						frmMain.set_jugadores();
						restart();
					}
					if (dialogButton == JOptionPane.NO_OPTION)
						System.exit(0);

				}

				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
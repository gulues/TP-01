package clases;

import java.util.Random;

public class numeros {
	private int _numero;

	public numeros() {
		double rndNum = new Random().nextDouble();

		_numero = (int) (Math.round(rndNum * (1000) * 100) / 100);
	}

	public int get_numero() {
		return _numero;
	}

}

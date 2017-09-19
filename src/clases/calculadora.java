package clases;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class calculadora {

	private Queue<String> _datos = new LinkedList<String>();

	public void addInput(String textoEntrada) {
		_datos.clear();
		textoEntrada = textoEntrada.trim();
		int indice = 0;
		String part = "";
		while (indice != textoEntrada.length()) {
			char caracter = textoEntrada.charAt(indice);
			if (isOper("" + caracter)) {
				if (part.length() != 0) {
					_datos.add(part);
					part = "";
				}

				_datos.add("" + caracter);
			} else if (caracter == ' ' && part.length() != 0) {
				_datos.add(part);
				part = "";
			} else if (caracter != ' ') {
				part += caracter;
			}
			indice++;
		}
	}

	public void addInput(Queue<String> c) {
		_datos = new LinkedList<String>();
		_datos = c;
	}

	public double evaluar() {
		Queue<String> listaEntrada = new LinkedList<String>();
		listaEntrada.addAll(_datos);
		Stack<Double> pila = new Stack<Double>();
		while (!listaEntrada.isEmpty()) {
			String tmp = listaEntrada.remove();
			if (isOper(tmp)) {
				double val1 = pila.pop();
				double val2 = pila.pop();
				pila.push(simpleCalc(tmp.charAt(0), val2, val1));
			}

			else {
				pila.push(new Double(tmp));
			}
		}
		return pila.pop();
	}

	private boolean isOper(String str) {
		return str.equals("-") || str.equals("+") || str.equals("*")
				|| str.equals("/") || str.equals("^");
	}

	private double simpleCalc(char oper, double val1, double val2) {
		if (oper == '-') {
			val1 -= val2;
		} else if (oper == '+') {
			val1 += val2;
		} else if (oper == '/') {
			val1 /= val2;
		} else if (oper == '*') {
			val1 *= val2;
		}

		return val1;
	}

	public String toString() {
		return _datos.toString();
	}
}

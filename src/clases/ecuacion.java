package clases;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ecuacion {
	private Queue<String> _listaEntrada = new LinkedList<String>();

	public void addInput(String txtEntrada) {
		_listaEntrada.clear();
		txtEntrada = txtEntrada.trim();
		int indice = 0;
		String part = "";
		while (indice != txtEntrada.length()) {
			char cur = txtEntrada.charAt(indice);

			if (toInt("" + cur) != 2) {
				if (part.length() != 0) {
					_listaEntrada.offer(part);
					part = "";

					if (cur == '(') {
						_listaEntrada.offer("*");
					}
				}

				_listaEntrada.offer("" + cur);
			} else if (cur != ' ') {
				part += cur;
			}
			indice++;
		}

		if (part.trim().length() != 0)
			_listaEntrada.offer(part);
	}

	public Queue<String> analizarFormula() {
		Queue<String> entrada = new LinkedList<String>();
		entrada.addAll(_listaEntrada);

		Queue<String> salida = new LinkedList<String>();
		Stack<String> operadores = new Stack<String>();
		while (!entrada.isEmpty()) {
			String elem = entrada.poll();
			if (toInt(elem) == 2) // numeros
			{
				salida.offer(elem);
			} else if (operadores.isEmpty()) // sin no hay una previa operacion

			{
				operadores.push(elem);
			} else if (elem.equals("(")) // si tiene parentesis
			{
				operadores.push(elem);
			} else if (elem.equals(")")) {
				while (!operadores.peek().equals("(")) {
					salida.offer(operadores.pop());
				}
				operadores.pop();
			} else {
				while (!operadores.isEmpty() && toInt(operadores.peek()) != -3
						&& toInt(operadores.peek()) >= toInt(elem)) {
					salida.offer(operadores.pop());
				}

				operadores.push(elem);
			}

		}

		while (!operadores.isEmpty())
			salida.offer(operadores.pop());

		return salida;
	}

	private int toInt(String str) {
		int resultado;
		if (str.equals(")") || str.equals("(")) {
			resultado = -3;
		} else if (str.equals("-") || str.equals("+")) {
			resultado = -2;
		} else if (str.equals("*") || str.equals("/")) {
			resultado = -1;
		} else if (str.equals("^")) {
			resultado = 0;
		} else {
			resultado = 2;
		}
		return resultado;
	}

	public String toString() {
		return _listaEntrada.toString();
	}
}

package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import clases.calculadora;
import clases.cronometro;
import clases.ecuacion;
import clases.jugador;
import clases.numeros;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class frmMain extends JFrame {
	private static final long serialVersionUID = 1L;
	public static JLabel lblTiempo = new JLabel("tiempo: ");
	private JPanel Panel1;
	private JTextField txtFormula;
	private cronometro crono = new cronometro();
	private static int _puntos = 0;
	String numRnd;
	private static JLabel lblInfo = new JLabel();
	private static JLabel lblPuntos = new JLabel();
	private static boolean operaciones = false;
	private static ArrayList<jugador> lista = new ArrayList<jugador>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					frame.setBounds(100, 100, 460, 414);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Anti Calculadora");
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmMain() {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel1 = new JPanel();
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel1);
		Panel1.setLayout(null);
		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setForeground(Color.RED);
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(0, 28, 436, 30);
		Panel1.add(lblNumero);

		JLabel lblOperaciones = new JLabel("/");
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOperaciones.setBounds(187, 132, 50, 35);
		Panel1.add(lblOperaciones);
		lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTiempo.setBounds(0, 0, 143, 22);
		Panel1.add(lblTiempo);

		JLabel lblopDisponibles = new JLabel("Operaciones Disponibles");
		lblopDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblopDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblopDisponibles.setBounds(0, 91, 436, 30);
		Panel1.add(lblopDisponibles);

		lblInfo.setForeground(Color.RED);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblInfo.setBounds(0, 299, 436, 22);
		Panel1.add(lblInfo);

		txtFormula = new JTextField();

		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setForeground(Color.RED);
		lblPuntos.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblPuntos.setBounds(0, 340, 436, 22);
		Panel1.add(lblPuntos);
		txtFormula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFormula.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtFormula.requestFocus();
		txtFormula.setBounds(47, 184, 349, 40);
		Panel1.add(txtFormula);
		txtFormula.setColumns(10);
		crono.setCero(true);
		if (operaciones)
			lblOperaciones.setText("/   -");
		else
			lblOperaciones.setText("*   +");

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtFormula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (calcular()) {
						numRnd = generarNumero() + "";
						lblNumero.setText("Numero: " + numRnd);
						if (operaciones)
							lblOperaciones.setText("/   -");
						else
							lblOperaciones.setText("*   +");
					} else
						lblInfo.setText("La formula tiene que contener los operadores validos");
					if (operaciones)
						lblOperaciones.setText("/   -");
					else
						lblOperaciones.setText("*   +");
				}
			}
		});
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (calcular()) {
					numRnd = generarNumero() + "";
					lblNumero.setText("Numero: " + numRnd);

				} else
					lblInfo.setText("La formula tiene que contener los operadores validos");
				if (operaciones)
					lblOperaciones.setText("/   -");
				else
					lblOperaciones.setText("*   +");
			}

		});
		btnCalcular.setBounds(85, 248, 108, 40);
		Panel1.add(btnCalcular);
		numRnd = generarNumero() + "";
		lblNumero.setText("Numero: " + numRnd);

		JButton btnLogros = new JButton("Logros");
		btnLogros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogros f = new frmLogros(lista);
				f.pack();
			    f.setLocationRelativeTo(null);
			    f.setVisible(true);
			}
		});
		btnLogros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogros.setBounds(243, 248, 108, 40);
		Panel1.add(btnLogros);

	}

	private boolean calcular() {
		if (operaciones) {
			if (txtFormula.getText().contains("/")
					&& txtFormula.getText().contains("-")
					&& !txtFormula.getText().contains("+")
					&& !txtFormula.getText().contains("*"))
				operaciones = !operaciones;
			else
				return false;
		} else {
			if (txtFormula.getText().contains("*")
					&& txtFormula.getText().contains("+")
					&& !txtFormula.getText().contains("-")
					&& !txtFormula.getText().contains("/"))
				operaciones = !operaciones;
			else
				return false;
		}

		try {
			calculadora cal = new calculadora();
			ecuacion ecu = new ecuacion();
			ecu.addInput(txtFormula.getText());
			cal.addInput(ecu.analizarFormula());
			String resultado = String.format("%.0f", cal.evaluar());
			jugador j = new jugador("", 0, 0);
			lblInfo.setText(resultado);
			if (numRnd.equals(resultado)) {
				lblInfo.setText("Tu Resultado:" + resultado + " ¡Bien!");
				_puntos++;
				lblPuntos.setText("Puntos: " + _puntos);
				txtFormula.setText("");
				txtFormula.requestFocus();
				j.numero = Integer.parseInt(numRnd);
				j.respuesta = Integer.parseInt(resultado);
				j.tiempo = lblTiempo.getText();
				lista.add(j);

			} else {
				_puntos--;
				lblInfo.setText("Tu Resultado:" + resultado
						+ " La cuenta no dio");
				lblPuntos.setText("Puntos: " + _puntos);
				txtFormula.setText("");
				j.numero = Integer.parseInt(numRnd);
				j.respuesta = Integer.parseInt(resultado);
				j.tiempo = lblTiempo.getText();
				lista.add(j);

			}

		} catch (Exception ex) {
			lblInfo.setText("Error en sintaxis");
			txtFormula.requestFocus();
			return false;
		}

		return true;
	}

	private int generarNumero() {
		numeros n = new numeros();
		return n.get_numero();

	}
}
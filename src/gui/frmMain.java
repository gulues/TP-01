package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
	public static JLabel lblTiempoActual = new JLabel("tiempo: ");
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
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel1 = new JPanel();
		Panel1.setBackground(Color.LIGHT_GRAY);
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel1);
		Panel1.setLayout(null);
		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setForeground(Color.RED);
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Dialog", Font.PLAIN, 29));
		lblNumero.setBounds(120, 57, 203, 30);
		Panel1.add(lblNumero);

		txtFormula = new JTextField();
		lblPuntos.setText("Puntos:");

		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setForeground(Color.RED);
		lblPuntos.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblPuntos.setBounds(0, 340, 436, 22);
		Panel1.add(lblPuntos);
		txtFormula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFormula.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtFormula.requestFocus();
		

		JLabel lblOperaciones = new JLabel("/");
		lblOperaciones.setForeground(Color.ORANGE);
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setFont(new Font("Dialog", Font.PLAIN, 29));
		lblOperaciones.setBounds(182, 110, 81, 35);
		Panel1.add(lblOperaciones);

		JLabel lblTiempoTotal = new JLabel("Tiempo Total:");
		lblTiempoTotal.setForeground(Color.WHITE);
		lblTiempoTotal.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblTiempoTotal.setBounds(0, 0, 81, 22);
		Panel1.add(lblTiempoTotal);
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblTiempo.setBounds(85, 0, 121, 22);
		Panel1.add(lblTiempo);

		JLabel lblopDisponibles = new JLabel("Operaciones Disponibles:");
		lblopDisponibles.setForeground(Color.WHITE);
		lblopDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblopDisponibles.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblopDisponibles.setBounds(0, 83, 436, 30);
		Panel1.add(lblopDisponibles);

		lblInfo.setForeground(Color.YELLOW);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(0, 298, 436, 22);
		Panel1.add(lblInfo);
		lblTiempoActual.setForeground(Color.WHITE);

		lblTiempoActual.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblTiempoActual.setBounds(414, 0, 28, 22);
		Panel1.add(lblTiempoActual);

		JLabel lblTiempo_1 = new JLabel("Tiempo:");
		lblTiempo_1.setForeground(Color.WHITE);
		lblTiempo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTiempo_1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblTiempo_1.setBounds(293, 0, 121, 22);
		Panel1.add(lblTiempo_1);
		txtFormula.setBounds(47, 156, 349, 40);
		Panel1.add(txtFormula);
		txtFormula.setColumns(10);
		crono.setCero(true);
		if (operaciones)
			lblOperaciones.setText("/   -");
		else
			lblOperaciones.setText("*   +");

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));

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
						lblInfo.setText("La formula esta incompleta o invalida");
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
					lblInfo.setText("La formula esta incompleta o invalida");
				if (operaciones)
					lblOperaciones.setText("/   -");
				else
					lblOperaciones.setText("*   +");
			}

		});
		btnCalcular.setBounds(85, 219, 108, 40);
		Panel1.add(btnCalcular);
		numRnd = generarNumero() + "";
		lblNumero.setText("Numero: " + numRnd);

		JButton btnLogros = new JButton("Logros");
		btnLogros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogros f = new frmLogros(lista);
				f.pack();
				f.setTitle("Logros de la partida");
				f.setBounds(100, 100, 300, 300);
				f.setLocationRelativeTo(null);
				f.setResizable(false);
				f.setVisible(true);
			}
		});
		btnLogros.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		btnLogros.setBounds(239, 219, 108, 40);
		Panel1.add(btnLogros);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\bg.jpg"));
		lblNewLabel.setBounds(0, 0, 534, 421);
		Panel1.add(lblNewLabel);
		
		JLabel lbFondo = new JLabel("");
		lbFondo.setBounds(0, 0, 508, 404);

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
				lblInfo.setForeground(Color.GREEN);
				lblInfo.setText("Resultado: " + resultado + " ¡ACERTASTE!");
				_puntos++;
				lblPuntos.setText("Puntos: " + _puntos);
				txtFormula.setText("");
				txtFormula.requestFocus();
				j.numero = Integer.parseInt(numRnd);
				j.respuesta = Integer.parseInt(resultado);
				j.tiempo = lblTiempo.getText();
				lista.add(j);
				crono.restart();

			} else {
				lblInfo.setForeground(Color.RED);
				lblInfo.setText("Resultado: " + resultado + " NO ACERTASTE!!");
				lblPuntos.setText("Puntos: " + _puntos);
				txtFormula.setText("");
				j.numero = Integer.parseInt(numRnd);
				j.respuesta = Integer.parseInt(resultado);
				j.tiempo = lblTiempo.getText();
				lista.add(j);
				crono.restart();
			}

		} catch (Exception ex) {
			lblInfo.setText("Error en sintaxis");
			txtFormula.requestFocus();
			return false;
		}

		return true;
	}

	public static void set_puntos(int _puntos) {
		frmMain._puntos = _puntos;
	}

	public static void set_jugadores() {
		frmMain.lista.clear();
		;
	}

	private int generarNumero() {
		numeros n = new numeros();
		return n.get_numero();

	}
}
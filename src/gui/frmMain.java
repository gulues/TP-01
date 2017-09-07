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
import java.awt.Component;

public class frmMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel Panel1;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					frame.setBounds(100, 100, 491, 544);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Anti------------ Calculadora");
					frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					try {
						UIManager
								.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 443);
		Panel1 = new JPanel();
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel1);
		Panel1.setLayout(null);

		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNumero.setBounds(0, 11, 436, 47);
		Panel1.add(lblNumero);

		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(92, 132, 35, 41);
		Panel1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(301, 135, 35, 35);
		Panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("+");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(240, 135, 35, 35);
		Panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(156, 135, 35, 35);
		Panel1.add(lblNewLabel_3);

		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblOperaciones.setBounds(10, 95, 416, 26);
		Panel1.add(lblOperaciones);

		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setText("0");
		textField.setBounds(47, 184, 349, 40);
		Panel1.add(textField);
		textField.setColumns(10);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(156, 278, 108, 40);
		Panel1.add(btnCalcular);
	}
}
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import clases.ecuacion;
import clases.numeros;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;

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
					frame.setTitle("Anti Calculadora");
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
		lblOperaciones.setBounds(0, 69, 436, 52);
		Panel1.add(lblOperaciones);

		textField = new JTextField();
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setText("0");
		textField.setBounds(47, 184, 349, 40);
		Panel1.add(textField);
		textField.setColumns(10);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblInfo.setBounds(0, 338, 436, 52);
		Panel1.add(lblInfo);
		
		JButton btnCalcular = new JButton("Calcular");
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (!(Pattern.matches("^[-+]?[0-9]+([-+*/]+[-+]?[0-9]+)*$", textField.getText()))) {
//					lblInfo.setText("error de sintaxis");
//					}
//					else
//					{
						try{
						calculadora cal= new calculadora();
						ecuacion ecu= new ecuacion();
						ecu.addInput(textField.getText());
						cal.addInput(ecu.toPostfix());
						
						
						lblInfo.setText(String.format("%.1f",cal.evaluate()));
						if(lblNumero.getText().contains(lblInfo.getText())){
							JOptionPane.showMessageDialog(Panel1,
								    "Felicitaciones",
								    ":)",
								    JOptionPane.ERROR_MESSAGE);
							
						}else{
							JOptionPane.showMessageDialog(Panel1,
								    "La cuenta no dio, lo lamentamos!",
								    ":(",
								    JOptionPane.ERROR_MESSAGE);
						}
							
						
							
						}catch(Exception ex){
							lblInfo.setText("Error en sintaxis");
							textField.setText("");
						}
//					}
			}
		});
		btnCalcular.setBounds(156, 252, 108, 40);
		Panel1.add(btnCalcular);
		String numRnd= generarNumero() +"";
		numRnd=numRnd.replace('.', ',');
		lblNumero.setText("Numero: " +numRnd);
		
		
		
		
	}

	private double generarNumero() {
		numeros n= new numeros();
		return n.get_numero();
	}
}
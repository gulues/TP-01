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
import clases.cronometro;
import clases.ecuacion;
import clases.numeros;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmMain extends JFrame {
	private static final long serialVersionUID = 1L;
	public static JLabel lblTiempo= new JLabel("tiempo: ");
	private JPanel Panel1;
	private JTextField textField;
	private cronometro crono = new cronometro();
	
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
				| IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel1 = new JPanel();
		Panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel1);
		Panel1.setLayout(null);

		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(0, 28, 436, 30);
		Panel1.add(lblNumero);

		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(92, 132, 35, 41);
		Panel1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(301, 135, 35, 35);
		Panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("+");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(240, 135, 35, 35);
		Panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(156, 135, 35, 35);
		Panel1.add(lblNewLabel_3);

		lblTiempo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crono.setCero(true);
			}
		});
		lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTiempo.setBounds(0, 0, 143, 22);
		Panel1.add(lblTiempo);
		
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOperaciones.setBounds(0, 69, 436, 52);
		Panel1.add(lblOperaciones);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
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

	private int generarNumero() {
		numeros n= new numeros();
		return n.get_numero();
	}
}
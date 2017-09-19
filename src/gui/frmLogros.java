package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.jugador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmLogros extends JDialog {
	private JTable tblJugadores;
	public static DefaultTableModel modeloJugadores;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public frmLogros(ArrayList<jugador> lista) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		JScrollPane scrollTblJugadores = new JScrollPane();
		setBounds(100, 100, 343, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		modeloJugadores = new DefaultTableModel();
		modeloJugadores.addColumn("Numero");
		modeloJugadores.addColumn("Respuesta");
		modeloJugadores.addColumn("Tiempo");

		tblJugadores = new JTable(modeloJugadores);
		tblJugadores.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					private static final long serialVersionUID = -2865377489485540085L;

					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int col) {
						super.getTableCellRendererComponent(table, value,
								isSelected, hasFocus, row, col);
						String num1 = (String) table.getModel().getValueAt(row,
								0);
						String num2 = (String) table.getModel().getValueAt(row,
								1);
						if (num1.equals(num2)) {
							setBackground(Color.GREEN);
							setForeground(Color.BLACK);
						} else {
							setBackground(Color.RED);
							setForeground(Color.WHITE);
						}
						return this;
					}
				});

		tblJugadores.setFillsViewportHeight(true);
		scrollTblJugadores.setViewportView(tblJugadores);
		// Deshabilitar edicion de tablas
		for (int c = 0; c < tblJugadores.getColumnCount(); c++) {
			Class<?> col_class = tblJugadores.getColumnClass(c);
			tblJugadores.setDefaultEditor(col_class, null); // remove editor
		}
		refreshTable(lista);

		getContentPane().add(scrollTblJugadores);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

	}

	public static void refreshTable(ArrayList<jugador> listadoJugadores) {

		if (listadoJugadores.isEmpty())
			return;
		// Cargar modelo de jugadores
		for (int i = 0; i < modeloJugadores.getRowCount(); i++) {
			modeloJugadores.removeRow(i);
			i -= 1;
		}
		Object[] arreglo = new String[3];

		for (jugador j : listadoJugadores) {
			arreglo[0] = j.numero + "";
			arreglo[1] = j.respuesta + "";
			arreglo[2] = j.tiempo;

			modeloJugadores.addRow(arreglo);
		}

	}
}

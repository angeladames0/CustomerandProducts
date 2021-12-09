package InterfazProducto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import Conneccion.DBconexion;

public class EliminarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField Pnombre;

	private final String user = "estuditlafinal";
	private final String password = "itla123.";
	private final String url = "jdbc:mysql://db4free.net/almacenitlafinal";
	private Connection con = null;
	PreparedStatement ps;
	private JTextField Mid;

	public Connection getConection()

	{
		try {

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DBconexion.class.getName()).log(Level.SEVERE, null, ex);

		} catch (SQLException ex) {
			Logger.getLogger(DBconexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;

	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EliminarProducto frame = new EliminarProducto();
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
	public EliminarProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(107, 72, 46, 14);
		contentPane.add(nombre);

		Pnombre = new JTextField();
		Pnombre.setBounds(160, 69, 114, 20);
		contentPane.add(Pnombre);
		Pnombre.setColumns(10);

		JButton Ecancelar = new JButton("Cancelar");
		Ecancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Ecancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Ecancelar.setBounds(41, 141, 124, 36);
		contentPane.add(Ecancelar);

		JButton Eeliminar = new JButton("Eliminar");
		Eeliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Eeliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;


				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("DELETE FROM productos WHERE nombreproducto=?");
					ps.setString(1, Pnombre.getText() );

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Producto eliminado");
						limpiar();
						dispose();
						Productos.TablaAct();


					} else {

						JOptionPane.showMessageDialog(null, "Eroor al eliminar producto");
					}

					con.close();

				} catch (SQLException ex) {
					System.err.println(ex);
				}


		}

		private void limpiar() {
			Pnombre.setText("");

		}

		});
		Eeliminar.setBounds(233, 141, 124, 36);
		contentPane.add(Eeliminar);

		JLabel lblNewLabel = new JLabel("ELIMINAR PRODUCTO");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(91, 0, 198, 36);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(34, 37, 312, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 114, 312, 2);
		contentPane.add(separator_1);
	}
}

package InterfazProducto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField Mprecio;
	private JTextField Mstock;
	private JTextField Mnombre;


	private final String user = "estuditlafinal";
	private final String password = "itla123.";
	private final String url = "jdbc:mysql://db4free.net/almacenitlafinal";
	private Connection con = null;
	PreparedStatement ps;
	private JTextField Midproducto;

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
					ModificarProducto frame = new ModificarProducto();
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
	public ModificarProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Mnombre = new JTextField();
		Mnombre.setBounds(135, 80, 133, 20);
		contentPane.add(Mnombre);
		Mnombre.setColumns(10);

		Mprecio = new JTextField();
		Mprecio.setBounds(135, 100, 133, 20);
		contentPane.add(Mprecio);
		Mprecio.setColumns(10);

		Mstock = new JTextField();
		Mstock.setColumns(10);
		Mstock.setBounds(135, 120, 133, 20);
		contentPane.add(Mstock);

		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(79, 80, 46, 14);
		contentPane.add(nombre);

		JLabel usuario = new JLabel("Precio");
		usuario.setBounds(79, 100, 46, 14);
		contentPane.add(usuario);

		JLabel lblNewLabel = new JLabel("Stock");
		lblNewLabel.setBounds(79, 120, 46, 14);
		contentPane.add(lblNewLabel);



		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(44, 213, 121, 37);
		contentPane.add(btnNewButton);

		JButton btnModificar = new JButton("Actualizar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

					Connection con = null;

					PreparedStatement ps;


					try {

						con = getConection();
						ps = (PreparedStatement) con.prepareStatement("UPDATE productos SET  precioproducto=?, stockproducto=?, idproducto=? WHERE nombreproducto=?");
						ps.setString(1, Mprecio.getText());
						ps.setString(2, Mstock.getText());
						ps.setString(3, Midproducto.getText());
						ps.setString(4, Mnombre.getText());
						int res = ps.executeUpdate();

						if (res > 0) {

							JOptionPane.showMessageDialog(null, "Producto modificado con exito");
							limpiar();
							dispose();
							Productos.TablaAct();


						} else {

							JOptionPane.showMessageDialog(null, "Error al modificar producto, Verifique los datos");
						}

						con.close();

					} catch (SQLException ex) {
						System.err.println(ex);
					}


			}

			private void limpiar() {
				Mnombre.setText("");
				Mprecio.setText("");
				Mstock.setText("");

			}

		});
		btnModificar.setBounds(247, 213, 121, 37);
		contentPane.add(btnModificar);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 192, 377, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(30, 36, 377, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_4 = new JLabel("MODIFICAR PRODUCTO");
		lblNewLabel_4.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel_4.setBounds(95, 0, 224, 38);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;
				ResultSet rs;

				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("SELECT * FROM productos WHERE nombreproducto = ?");
					ps.setString(1, Mnombre.getText() );

					rs = ps.executeQuery();

					if(rs.next()) {
						Midproducto.setText(rs.getString("idproducto"));
						Mnombre.setText(rs.getString("nombreproducto"));
						Mprecio.setText(rs.getString("precioproducto"));
						Mstock.setText(rs.getString("stockproducto"));


					} else {

						JOptionPane.showMessageDialog(null, "No existe este producto");
					}

				} catch (SQLException ex) {
					System.err.println(ex);
				}


			}

		});
		btnNewButton_1.setBounds(278, 48, 89, 23);
		contentPane.add(btnNewButton_1);

		Midproducto = new JTextField();
		Midproducto.setEnabled(false);
		Midproducto.setBounds(387, 50, 37, 17);
		contentPane.add(Midproducto);
		Midproducto.setColumns(10);


	}
}

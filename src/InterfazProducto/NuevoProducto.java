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

public class NuevoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField Pnombre;
	private JTextField Pmarca;
	private JTextField Pcategoria;
	private JTextField Pprecio;
	private JTextField Pstocks;

	private final String user = "estuditlafinal";
	private final String password = "itla123.";
	private final String url = "jdbc:mysql://db4free.net/almacenitlafinal";
	private Connection con = null;
	PreparedStatement ps;

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
	 *

	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					NuevoProducto frame = new NuevoProducto();
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
	public NuevoProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(80, 72, 46, 14);
		contentPane.add(nombre);

		JLabel marca = new JLabel("Marca");
		marca.setBounds(90, 97, 46, 14);
		contentPane.add(marca);

		JLabel categoria = new JLabel("Categoria");
		categoria.setBounds(74, 122, 62, 14);
		contentPane.add(categoria);

		JLabel precio = new JLabel("Precio");
		precio.setBounds(93, 147, 46, 14);
		contentPane.add(precio);

		JLabel stock = new JLabel("Stocks");
		stock.setBounds(93, 172, 46, 14);
		contentPane.add(stock);

		Pnombre = new JTextField();
		Pnombre.setBounds(139, 69, 129, 20);
		contentPane.add(Pnombre);
		Pnombre.setColumns(10);

		Pmarca = new JTextField();
		Pmarca.setBounds(139, 94, 129, 20);
		contentPane.add(Pmarca);
		Pmarca.setColumns(10);

		Pcategoria = new JTextField();
		Pcategoria.setBounds(139, 119, 129, 20);
		contentPane.add(Pcategoria);
		Pcategoria.setColumns(10);

		Pprecio = new JTextField();
		Pprecio.setBounds(139, 144, 129, 20);
		contentPane.add(Pprecio);
		Pprecio.setColumns(10);

		Pstocks = new JTextField();
		Pstocks.setBounds(139, 169, 129, 20);
		contentPane.add(Pstocks);
		Pstocks.setColumns(10);

		JLabel lblNewLabel = new JLabel("AGREGAR PRODUCTO");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(102, 11, 189, 20);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnNewButton.setBounds(56, 211, 112, 39);
		contentPane.add(btnNewButton);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;
				ResultSet rs;

				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("INSERT INTO productos (nombreproducto, marcaproducto, categoriaproducto, precioproducto, stockproducto) VALUES (?,?,?,?,?)");
					ps.setString(1, Pnombre.getText());
					ps.setString(2, Pmarca.getText());
					ps.setString(3, Pcategoria.getText());
					ps.setString(4, Pprecio.getText());
					ps.setString(5, Pstocks.getText());

					int res = ps.executeUpdate();

					if (res > 0) {

						JOptionPane.showMessageDialog(null, "Producto Guardado");
						limpiar();
						dispose();
						Productos.TablaAct();


					} else {

						JOptionPane.showMessageDialog(null, "Error al guardar producto, Verifique los datos");
					}

					con.close();

				} catch (SQLException ex) {
					System.err.println(ex);
				}
			}

			private void limpiar() {
				Pnombre.setText("");
				Pmarca.setText("");
				Pcategoria.setText("");
				Pprecio.setText("");
				Pstocks.setText("");
			}

		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(242, 211, 112, 39);
		contentPane.add(btnGuardar);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 198, 334, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 48, 334, 2);
		contentPane.add(separator_1);
	}
}

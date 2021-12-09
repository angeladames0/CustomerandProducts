package InterfazClientes;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InterfazProducto.Productos;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Clientes");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbrirClientes();
				dispose();
			}
		});
		btnNewButton.setBounds(30, 105, 145, 53);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Productos");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbrirProductos();
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(246, 105, 137, 53);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cerrar Sesion");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CerrarSesion();
				dispose();
			}
		});
		btnNewButton_2.setBounds(306, 227, 118, 23);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("VENTANA PRINCIPAL");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(101, 11, 239, 33);
		contentPane.add(lblNewLabel);

	}

	public void AbrirProductos() {
		Productos frmProductos = new Productos();
		frmProductos.setVisible(true);
	}

	public void CerrarSesion() {
		Login frmLogin = new Login();
		frmLogin.setVisible(true);
	}

	public void AbrirClientes() {
		Clientes frmClientes = new Clientes();
		frmClientes.setVisible(true);
	}

}

package Conneccion;

public class DBregistro {

	private int iduser;
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;

	public int getId() {
		return iduser;
	}
	public void setId(int iduser) {
		this.iduser = iduser;
	}
	public String getUsuario() {
		return username;
	}
	public void setUsuario(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return email;
	}
	public void setCorreo(String email) {
		this.email = email;
	}



}

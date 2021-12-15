package uniandes.cupi2.tiendadelibros.extension;

public class Cliente {
	String cedula;
	String nombres;
	String apellidos;
	
	public Cliente(String pCedula, String pNombres, String pApellidos) {
		cedula=pCedula;
		nombres=pNombres;
	    apellidos=pApellidos;
	}
	public String getCedula() {
		return cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}

}

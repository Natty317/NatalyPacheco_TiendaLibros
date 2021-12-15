package uniandes.cupi2.tiendadelibros.extension;

public class Comentario {
	 String contenido;
	 int calificacion;
	 Cliente Cliente2;
	 
	 public Comentario(String pContenido,int pCalificacion,Cliente pCliente2) {
		 contenido=pContenido;
		 calificacion=pCalificacion;
	     Cliente2=pCliente2;
	 }
	 
	 public String getContenido() {
		 return contenido;
	 }
	 
	 public int getCalificacion() {
		 return calificacion;
	 }
	 
	 public Cliente getCliente2() {
		 return Cliente2;
	 }

}

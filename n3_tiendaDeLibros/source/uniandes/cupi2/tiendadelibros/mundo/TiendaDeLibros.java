/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot? - Colombia)
 * Departamento de Ingenier?a de Sistemas y Computaci?n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_tiendaDeLibros
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tiendadelibros.mundo;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import uniandes.cupi2.tiendadelibros.extension.*;

import java.util.ArrayList;


/**
 * Clase que representa la tienda de libros.
 */
public class TiendaDeLibros
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Catalogo de libros registrados en la tienda.
     */
    private ArrayList<Libro> catalogo;

    /**
     * Cantidad actual en la caja de la tienda de libros.
     */
    private double caja;
    private ArrayList<Cliente> VectorClientes;

    private ArrayList<Comentario> VectorComentario;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea una nueva tienda de libros. <br>
     * <b>post:</b>El catalogo de libros fue inicializado. <br>
     * La caja fue inicializada en 1000000.
     */
    public TiendaDeLibros( )
    {
        catalogo = new ArrayList<Libro>( );
        caja = 1000000;
        VectorClientes = new ArrayList<Cliente>( );

    }

    // -----------------------------------------------------------------
    // M?todos
    // -----------------------------------------------------------------

    /**
     * Retorna el cat?logo de libros.
     * @return El cat?logo de libros.
     */
    public ArrayList<Libro> darCatalogo( )
    {
        return catalogo;
    }

    /**
     * Retorna el valor actual en la caja.
     * @return El valor actual en caja.
     */
    public double darCaja( )
    {
        return caja;
    }

    /**
     * Modifica el valor actual de la caja.
     * @param pCaja El nuevo valor de la caja.
     */
    public void cambiarCaja( double pCaja )
    {
        caja = pCaja;
    }

    /**
     * Busca un libro por el t?tulo dado por par?metro.
     * @param pTitulo El titulo del libro que se quiere buscar. pTitulo != null && pTitulo != "".
     * @return Si existe un libro con ese t?tulo, lo retorna. En caso contrario, retorna null.
     */
    public Libro buscarLibroPorTitulo( String pTitulo )
    {
        Libro buscado = null;
        int i = 0;
        while( i < catalogo.size( ) && buscado == null )
        {
            Libro actual = catalogo.get( i );
            if( actual.darTitulo( ).equals( pTitulo ) )
            {
                buscado = actual;
            }
            i++;
        }
        return buscado;
    }

    /**
     * Busca un libro por el c?digo ISBN dado por par?metro.
     * @param pIsbn El c?digo ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
     * @return Si existe un libro con ese ISBN, lo retorna. En caso contrario, retorna null.
     */
    public Libro buscarLibroPorISBN( String pIsbn )
    {
        Libro buscado = null;
        for( int i = 0; i < catalogo.size( ) && buscado == null; i++ )
        {
            Libro actual = catalogo.get( i );
            if( actual.darIsbn( ).equals( pIsbn ) )
            {
                buscado = actual;
            }
        }

        return buscado;
    }

    /**
     * Registra un libro en la tienda de libros. <br>
     * <b>post: </b> El libro fue creado y agregado al cat?logo.
     * @param pTitulo El t?tulo del libro que se quiere agregar. pTitulo != null && pTitulo != "".
     * @param pIsbn El c?digo ISBN del libro que se quiere agregar. pIsbn != null && pIsbn != "".
     * @param pPrecioVenta El precio de venta del libro que se quiere agregar. pPrecioVenta > 0.
     * @param pPrecioCompra El precio de compra del libro que se quiere agregar. pPrecioCompra > 0.
     * @param pRutaImagen La ruta de la imagen del libro. pRutaImagen != null && pRutaImagen != "".
     * @return El nuevo libro registrado en caso de que si se haya podido realizar la operaci?n, null en caso de que el libro ya exista.
     */
    public Libro registrarLibro( String pTitulo, String pIsbn, double pPrecioVenta, double pPrecioCompra, String pRutaImagen )
    {
        // Comprueba si el libro con ese ISBN no ha sido creado
        Libro buscado = buscarLibroPorISBN( pIsbn );
        Libro nuevo = null;
        if( buscado == null )
        {
            nuevo = new Libro( pTitulo, pIsbn, pPrecioVenta, pPrecioCompra, pRutaImagen );
            catalogo.add( nuevo );
        }
        return nuevo;
    }

    /**
     * Elimina un libro con el ISBN dado por par?metro. Si la cantidad actual de ejemplares es mayor a cero, no se eliminar? el libro. <br>
     * <b>post: </b> El libro fue eliminado del cat?logo.
     * @param pIsbn El ISBN del libro que se quiere eliminar. pIsbn != null && pIsbn != "".
     * @return Retorna true si se pudo eliminar, false si el libro no existe o si la cantidad actual de ejemplares es mayor a cero.
     */
    public boolean eliminarLibro( String pIsbn )
    {
        boolean eliminado = false;
        Libro buscado = buscarLibroPorISBN( pIsbn );
        if( buscado != null )
        {
            if( buscado.darCantidadActual( ) == 0 )
            {
                catalogo.remove( buscado );
                eliminado = true;
            }

        }
        return eliminado;
    }

    /**
     * Abastece un libro con la cantidad de ejemplares dada por par?metro. <br>
     * <b>post: </b> Se abasteci? el libro con el ISBN dado y se disminuy? la cantidad en caja con el precio final del abastecimiento.
     * @param pIsbn El C?digo ISBN del libro que se quiere abastecer. pIsbn!= null && pISBN != "".
     * @param pFecha La fecha en la que se realiz? la transacci?n. pFecha != "" && pFecha != null.
     * @param pCantidad La cantidad de ejemplares que se van a abastecer. pCantidad >= 0.
     * @return Retorna true si se pudo abastecer el libro, false en caso contrario.
     */
    public boolean abastecer( String pIsbn, int pCantidad, String pFecha )
    {
        Libro buscado = buscarLibroPorISBN( pIsbn );
        boolean seAbastecio = false;
        if( buscado != null && caja >= pCantidad * buscado.darPrecioCompra( ) )
        {
            buscado.abastecer( pCantidad, pFecha );
            // Disminuye la caja con el valor total de los ejemplares abastecidos
            caja -= pCantidad * buscado.darPrecioCompra( );
            seAbastecio = true;
        }
        return seAbastecio;
    }

    /**
     * Vende la cantidad de ejemplares del libro con el ISBN dado por par?metro. <br>
     * <b>post: </b> Se vendi? el libro con el ISBN dado y se aument? la cantidad en caja con el precio final de la venta.
     * @param pIsbn El C?digo ISBN del libro que se quiere vender. pIsbn != null && pIsbn != "".
     * @param pCantidad La cantidad de ejemplares que se van a vender.
     * @param pFecha La fecha en la que se realiz? la transacci?n. pFecha != "" && pFecha != null.
     * @return Retorna true en caso de que se pueda vender la cantidad de ejemplares dada por par?metro. False en caso contrario.
     */
    public boolean vender( String pIsbn, int pCantidad, String pFecha )
    {
        boolean vendido = false;
        Libro buscado = buscarLibroPorISBN( pIsbn );
        if( buscado != null )
        {
            vendido = buscado.vender( pCantidad, pFecha );
            // Aumenta la caja con el valor total de los ejemplares vendidos
            caja += pCantidad * buscado.darPrecioVenta( );
        }
        return vendido;
    }

    /**
     * Busca el libro m?s costoso, es decir el libro con el mayor precio de venta en el cat?logo.
     * @return El libro m?s costoso. En caso de que el catalogo est? vac?o, retorna null
     */
    public Libro darLibroMasCostoso( )
    {
        // Guarda el libro m?s costoso y su precio
        Libro masCostoso = null;
        double precioMasCostoso = 0.0;

        for( Libro actual : catalogo )
        {
            // Verifica si el libro actual tiene un precio mayor al que est? guardado
            if( actual.darPrecioVenta( ) > precioMasCostoso )
            {
                masCostoso = actual;
                precioMasCostoso = actual.darPrecioVenta( );
            }
        }

        return masCostoso;
    }

    /**
     * Busca el libro m?s econ?mico en el cat?logo de libros. El libros m?s econ?mico es el libro con el menor precio de venta.
     * @return El libro menos costoso. En caso de que el cat?logo est? vac?o, retorna null.
     */
    public Libro darLibroMasEconomico( )
    {
        Libro menosCostoso = null;
        double precioMenosCostoso = 0.0;

        // Revisa que el cat?logo no est? creado
        if( catalogo.size( ) > 0 )
        {
            // Para comenzar el recorrido, se asume que el primer libro es el m?s econ?mico
            menosCostoso = catalogo.get( 0 );
            precioMenosCostoso = catalogo.get( 0 ).darPrecioVenta( );

            for( int i = 0; i < catalogo.size( ); i++ )
            {
                Libro actual = catalogo.get( i );
                // Pregunta si el precio del libro actual es menos costoso
                if( actual.darPrecioVenta( ) < precioMenosCostoso )
                {
                    menosCostoso = actual;
                    precioMenosCostoso = actual.darPrecioVenta( );
                }
            }
        }
        return menosCostoso;
    }

    /**
     * Busca el libro m?s vendido, es decir el libro con m?s transacciones de tipo VENTA.
     * @return El libro m?s vendido. En caso de que el cat?logo est? vac?o, retorna null.
     */
    public Libro darLibroMasVendido( )
    {
        Libro masVendido = null;
        int ventas = 0;
        // Recorre el catalogo de libros
        for( Libro libroActual : catalogo )
        {
            int ventasActual = 0;
            // Recorre las transacciones del libro actual
            for( Transaccion transaccionActual : libroActual.darTransacciones( ) )
            {
                if( transaccionActual.darTipo( ).equals( Transaccion.Tipo.VENTA ) )
                {
                    // Cuenta los libros vendidos en la transacci?n actual.
                    ventasActual += transaccionActual.darCantidad( );
                }
            }
            // Verifica que las ventas actuales sean mayores a las ventas guardadas
            if( ventasActual > ventas )
            {
                masVendido = libroActual;
                ventas = ventasActual;
            }
        }

        return masVendido;
    }

    /**
     * Calcula la cantidad de transacciones de abastecimiento del libro con el ISBN dado por par?metro.
     * @param pIsbn El c?digo ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
     * @return La cantidad de transacciones de abastecimiento. En caso de que no encuentre el libro o no tenga transacciones, retorna cero.
     */
    public int darCantidadTransaccionesAbastecimiento( String pIsbn )
    {
        // Busca el libro con el ISBN dado por par?metro
        Libro buscado = buscarLibroPorISBN( pIsbn );
        int cantidadTransacciones = 0;
        // Verifica que si exista el libro
        if( buscado != null )
        {
            // Guarda las transacciones del libro buscado
            ArrayList<Transaccion> transacciones = buscado.darTransacciones( );
            for( int i = 0; i < transacciones.size( ); i++ )
            {
                Transaccion actual = transacciones.get( i );
                // Verifica y cuenta las transacciones de tipo ABASTECIMIENTO
                if( actual.darTipo( ).equals( Transaccion.Tipo.ABASTECIMIENTO ) )
                {
                    cantidadTransacciones++;
                }
            }
        }
        return cantidadTransacciones;
    }
    
 // a. Agregar un nuevo cliente
    public Cliente agregarCliente( String pCedula, String pNombres, String pApellidos )
    {
        // Comprueba si el cliente con ese numero de cedula no ha sido registrado
        Cliente buscarCLi = buscarClientePorCI(pCedula );
        Cliente nuevoCli = null;
        if( buscarCLi == null )
        {
        	nuevoCli = new Cliente( pCedula, pNombres,pApellidos );
        	 VectorClientes.add( nuevoCli );
        }else {
        	
        	System.out.print("El cliente ya existe:");

        }
        return nuevoCli;
    }

    
    // b. M?todo para buscar un cliente dado su n?mero de c?dula
    
    public Cliente buscarClientePorCI( String pCedula )
    {
    	Cliente buscarCLi = null;
        for( int i = 0; i < VectorClientes.size( ) && buscarCLi == null; i++ )
        {
        	Cliente actual = VectorClientes.get( i );
            if( actual.getCedula( ).equals( pCedula ) )
            {
            	buscarCLi = actual;
            }
        }

        return buscarCLi;
    }
    
    // c. M?todo para calcular la calificaci?n promedio de un libro dado su ISBN,
    public double calcularCalificacion(String isbn) {
    	int calificacion=0;
    	double promedio=0;
    	Libro buscar=buscarLibroPorISBN(isbn);
    	if(buscar!=null) {
    		ArrayList<Comentario> comentarios= buscar.VectorComentario();
    		for(int i=0;i<comentarios.size();i++) {
    			calificacion=calificacion+comentarios.get(i).getCalificacion();
    		}
    		promedio= calificacion/comentarios.size();    		
    	}
    	return promedio;
	
    }
    
    // d. M?todo que calcule el n?mero total de libros del cat?logo que tienen al menos comentario
    public int NumeroTotalDeLibros( String abastecimiento )
    {
        int TotalLibros = 0;
		Transaccion actual = null;
		if( actual.darTipo( ).equals( Transaccion.Tipo.ABASTECIMIENTO ) )
        {
			TotalLibros++;
        }
		
        return TotalLibros;
    }

    // e. M?todo que agregue un nuevo comentario a un libro. Este ?ltimo m?todo recibe como par?metros el ISBN del libro, la c?dula del cliente, y el contenido y la calificaci?n del comentario.
     public Comentario AgregarComentario(String pContenido,int pCalificacion,Cliente pCliente2) {
    	 Comentario comentarios=new Comentario (pContenido,pCalificacion,pCliente2);
    	 VectorComentario.add(comentarios);
    	  
    	 return comentarios;
    	 
     }

    // -----------------------------------------------------------------
    // Puntos de Extensi?n
    // -----------------------------------------------------------------

    /**
     * M?todo para la extensi?n 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M?todo para la extensi?n 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
    	
        return "Respuesta 2";
         }
    public String toString() {
    	return "TiendaDeLibros[catalogo="+catalogo+",caja"+caja+"j";
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    

}

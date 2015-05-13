package es.uvigo.esei.daa.bean;

import java.io.Serializable;


/**
 * Esta clase se utiliza para guardar datos de paginacion
 * 
 * @author Miguel Callon
 */
@SuppressWarnings("serial")
public class PagBean implements Serializable {
	private int numPag; // Numero de pagina seleccionada
	private int numElemPag; // Numero de elementos que se muestran en una pagina
	private long numElemTotal; // Numero total de elementos
	private int firstElement;
	
	public PagBean() {
		
	}
	
	public PagBean(
			int numPag, int numElemPag, long numElemTotal) {
		this.setNumPag(numPag);
		this.numElemPag = numElemPag;
		this.setNumElemTotal(numElemTotal);
	}

	/**
	 * Return de top limit
	 * @return firstElement
	 */
	public int getFirstElement() {
		return numElemPag  
		* ( (numPag - 1) +1 );
	}

	public int getNumElemPag() {
		return numElemPag;
	}

	public void setNumElemPag(int numElemPag) {
		this.numElemPag = numElemPag;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}

	public long getNumElemTotal() {
		return numElemTotal;
	}

	public void setNumElemTotal(long numElemTotal) {
		this.numElemTotal = numElemTotal;
	}
}

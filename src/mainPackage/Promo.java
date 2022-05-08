package mainPackage;

import java.util.List;

public class Promo {
	private String denumire;
	private List<Produs> produse;
	private int reducere;
	private float pret;
	
	
	public Promo(String denumire, List<Produs> produse, int reducere) {
		this.denumire = denumire;
		this.produse = produse;
		this.reducere = reducere;
		
		this.pret = 0;
		for(var produs: produse) {
			this.pret += produs.getPret();
		}
		this.pret = (this.pret * (100 - reducere))/100;	
	}


	public String getDenumire() {
		return denumire;
	}


	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}


	public List<Produs> getProduse() {
		return produse;
	}


	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}


	public int getReducere() {
		return reducere;
	}


	public void setReducere(int reducere) {
		this.reducere = reducere;
	}


	public float getPret() {
		return pret;
	}
	
}
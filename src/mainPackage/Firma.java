package mainPackage;

import java.util.List;

public class Firma {
	private List <Produs> produse;
	private List <Promo> pachetePromotionale;
	private List <Componenta> componente;
	
	public Firma(List<Produs> produse, List<Promo> pachetePromotionale, List<Componenta> componente) {
		super();
		this.produse = produse;
		this.pachetePromotionale = pachetePromotionale;
		this.componente = componente;
	}
	
	public List<Produs> getProduse() {
		return produse;
	}
	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}
	public List<Promo> getPachetePromotionale() {
		return pachetePromotionale;
	}
	public void setPachetePromotionale(List<Promo> pachetePromotionale) {
		this.pachetePromotionale = pachetePromotionale;
	}
	public List<Componenta> getComponente() {
		return componente;
	}
	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}
	
	
}

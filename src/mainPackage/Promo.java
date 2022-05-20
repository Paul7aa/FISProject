package mainPackage;

import java.util.List;

public class Promo extends Produs{
	private String denumire;
	private List<Produs> produse;
	private int reducere;
	private float pretRedus;
	

	public Promo(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata, String denumire,
			List<Produs> produse, int reducere) {
		super(cod, pret, cantitate, cantitateComandata, cantitateLivrata);
		this.denumire = denumire;
		this.produse = produse;
		this.reducere = reducere;
		this.pretRedus = this.getPret() * reducere / 100;
	}


	public float getPretRedus() {
		return pretRedus;
	}


	public void setPretRedus(float pretRedus) {
		this.pretRedus = pretRedus;
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


	@Override
	public String toString() {
		Firma firma = new Firma();
		String produse = "\n";
		for (var produs : this.getProduse()) {
			
			for(var desktop : firma.getDesktopuri()) {
				if(produs.getCod().equals(desktop.getCod())) {
					produse += "  > Desktop: " + desktop.getDenumire() + "\n"; 
					break;
				}
			}
			
			for(var laptop : firma.getLaptopuri()) {
				if(produs.getCod().equals(laptop.getCod())) {
					produse += "  > Laptop: " + laptop.getDenumire() + "\n"; 
					break;
				}
			}
			
			for(var aux : firma.getAuxuri()) {
				if(produs.getCod().equals(aux.getCod())) {
					produse += "  > " + aux.getTip().toString() + ": " +aux.getDenumire() + "\n"; 
					break;
				}
			}
			
		}
		return "Denumire : " + denumire + "\nProduse:" + produse + "\nPret initial : " + this.getPret() + "\nReducere : " + reducere + "% \nPret redus : "
				+ pretRedus;
	}

	
}
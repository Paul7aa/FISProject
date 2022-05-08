package mainPackage;

import java.util.List;

public class Furnizor {
	private List<Componenta> componente;
	private List<Comanda> comenziPrimite;
	
	public Furnizor(List<Componenta> componente, List<Comanda> comenziPrimite) {
		super();
		this.componente = componente;
		this.comenziPrimite = comenziPrimite;
	}
	
	public List<Componenta> getComponente() {
		return componente;
	}
	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}
	public List<Comanda> getComenziPrimite() {
		return comenziPrimite;
	}
	public void setComenziPrimite(List<Comanda> comenziPrimite) {
		this.comenziPrimite = comenziPrimite;
	}
	
}

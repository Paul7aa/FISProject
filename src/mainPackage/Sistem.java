package mainPackage;
import java.util.List;

public class Sistem extends Produs{
	
	private String denumire;
	private List<Componenta>componente;
	private Garantie garantie;
	
	
	public Sistem(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata, String denumire,
			List<Componenta> componente, Garantie garantie) {
		super(cod, pret, cantitate, cantitateComandata, cantitateLivrata);
		this.denumire = denumire;
		this.componente = componente;
		this.garantie = garantie;
	}
	public Garantie getGarantie() {
		return garantie;
	}
	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public List<Componenta> getComponente() {
		return componente;
	}
	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}
	
	@Override
	public String toString() {
		String componenteSistem = "\n";
		for (var componenta : this.getComponente())
			componenteSistem += "  >" + componenta.getDenumire().toString() + "\n";
		return super.toString() + "\nDenumire : " + this.getDenumire() + "\nComponente : " + componenteSistem + "Garantie : " + this.getGarantie().getDurataLuni();
	}
	
	
}

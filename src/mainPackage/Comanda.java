package mainPackage;

import java.time.LocalDate;
import java.util.List;

public class Comanda {
	private String dataInregistrarii;
	private List<Componenta> inventar;
	private List<Integer> cantitati;
	private Furnizor furnizor;
	private StatusComanda status;
	private String observatii;
	
	public Comanda(String dataInregistrarii, List<Componenta> inventar, List<Integer>cantitati ,Furnizor furnizor, StatusComanda status,
			String observatii) {
		super();
		this.dataInregistrarii = dataInregistrarii;
		this.inventar = inventar;
		this.cantitati = cantitati;
		this.furnizor = furnizor;
		this.status = status;
		this.observatii = observatii;
	}

	public String getDataInregistrarii() {
		return dataInregistrarii;
	}

	public void setDataInregistrarii(String dataInregistrarii) {
		this.dataInregistrarii = dataInregistrarii;
	}

	public List<Componenta> getInventar() {
		return inventar;
	}

	public void setInventar(List<Componenta> inventar) {
		this.inventar = inventar;
	}

	
	public List<Integer> getCantitati() {
		return cantitati;
	}

	public void setCantitati(List<Integer> cantitati) {
		this.cantitati = cantitati;
	}

	public Furnizor getFurnizor() {
		return furnizor;
	}

	public void setFurnizor(Furnizor furnizor) {
		this.furnizor = furnizor;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public void setStatus(StatusComanda status) {
		this.status = status;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}

	@Override
	public String toString() {
		String string = "Data: " + dataInregistrarii + "\nInventar:";
		int i = 0;
		for (var component: inventar) {
			string += "\n" + cantitati.get(i++)+ " " + component.getTip() + " " + component.getCod() + " : " + component.getDenumire();
		}
		string += "\nObservatii: " + observatii;
		
		return string;
	}
	
	
}

package mainPackage;

import java.util.Calendar;
import java.util.List;

public class Comanda {
	private Calendar dataInregistrarii;
	private List<Componenta> inventar;
	private Furnizor furnizor;
	private StatusComanda status;
	private String observatii;
	
	public Comanda(Calendar dataInregistrarii, List<Componenta> inventar, Furnizor furnizor, StatusComanda status,
			String observatii) {
		super();
		this.dataInregistrarii = dataInregistrarii;
		this.inventar = inventar;
		this.furnizor = furnizor;
		this.status = status;
		this.observatii = observatii;
	}

	public Calendar getDataInregistrarii() {
		return dataInregistrarii;
	}

	public void setDataInregistrarii(Calendar dataInregistrarii) {
		this.dataInregistrarii = dataInregistrarii;
	}

	public List<Componenta> getInventar() {
		return inventar;
	}

	public void setInventar(List<Componenta> inventar) {
		this.inventar = inventar;
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
	
	
}

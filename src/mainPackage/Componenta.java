package mainPackage;

enum TipComponenta {
	PlacaDeBaza,
	Procesor,
	HDD,
	SSD,
	PlacaVideo,
	RAM,
	Carcasa,
	Sursa
}

public class Componenta {
	private TipComponenta tip;
	private String denumire;
	private String cod;
	private int nr_stoc;
	private String caracteristici;
	
	public Componenta(TipComponenta tip, String denumire, String cod, int nr_stoc, String caracteristici) {
		super();
		this.tip = tip;
		this.cod = cod;
		this.nr_stoc = nr_stoc;
		this.caracteristici = caracteristici;
		this.denumire = denumire;
	}
	
	public TipComponenta getTip() {
		return tip;
	}
	public void setTip(TipComponenta tip) {
		this.tip = tip;
	}
	
	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public int getNr_stoc() {
		return nr_stoc;
	}
	public void setNr_stoc(int nr_stoc) {
		this.nr_stoc = nr_stoc;
	}

	public String getCaracteristici() {
		return caracteristici;
	}

	public void setCaracteristici(String caracteristici) {
		this.caracteristici = caracteristici;
	}

	@Override
	public String toString() {
		return "Tip: " + tip + "\nDenumire: " + denumire + "\nCod: " + cod + "\nNr stoc: " + nr_stoc + "\nCaracteristici: " + caracteristici;
	}
	
	
}

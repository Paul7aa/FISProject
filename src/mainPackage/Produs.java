package mainPackage;

public class Produs {
	private String cod;
	private float pret;
	private int cantitate;
	private int cantitateComandata;
	private int cantitateLivrata;
	
	public Produs(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata) {
		super();
		this.cod = cod;
		this.pret = pret;
		this.cantitate = cantitate;
		this.cantitateComandata = cantitateComandata;
		this.cantitateLivrata = cantitateLivrata;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public int getCantitateComandata() {
		return cantitateComandata;
	}
	public void setCantitateComandata(int cantitateComandata) {
		this.cantitateComandata = cantitateComandata;
	}
	public int getCantitateLivrata() {
		return cantitateLivrata;
	}
	public void setCantitateLivrata(int cantitateLivrata) {
		this.cantitateLivrata = cantitateLivrata;
	}
	
}

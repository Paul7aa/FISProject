package mainPackage;

import java.util.Calendar;
import java.util.List;

public class ComandaClient {
	
	private List <Produs> produseComandate;
	private float pret;
	private StatusPlata statusPlata;
	private StatusComanda statusComanda;
	private Calendar dataPlasarii;
	
	public ComandaClient(List<Produs> produseComandate, float pret, StatusPlata statusPlata,
			StatusComanda statusComanda, Calendar dataPlasarii) {
		super();
		this.produseComandate = produseComandate;
		this.pret = pret;
		this.statusPlata = statusPlata;
		this.statusComanda = statusComanda;
		this.dataPlasarii = dataPlasarii;
	}
	public List<Produs> getProduseComandate() {
		return produseComandate;
	}
	public void setProduseComandate(List<Produs> produseComandate) {
		this.produseComandate = produseComandate;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public StatusPlata getStatusPlata() {
		return statusPlata;
	}
	public void setStatusPlata(StatusPlata statusPlata) {
		this.statusPlata = statusPlata;
	}
	public StatusComanda getStatusComanda() {
		return statusComanda;
	}
	public void setStatusComanda(StatusComanda statusComanda) {
		this.statusComanda = statusComanda;
	}
	public Calendar getDataPlasarii() {
		return dataPlasarii;
	}
	public void setDataPlasarii(Calendar dataPlasarii) {
		this.dataPlasarii = dataPlasarii;
	}
	
	
	
}

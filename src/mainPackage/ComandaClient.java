package mainPackage;

import java.time.LocalDate;
import java.util.List;

public class ComandaClient {

	private List<Produs> produseComandate;
	private float pret;
	private StatusPlata statusPlata;
	private StatusComanda statusComanda;
	private String dataPlasarii;

	public ComandaClient(List<Produs> produseComandate, float pret, StatusPlata statusPlata,
			StatusComanda statusComanda, String dataPlasarii) {
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

	public String getDataPlasarii() {
		return dataPlasarii;
	}

	public void setDataPlasarii(String dataPlasarii) {
		this.dataPlasarii = dataPlasarii;
	}

	@Override
	public String toString() {

		Firma f = new Firma();
		String produseComString = "";
		for (var produs : produseComandate) {
			for (var desktop : f.getDesktopuri()) {
				if (produs.getCod().equals(desktop.getCod())) {
					produseComString += "Desktop : " + desktop.getDenumire() + " - pret : " + desktop.getPret() + "\n";
				}
			}
			for (var laptop : f.getLaptopuri()) {
				if (produs.getCod().equals(laptop.getCod())) {
					produseComString += "Laptop : " + laptop.getDenumire() + " - pret : " + laptop.getPret() + "\n";
				}
			}
			for (var promo : f.getPromouri()) {
				if (produs.getCod().equals(promo.getCod())) {
					produseComString += "Promo : " + promo.getDenumire() + " - pret : " + promo.getPret() + "\n";
				}
			}
			for (var aux : f.getAuxuri()) {
				if (produs.getCod().equals(aux.getCod())) {
					produseComString += "Laptop : " + aux.getDenumire() + " - pret : " + aux.getPret() + "\n";
				}
			}
		}

		return "Data plasarii : " + dataPlasarii + "\nProduse Comandate : \n" + produseComString + "\nPret Total : " +
		pret + "\nStatus Plata : " + statusPlata + "\nStatus Comanda : " + statusComanda + "\nData Plasarii : " + dataPlasarii;

	}

}

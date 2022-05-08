package mainPackage;

enum TipSistemAux{
	Imprimanta,
	Copiator,
	Scanner,
	Microfon,
	Casti,
	Mouse,
	Monitor,
	Keyboard
}

public class SistemAuxiliar extends Produs{
	
	private String denumire;
	private TipSistemAux tip;
	
	public SistemAuxiliar(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata,
			String denumire, TipSistemAux tip) {
		super(cod, pret, cantitate, cantitateComandata, cantitateLivrata);
		this.denumire = denumire;
		this.tip = tip;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public TipSistemAux getTip() {
		return tip;
	}
	public void setTip(TipSistemAux tip) {
		this.tip = tip;
	}
	
	
}

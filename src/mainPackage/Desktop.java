package mainPackage;

import java.util.List;

enum TipDesktop{
	Gaming,
	Office
}

public class Desktop extends Sistem {
	private String monitor;
	private String keyboard;
	private String mouse;
	private TipDesktop tip;

	public Desktop(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata, String denumire,
			List<Componenta> componente, Garantie garantie, String monitor, String keyboard, String mouse,
			TipDesktop tip) {
		super(cod, pret, cantitate, cantitateComandata, cantitateLivrata, denumire, componente, garantie);
		this.monitor = monitor;
		this.keyboard = keyboard;
		this.mouse = mouse;
		this.tip = tip;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public TipDesktop getTip() {
		return tip;
	}

	public void setTip(TipDesktop tip) {
		this.tip = tip;
	}

	@Override
	public String toString() { 
		return "Tip: " + tip.toString() + "\n" + super.toString() + "\nMonitor : " + this.getMonitor() +  "\nKeyboard : " + this.getKeyboard() + "\nMouse : " + this.getMouse();
	}
	
	
}

package mainPackage;

import java.util.List;

enum TipLaptop{
	Mini,
	Notebook,
	Ultrabook,
	Gaming
};


public class Laptop extends Sistem{
	
	private float greutate;
	private String camera;
	private String microfon;
	private float diagonala;
	private TipLaptop tip;

	
	
	public Laptop(String cod, float pret, int cantitate, int cantitateComandata, int cantitateLivrata, String denumire,
			List<Componenta> componente, Garantie garantie, float greutate, String camera, String microfon,
			float diagonala, TipLaptop tip) {
		super(cod, pret, cantitate, cantitateComandata, cantitateLivrata, denumire, componente, garantie);
		this.greutate = greutate;
		this.camera = camera;
		this.microfon = microfon;
		this.diagonala = diagonala;
		this.tip = tip;
	}
	
	public float getGreutate() {
		return greutate;
	}
	public void setGreutate(float greutate) {
		this.greutate = greutate;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getMicrofon() {
		return microfon;
	}
	public void setMicrofon(String microfon) {
		this.microfon = microfon;
	}
	public float getDiagonala() {
		return diagonala;
	}
	public void setDiagonala(float diagonala) {
		this.diagonala = diagonala;
	}
	public TipLaptop getTip() {
		return tip;
	}
	public void setTip(TipLaptop tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "Tip: " + tip.toString() + "\n" + super.toString() + "\nGreutate: " + greutate + "\nDiagonala: " + diagonala
				+ "\nCamera: " + camera + "\nMicrofon: " + microfon;
	}
	
	public String toStringClient() {
		return "Tip: " + tip.toString() + "\n" + super.toStringClient() + "\nGreutate: " + greutate + "\nDiagonala: " + diagonala
				+ "\nCamera: " + camera + "\nMicrofon: " + microfon;
	}
}

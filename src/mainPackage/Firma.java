package mainPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

public class Firma {
	private List <Produs> produse;
	private List <Promo> pachetePromotionale;
	private List <Componenta> componente;
	
	public Firma() {
		super();
	}
	
	public List<Produs> getProduse() {
		return produse;
	}
	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}
	public List<Promo> getPachetePromotionale() {
		return pachetePromotionale;
	}
	public void setPachetePromotionale(List<Promo> pachetePromotionale) {
		this.pachetePromotionale = pachetePromotionale;
	}
	public List<Componenta> getComponente() {
		return componente;
	}
	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}
	
	
	public void TrimiteComandaFurnizor(Comanda comanda) {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString(comanda.getFurnizor().getDenumire() + "_comenzi.json");
		java.lang.reflect.Type comenziListType = new TypeToken<ArrayList<Comanda>>() {}.getType();
		
		ArrayList<Comanda> comenzi = gson.fromJson(jsonFileContent, comenziListType);
		
		try (Writer writer = new FileWriter(comanda.getFurnizor().getDenumire() + "_comenzi.json")) {
			if(comenzi == null) comenzi = new ArrayList<Comanda>();
			comenzi.add(comanda);
			gson.toJson(comenzi, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(null, error.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		}
	}
}

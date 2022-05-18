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
	private List<Produs> produse = new ArrayList<Produs>();
	private List<Desktop> desktopuri = new ArrayList<Desktop>();
	private List<Laptop> laptopuri = new ArrayList<Laptop>();
	private List<Promo> pachetePromotionale = new ArrayList<Promo>();
	private List<Componenta> componente = new ArrayList<Componenta>();
	
	public void WriteDesktop(Desktop newDesktop) {
		ReadDesktopuri();
		desktopuri.add(newDesktop);
		
		try (Writer writer = new FileWriter("firma_desktop.json")) {
			Gson gson = new Gson();
			gson.toJson(desktopuri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteLaptop(Laptop newLaptop) {
		ReadLaptopuri();
		laptopuri.add(newLaptop);
		
		try (Writer writer = new FileWriter("firma_laptop.json")) {
			Gson gson = new Gson();
			gson.toJson(laptopuri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void ReadDesktopuri() {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString("firma_desktop.json");
		java.lang.reflect.Type desktopListType = new TypeToken<ArrayList<Desktop>>() {
		}.getType();
		desktopuri = gson.fromJson(jsonFileContent, desktopListType);
	}
	
	public void ReadLaptopuri() {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString("firma_laptop.json");
		java.lang.reflect.Type laptopListType = new TypeToken<ArrayList<Laptop>>() {
		}.getType();
		laptopuri = gson.fromJson(jsonFileContent, laptopListType);
	}
	
	public void ReadComponente() {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString("firma_componente.json");
		java.lang.reflect.Type componentsListType = new TypeToken<ArrayList<Componenta>>() {
		}.getType();
		ArrayList<Componenta> componente = gson.fromJson(jsonFileContent, componentsListType);

		if (componente != null) {
			this.componente = new ArrayList<Componenta>(
					componente.stream().sorted((a, b) -> Integer.compare(a.getTip().ordinal(), b.getTip().ordinal()))
							.collect(Collectors.toList()));
		}
	}

	public void WriteComponente() {
		try (Writer writer = new FileWriter("firma_componente.json")) {
			Gson gson = new Gson();
			gson.toJson(this.getComponente(), writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void TrimiteComandaFurnizor(Comanda comanda) {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString(comanda.getFurnizor().getDenumire() + "_comenzi.json");
		java.lang.reflect.Type comenziListType = new TypeToken<ArrayList<Comanda>>() {
		}.getType();

		ArrayList<Comanda> comenzi = gson.fromJson(jsonFileContent, comenziListType);

		try (Writer writer = new FileWriter(comanda.getFurnizor().getDenumire() + "_comenzi.json")) {
			if (comenzi == null)
				comenzi = new ArrayList<Comanda>();
			comenzi.add(comanda);
			gson.toJson(comenzi, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(null, error.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Firma() {
		super();
		ReadComponente();
		ReadDesktopuri();
	}

	
	
	public List<Laptop> getLaptopuri() {
		return laptopuri;
	}

	public void setLaptopuri(List<Laptop> laptopuri) {
		this.laptopuri = laptopuri;
	}

	public List<Desktop> getDesktopuri() {
		return desktopuri;
	}

	public void setDesktopuri(List<Desktop> desktopuri) {
		this.desktopuri = desktopuri;
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
}

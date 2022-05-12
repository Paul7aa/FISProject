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

public class Furnizor {
	private String denumire;
	private List<Componenta> componente = new ArrayList<Componenta>();
	private List<Comanda> comenziPrimite = new ArrayList<Comanda>();

	public void ReadComponente() {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString(this.getDenumire() + "_componente.json");
		java.lang.reflect.Type componentsListType = new TypeToken<ArrayList<Componenta>>() {
		}.getType();
		ArrayList<Componenta> componente = gson.fromJson(jsonFileContent, componentsListType);
		
		if(componente!=null) {
			this.componente = new ArrayList<Componenta>(
			componente
			.stream()
			.sorted((a,b) -> Integer.compare(a.getTip().ordinal(), b.getTip().ordinal()))
			.collect(Collectors.toList()));
			}
	}

	public void WriteComponente() {
		try (Writer writer = new FileWriter(this.getDenumire() + "_componente.json")) {
			Gson gson = new Gson();
			gson.toJson(this.getComponente(), writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
			
	}
	
	public void ReadComenziPrimite() {
		Gson gson = new Gson();
		String jsonFileContent = Utilities.getFileString(this.getDenumire() + "_comenzi.json");
		java.lang.reflect.Type comenziListType = new TypeToken<ArrayList<Comanda>>() {
		}.getType();
		ArrayList<Comanda> comenziPrimite = gson.fromJson(jsonFileContent, comenziListType);
		this.comenziPrimite = comenziPrimite;
	}
	
	public Furnizor(String denumire) {
		super();
		this.denumire = denumire;

		// get componente from json
		ReadComponente();
		ReadComenziPrimite();
		//this.comenziPrimite = comenziPrimite;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public List<Componenta> getComponente() {
		return componente;
	}

	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}

	public List<Comanda> getComenziPrimite() {
		return comenziPrimite;
	}

	public void setComenziPrimite(List<Comanda> comenziPrimite) {
		this.comenziPrimite = comenziPrimite;
	}

}

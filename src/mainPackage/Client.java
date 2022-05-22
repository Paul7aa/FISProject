package mainPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

public class Client {

	private Firma f = new Firma();
	private List <ComandaClient> comenzi = new ArrayList<ComandaClient>();
	private List <ComandaPersonalizatClient> comenziP = new ArrayList<ComandaPersonalizatClient>();
	private List <Produs> produse = new ArrayList<Produs>();
	
	public void ReadProduse() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("client_produse.json");
			java.lang.reflect.Type produseListType = new TypeToken<ArrayList<Produs>>() {
			}.getType();
			produse = gson.fromJson(jsonFileContent, produseListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteProduse() {
		ReadProduse();

		try (Writer writer = new FileWriter("client_produse.json")) {
			Gson gson = new Gson();
			gson.toJson(produse, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	
	public void WriteProdus(Produs p) {
		ReadProduse();
		produse.add(p);
		
		try (Writer writer = new FileWriter("client_produse.json")) {
			Gson gson = new Gson();
			gson.toJson(produse, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public Client() {
		super();
		ReadProduse();
	}

	public void TrimiteComanda (ComandaClient c) {
		f.WriteComandaClient(c);
	}
	
	public void TrimiteComandaPersonalizata(ComandaPersonalizatClient c) {
		f.WriteComandaPClient(c);
	}
	
	public Firma getF() {
		return f;
	}

	public void setF(Firma f) {
		this.f = f;
	}

	public void setComenzi(List<ComandaClient> comenzi) {
		this.comenzi = comenzi;
	}

	public void setComenziP(List<ComandaPersonalizatClient> comenziP) {
		this.comenziP = comenziP;
	}

	public List<ComandaClient> getComenzi() {
		return f.getComenziClient();
	}

	public List<ComandaPersonalizatClient> getComenziP() {
		return f.getComenziPClient();
	}


	public List<Produs> getProduse() {
		return produse;
	}

	public void setProduse(List<Produs> produse) {
		this.produse = produse;
	}
	
	
	
}

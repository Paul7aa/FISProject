package mainPackage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

public class Firma {
	private List<Produs> produse = new ArrayList<Produs>();
	private List<Desktop> desktopuri = new ArrayList<Desktop>();
	private List<Laptop> laptopuri = new ArrayList<Laptop>();
	private List<SistemAuxiliar> auxuri = new ArrayList<SistemAuxiliar>();
	private List<Promo> promouri = new ArrayList<Promo>();
	private List<Componenta> componente = new ArrayList<Componenta>();
	private List<ComandaClient> comenziClient = new ArrayList<ComandaClient>();
	private List<ComandaPersonalizatClient> comenziPClient = new ArrayList<ComandaPersonalizatClient>();

	public boolean ProductExists(String codX) {
		try {
			ReadProduse();
			for (var produs : produse) {
				if (produs.getCod().toLowerCase().equals(codX.toLowerCase()))
					return true;
			}

			return false;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
			return true;
		}
	}

	public void DeleteDesktop(Desktop deletedDesktop) {

		try {
			ReadDesktopuri();

			if (desktopuri == null)
				return;

			for (var desktop : desktopuri) {
				if (desktop.getCod().equals(deletedDesktop.getCod())) {
					desktopuri.remove(desktop);
					break;
				}
			}

			WriteDesktopuri();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare la stergere desktop",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void DeleteLaptop(Laptop deletedLaptop) {
		ReadLaptopuri();

		try {

			if (laptopuri == null)
				return;

			for (var laptop : laptopuri) {
				if (laptop.getCod().equals(deletedLaptop.getCod())) {
					laptopuri.remove(laptop);
					break;
				}
			}
			
			WriteLaptopuri();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare la stergere laptop",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void DeleteAux(SistemAuxiliar deletedAux) {
		ReadAux();
		try {

			if (auxuri == null)
				return;

			for (var aux : auxuri) {
				if (aux.getCod().equals(deletedAux.getCod())) {
					auxuri.remove(aux);
					break;
				}
			}
			
			WriteAuxuri();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare la stergere laptop",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void DeletePromo(Promo deletedPromo) {
		ReadPromo();
		try {

			if (promouri == null)
				return;

			for (var promo : promouri) {
				if (promo.getCod().equals(deletedPromo.getCod())) {
					promouri.remove(promo);
					break;
				}
			}
			
			WritePromouri();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare la stergere laptop",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
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

	public void WriteDesktopuri() {
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

	public void WriteLaptopuri() {

		try (Writer writer = new FileWriter("firma_laptop.json")) {
			Gson gson = new Gson();
			gson.toJson(laptopuri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void WriteAux(SistemAuxiliar newAux) {
		ReadAux();
		auxuri.add(newAux);

		try (Writer writer = new FileWriter("firma_aux.json")) {
			Gson gson = new Gson();
			gson.toJson(auxuri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void WriteAuxuri() {

		try (Writer writer = new FileWriter("firma_aux.json")) {
			Gson gson = new Gson();
			gson.toJson(auxuri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WritePromo(Promo newPromo) {
		ReadPromo();
		promouri.add(newPromo);

		try (Writer writer = new FileWriter("firma_promo.json")) {
			Gson gson = new Gson();
			gson.toJson(promouri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void WritePromouri() {

		try (Writer writer = new FileWriter("firma_promo.json")) {
			Gson gson = new Gson();
			gson.toJson(promouri, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteComenziClient() {

		try (Writer writer = new FileWriter("firma_comenzi.json")) {
			Gson gson = new Gson();
			gson.toJson(comenziClient, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteComandaClient(ComandaClient newComandaClient) {
		ReadComenziClient();
		comenziClient.add(newComandaClient);

		try (Writer writer = new FileWriter("firma_comenzi.json")) {
			Gson gson = new Gson();
			gson.toJson(comenziClient, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteComenziPClient() {

		try (Writer writer = new FileWriter("firma_comenziP.json")) {
			Gson gson = new Gson();
			gson.toJson(comenziPClient, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void WriteComandaPClient(ComandaPersonalizatClient newComandaClient) {
		ReadComenziPClient();
		comenziPClient.add(newComandaClient);

		try (Writer writer = new FileWriter("firma_comenziP.json")) {
			Gson gson = new Gson();
			gson.toJson(comenziPClient, writer);
		} catch (JsonIOException | IOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void ReadProduse() {
		ReadAux();
		ReadPromo();
		ReadDesktopuri();
		ReadLaptopuri();

		produse = Stream.concat(desktopuri.stream(), laptopuri.stream()).collect(Collectors.toList());
		produse = Stream.concat(produse.stream(), auxuri.stream()).collect(Collectors.toList());
		produse = Stream.concat(produse.stream(), promouri.stream()).collect(Collectors.toList());
	}

	public void ReadAux() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_aux.json");
			java.lang.reflect.Type auxuriListType = new TypeToken<ArrayList<SistemAuxiliar>>() {
			}.getType();
			auxuri = gson.fromJson(jsonFileContent, auxuriListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void ReadPromo() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_promo.json");
			java.lang.reflect.Type promouriListType = new TypeToken<ArrayList<Promo>>() {
			}.getType();
			promouri = gson.fromJson(jsonFileContent, promouriListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void ReadDesktopuri() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_desktop.json");
			java.lang.reflect.Type desktopListType = new TypeToken<ArrayList<Desktop>>() {
			}.getType();
			desktopuri = gson.fromJson(jsonFileContent, desktopListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}

	public void ReadLaptopuri() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_laptop.json");
			java.lang.reflect.Type laptopListType = new TypeToken<ArrayList<Laptop>>() {
			}.getType();
			laptopuri = gson.fromJson(jsonFileContent, laptopListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void ReadComenziClient() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_comenzi.json");
			java.lang.reflect.Type comenziListType = new TypeToken<ArrayList<ComandaClient>>() {
			}.getType();
			comenziClient = gson.fromJson(jsonFileContent, comenziListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}
	
	public void ReadComenziPClient() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_comenziP.json");
			java.lang.reflect.Type comenziPListType = new TypeToken<ArrayList<ComandaPersonalizatClient>>() {
			}.getType();
			comenziPClient = gson.fromJson(jsonFileContent, comenziPListType);
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
		}
	}



	public void ReadComponente() {
		try {
			Gson gson = new Gson();
			String jsonFileContent = Utilities.getFileString("firma_componente.json");
			java.lang.reflect.Type componentsListType = new TypeToken<ArrayList<Componenta>>() {
			}.getType();
			ArrayList<Componenta> componente = gson.fromJson(jsonFileContent, componentsListType);

			if (componente != null) {
				this.componente = new ArrayList<Componenta>(componente.stream()
						.sorted((a, b) -> Integer.compare(a.getTip().ordinal(), b.getTip().ordinal()))
						.collect(Collectors.toList()));
			}
		} catch (JsonIOException error) {
			JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
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
	
	public void AcceptaComanda(int i) {
		ReadComenziClient();
		
		ComandaClient comandaClient = comenziClient.get(i);
		
		for (var produs : comandaClient.getProduseComandate()) {
			for(var desktop : getDesktopuri()) {
				if(produs.getCod().equals(desktop.getCod())) {
					desktop.setCantitateLivrata(desktop.getCantitateLivrata() - 1);
					desktop.setCantitate(desktop.getCantitate()-1);
					desktop.setCantitateLivrata(desktop.getCantitateLivrata() + 1);
				}
			}
			for(var laptop : getLaptopuri()) {
				if(produs.getCod().equals(laptop.getCod())) {
					laptop.setCantitateLivrata(laptop.getCantitateLivrata() - 1);
					laptop.setCantitate(laptop.getCantitate()-1);
					laptop.setCantitateLivrata(laptop.getCantitateLivrata() + 1);
				}
			}
			for(var promo : getPromouri()) {
				if(produs.getCod().equals(promo.getCod())) {
					promo.setCantitateLivrata(promo.getCantitateLivrata() - 1);
					promo.setCantitate(promo.getCantitate()-1);
					promo.setCantitateLivrata(promo.getCantitateLivrata() + 1);
				}
			}
			for(var aux: getAuxuri()) {
				if(produs.getCod().equals(aux.getCod())) {
					aux.setCantitateLivrata(aux.getCantitateLivrata() - 1);
					aux.setCantitate(aux.getCantitate()-1);
					aux.setCantitateLivrata(aux.getCantitateLivrata() + 1);
				}
			}
		}
	
		WriteDesktopuri();
		WriteLaptopuri();
		WritePromouri();
		WriteAuxuri();
		Client c = new Client();
		
		
		for(var produs : comandaClient.getProduseComandate()) {
			c.WriteProdus(produs);
		}
		
		comenziClient.get(i).setStatusComanda(StatusComanda.Livrata);
		comenziClient.get(i).setStatusPlata(StatusPlata.Platit);
		
		WriteComenziClient();
	}
	
	public void AcceptaComandaP(int i) {
		ReadComenziPClient();
		
		ComandaPersonalizatClient comandaPClient = comenziPClient.get(i);
		
		for(var componenta : comandaPClient.getComponente()) {
			for(var componentaFirma : getComponente()) {
				if(componentaFirma.getCod().equals(componenta.getCod()))
					componentaFirma.setNr_stoc(componentaFirma.getNr_stoc()-1);
			}
		}
		
		WriteComponente();
		
		comenziPClient.get(i).setStatusComanda(StatusComanda.Livrata);
		comenziPClient.get(i).setStatusPlata(StatusPlata.Platit);
		
		WriteComenziPClient();
	}

	public Firma() {
		super();
		ReadComponente();
		ReadDesktopuri();
		ReadAux();
		ReadProduse();
	}

	
	
	public List<SistemAuxiliar> getAuxuri() {
		return auxuri;
	}

	public void setAuxuri(List<SistemAuxiliar> auxuri) {
		this.auxuri = auxuri;
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

	public List<Promo> getPromouri() {
		return promouri;
	}

	public void setPromouri(List<Promo> promouri) {
		this.promouri = promouri;
	}

	public List<Componenta> getComponente() {
		return componente;
	}

	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}

	public List<ComandaClient> getComenziClient() {
		return comenziClient;
	}

	public void setComenziClient(List<ComandaClient> comenziClient) {
		this.comenziClient = comenziClient;
	}

	public List<ComandaPersonalizatClient> getComenziPClient() {
		return comenziPClient;
	}

	public void setComenziPClient(List<ComandaPersonalizatClient> comenziPClient) {
		this.comenziPClient = comenziPClient;
	}
	
	
}

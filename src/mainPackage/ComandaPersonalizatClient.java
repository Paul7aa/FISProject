package mainPackage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComandaPersonalizatClient extends ComandaClient{

	public List<Componenta> componente= new ArrayList<Componenta>();
	
	public ComandaPersonalizatClient(List<Produs> produseComandate, float pret, StatusPlata statusPlata,
			StatusComanda statusComanda, String dataPlasarii, List<Componenta> componente) {
		super(produseComandate, pret, statusPlata, statusComanda, dataPlasarii);
		this.componente = componente;

	}


	public List<Componenta> getComponente() {
		return componente;
	}


	public void setComponente(List<Componenta> componente) {
		this.componente = componente;
	}



	@Override
	public String toString() {
		String componennn = "";
		for(var component : componente)
			componennn+=component.getDenumire()+ "\n";
		return componennn;
	}
	
	
	
	
}

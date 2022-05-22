package mainPackage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

enum StatusGarantie{
	Activ,
	Inactiv
}

public class Garantie {

	private String dataStart;
	private int durataLuni;
	private StatusGarantie status;
	private int nrReturnat = 0;
	List <Componenta> faultyComp;
	
	public Garantie(int durataLuni, StatusGarantie status, List<Componenta> faultyComp) {
		super();
		this.dataStart = LocalDate.now().toString();
		this.durataLuni = durataLuni;
		this.status = status;
		this.faultyComp = faultyComp;
	}
	
	public String getDataStart() {
		return dataStart;
	}
	public void setDataStart(String dataStart) {
		this.dataStart = dataStart;
	}
	public int getDurataLuni() {
		return durataLuni;
	}
	public void setDurataLuni(int durataLuni) {
		this.durataLuni = durataLuni;
	}
	public StatusGarantie getStatus() {
		return status;
	}
	public void setStatus(StatusGarantie status) {
		this.status = status;
	}
	public List<Componenta> getFaultyComp() {
		return faultyComp;
	}
	public void setFaultyComp(List<Componenta> faultyComp) {
		this.faultyComp = faultyComp;
	}

	public int getNrReturnat() {
		return nrReturnat;
	}

	public void setNrReturnat(int nrReturnat) {
		this.nrReturnat = nrReturnat;
	}
	
	
}

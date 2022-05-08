package mainPackage;

import java.util.Calendar;
import java.util.List;

enum StatusGarantie{
	Activ,
	Inactiv
}

public class Garantie {
	private Calendar dataStart;
	private int durataLuni;
	private StatusGarantie status;
	List <Componenta> faultyComp;
	
	public Garantie(Calendar dataStart, int durataLuni, StatusGarantie status, List<Componenta> faultyComp) {
		super();
		this.dataStart = dataStart;
		this.durataLuni = durataLuni;
		this.status = status;
		this.faultyComp = faultyComp;
	}
	
	public Calendar getDataStart() {
		return dataStart;
	}
	public void setDataStart(Calendar dataStart) {
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
	
	
}

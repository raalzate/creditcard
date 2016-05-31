package co.com.techandsolve.creditcard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="card")
@NamedQueries({
    @NamedQuery(name="Card.findAll",
                query="SELECT p FROM Card p WHERE p.cedula = :cedula"),
    @NamedQuery(name="Card.updateBonus",
                query="UPDATE Card SET bonus = :bonus WHERE p.cedula = :cedula And mount > 1000000"),
}) 
public class Card {
	
	@Id
	private int id;
	
	@NotNull(message="label requerido")
	private String label;
	
	@NotNull(message="cedula requerida")
	private String cedula;
	
	@Column(name="amount")
	@NotNull(message="monto requerida")
	private double mount;
	
	private double bonus;
	
	@NotNull(message="fecha de corte requerida")
	@Column(name="cut_date")
	private String dateCut;
	
	
	public String getDateCut() {
		return dateCut;
	}
	public void setDateCut(String dateCut) {
		this.dateCut = dateCut;
	}
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getMount() {
		return mount;
	}
	public void setMount(double mount) {
		this.mount = mount;
	}
	
	
}

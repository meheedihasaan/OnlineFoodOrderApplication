package foodOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.context.annotation.Primary;

import lombok.Data;

@Data
@Entity
public class MenuOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date placedAt;

	@NotBlank(message="Delivery Name is required.")
	private String deliveryName;
	
	@NotBlank(message="Street is required.")
	private String deliveryStreet;
	
	@NotBlank(message="City is required.")
	private String deliveryCity;
	
	@NotBlank(message="State is required.")
	private String deliveryState;
	
	@NotBlank(message="Zip Code is required.")
	private String deliveryZip;
	
	//@CreditCardNumber(message="Please enter a valid Credit Card Number")
	private String ccNumber;
	
	//@Pattern(regexp="^(0[1-9] | 1[0-2]) ([\\/]) ([2-9] [0-9])$")
	private String ccExpiration;
	
	//@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Menu> menus = new ArrayList<>();
	
	public MenuOrder() {
		
	}

	public MenuOrder(long id, Date placedAt, String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState,
			String deliveryZip, String ccNumber, String ccExpiration, String ccCVV, List<Menu> menus) {
		super();
		this.id = id;
		this.placedAt = placedAt;
		this.deliveryName = deliveryName;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryState = deliveryState;
		this.deliveryZip = deliveryZip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.menus = menus;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getPlacedAt() {
		return placedAt;
	}
	
	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryZip() {
		return deliveryZip;
	}

	public void setDeliveryZip(String deliveryZip) {
		this.deliveryZip = deliveryZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
	@Override
	public String toString() {
		return "MenuOrder [deliveryName=" + deliveryName + ", deliveryStreet=" + deliveryStreet + ", deliveryCity="
				+ deliveryCity + ", deliveryState=" + deliveryState + ", deliveryZip=" + deliveryZip + ", ccNumber="
				+ ccNumber + ", ccExpiration=" + ccExpiration + ", ccCVV=" + ccCVV + ", menus=" + menus + "]";
	}

	//Add Food Menu to menus
	public void addMenu(Menu menu) {
		this.menus.add(menu);
	}
	
}

package foodOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
@Entity
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date createdAt;

	@NotBlank(message="Name field can not be null.")
	@Size(min=5, message="Name must be at least 5 characters long.")
	private String name;
	
//	@NotNull
//	@Size(min=1, message="Your must choose at least 1 ingredient.")
	@ManyToMany
	private List<Ingredient> ingredients = new ArrayList<>();
	
	
	public Menu() {
		
	}
	
	public Menu(long id, Date createdAt, String name, List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
		this.ingredients = ingredients;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", ingredients=" + ingredients + "]";
	}
	
	
}

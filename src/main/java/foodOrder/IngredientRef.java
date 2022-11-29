package foodOrder;

import lombok.Data;

@Data
public class IngredientRef {

	private String ingredient;
	
	public IngredientRef() {
		
	}
	
	public IngredientRef(String ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	
}

package foodOrder.data;

import java.util.List;
import java.util.Optional;

import foodOrder.Ingredient;

public interface IngredientRepository {
	
	//To get all the ingredients
	List<Ingredient> findAll();
	
	//Search an ingredient by id
	Ingredient findById(String id);
	
	//Save Ingredient
	Ingredient save(Ingredient ingredient);
	
}

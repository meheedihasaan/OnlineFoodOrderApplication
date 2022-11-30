package foodOrder.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import foodOrder.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
	//To get all the ingredients
	List<Ingredient> findAll();
	
	//Search an ingredient by id
	//Optional<Ingredient> findById(String id);
	
	//Save Ingredient
	//Ingredient save(Ingredient ingredient);
	
}

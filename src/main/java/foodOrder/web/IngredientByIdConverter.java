package foodOrder.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import foodOrder.Ingredient;
import foodOrder.Ingredient.Type;
import foodOrder.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

//	public Map<String, Ingredient> ingredientMap = new HashMap<>();
//	public IngredientByIdConverter() {
//		ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//		ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//		ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//		ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//		ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatos", Type.VEGGIES));
//		ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//		ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
//		ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//		ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
//		ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//	}
	
	private IngredientRepository ingredientRepo;
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@Override
	public Ingredient convert(String id) {
		//return ingredientMap.get(id);
		return ingredientRepo.findById(id);
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

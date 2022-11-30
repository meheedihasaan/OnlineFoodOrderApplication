package foodOrder.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import foodOrder.Ingredient;
import foodOrder.Menu;
import foodOrder.MenuOrder;
import foodOrder.data.IngredientRepository;
import foodOrder.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("menuOrder")
public class DesignMenuController {
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignMenuController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//			new Ingredient("TMTO", "Diced Tomatos", Type.VEGGIES),
//			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//			new Ingredient("CHED", "Cheddar", Type.CHEESE),
//			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//			new Ingredient("SLSA", "Salsa", Type.SAUCE),
//			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//		);
		
		List<Ingredient> ingredients = ingredientRepo.findAll();		
		Type[] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name="menuOrder")
	public MenuOrder order() {
		return new MenuOrder();
	}
	
	@ModelAttribute(name="menu")
	public Menu menu() {
		return new Menu();
	}
	
	//Show Menu Design Form Handler
	@GetMapping
	public String showDesignForm(@ModelAttribute("menu") Menu menu) {
		return "design";
	}
	
	//Method for filtering Menu by Type
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
		return ingredients
				.stream()
				.filter(x->x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	
	//Process Menu Handler
	@PostMapping
	public String processMenu(@Valid @ModelAttribute("menu") Menu menu, BindingResult result, @ModelAttribute MenuOrder menuOrder) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "design";
		}
		
		menuOrder.addMenu(menu);
		System.out.println(menu.toString());
		return "redirect:/orders/current";
	}
	
	
}

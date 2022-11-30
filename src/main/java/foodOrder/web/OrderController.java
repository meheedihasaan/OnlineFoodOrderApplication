package foodOrder.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import foodOrder.MenuOrder;
import foodOrder.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("menuOrder")
public class OrderController {
	
	private OrderRepository orderRepo;
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(@ModelAttribute("menuOrder") MenuOrder menuOrder) {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid @ModelAttribute("menuOrder") MenuOrder menuOrder, Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		menuOrder.setPlacedAt(new Date());
		
		orderRepo.save(menuOrder);
		
		System.out.println(menuOrder.toString());
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
}

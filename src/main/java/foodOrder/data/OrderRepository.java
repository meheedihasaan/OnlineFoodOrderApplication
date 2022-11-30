package foodOrder.data;

import org.springframework.data.repository.CrudRepository;

import foodOrder.MenuOrder;

public interface OrderRepository extends CrudRepository<MenuOrder, Long> {

	//MenuOrder save(MenuOrder order);
	
}

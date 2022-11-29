package foodOrder.data;

import foodOrder.MenuOrder;

public interface OrderRepository {

	MenuOrder save(MenuOrder order);
	
}

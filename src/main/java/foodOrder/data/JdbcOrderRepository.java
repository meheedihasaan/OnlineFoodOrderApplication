package foodOrder.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import foodOrder.Ingredient;
import foodOrder.IngredientRef;
import foodOrder.Menu;
import foodOrder.MenuOrder;

public class JdbcOrderRepository implements OrderRepository {
	
	private JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	@Transactional
	public MenuOrder save(MenuOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"INSERT INTO Menu_Order (delivery_name, delivery_street, dellivery_city, delivery_state,"
			+ " delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) VALUES (?,?,?,?,?,?,?,?,?)",
			Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
			Types.VARCHAR, Types.VARBINARY, Types.TIMESTAMP			
		);
		
		pscf.setReturnGeneratedKeys(true);
		
		order.setPlacedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
			Arrays.asList(
				order.getDeliveryName(),
				order.getDeliveryStreet(),
				order.getDeliveryCity(),
				order.getDeliveryState(),
				order.getDeliveryZip(),
				order.getCcNumber(),
				order.getCcExpiration(),
				order.getCcCVV()
			)
		);
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);
		
		List<Menu> menus = order.getMenus();
		int i = 0;
		for(Menu menu : menus) {
			saveMenu(orderId, i++, menu);
		}
		return order;
	}
	
	private long saveMenu(long orderId, int orderKey, Menu menu) {
		menu.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
			"INSERT INTO Menu (name, created_at, menu_order, menu_order_key) VALUES(?,?,?,?)",
			Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
		);
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc =  pscf.newPreparedStatementCreator(
			Arrays.asList(
				menu.getName(),
				menu.getCreatedAt(),
				orderId,
				orderKey
			)
		);
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long menuId = keyHolder.getKey().longValue();
		menu.setId(menuId);
		
		saveIngredientRefs(menuId, menu.getIngredients());
		
		return menuId;
	}
	
	private void saveIngredientRefs(long menuId, List<Ingredient> ingredients) {
		int key = 0;
		for(Ingredient ingredient : ingredients) {
			jdbcOperations.update(
				"INSERT INTO Ingredient_Ref (ingredient, menu, menu_key) VALUES(?,?,?)",
				ingredient.getName(), menuId, key++
			);
		}
	}

}

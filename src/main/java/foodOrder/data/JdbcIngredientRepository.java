package foodOrder.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import foodOrder.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Ingredient> findAll() {
		return jdbcTemplate.query(
			"SELECT id, name, type from Ingredient",
			this::mapRowToIngredient
			);
	}

	@Override
	public Ingredient findById(String id) {
		List<Ingredient> results = jdbcTemplate.query(
			"SELECT id, name, type from Ingredient WHERE id = ?",
			this::mapRowToIngredient,
			id
			);
		
		if(results.size() == 0) {
			return null;
		}
		return results.get(0);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update(
			"INSERT INTO Ingredient (id, name, type) values (?, ?, ?, ?)",
			ingredient.getId(),
			ingredient.getName(),
			ingredient.getType().toString()
			);
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
		return new Ingredient(
			row.getString("id"),
			row.getString("name"),
			Ingredient.Type.valueOf(row.getString("type"))
			);
	}
	
	
	
}

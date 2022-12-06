package foodOrder.data;

import org.springframework.data.repository.CrudRepository;

import foodOrder.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}

package pollub.cs.ptrwrbl.lab9.repositories;

import org.springframework.data.repository.CrudRepository;
import pollub.cs.ptrwrbl.lab9.models.UserDAO;

public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
}

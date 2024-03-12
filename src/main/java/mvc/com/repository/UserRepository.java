package mvc.com.repository;

import com.tms.JPA.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { //ентити и тип айдишки оттуда

}

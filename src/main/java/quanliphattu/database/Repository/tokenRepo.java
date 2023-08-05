package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanliphattu.database.models.token;
@Repository
public interface tokenRepo extends JpaRepository<token,Integer> {
}

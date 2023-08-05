package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanliphattu.database.models.kieuthanhviens;
@Repository
public interface kieuthanhvienRepo extends JpaRepository<kieuthanhviens,Integer> {
}

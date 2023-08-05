package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanliphattu.database.models.dondangkys;
@Repository
public interface dondangkiRepo extends JpaRepository<dondangkys,Integer> {
}

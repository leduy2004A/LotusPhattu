package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanliphattu.database.models.daotrangs;
@Repository
public interface daotrangRepo extends JpaRepository<daotrangs,Integer> {
}

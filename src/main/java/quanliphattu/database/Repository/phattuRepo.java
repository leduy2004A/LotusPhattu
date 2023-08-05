package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanliphattu.database.models.phattu;

import java.util.Optional;

@Repository
public interface phattuRepo extends JpaRepository<phattu, Integer> {

    Optional<phattu> findByEmail(String gmail);
}
package quanliphattu.database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quanliphattu.database.models.chuas;

public interface chuaRepo extends JpaRepository<chuas,Integer> {
    chuas findByTenchua (String tenchua);
}

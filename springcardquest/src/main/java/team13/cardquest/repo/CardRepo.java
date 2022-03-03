package team13.cardquest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import team13.cardquest.model.Card;

import java.util.Optional;

public interface CardRepo extends JpaRepository<Card, Long>{
    void deleteCardById(Long id);
    Optional<Card> findCardById(Long id);
}

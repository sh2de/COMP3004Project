package ala.sami.demo.repo;

import ala.sami.demo.model.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Long>{
    void deleteCardById(Long id);
    Optional<Card> findCardById(Long id);
}

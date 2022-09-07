package investiments.orders.repositories;

import investiments.orders.entities.Provento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProventoRepository extends JpaRepository<Provento, Integer> {
}

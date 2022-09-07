package investiments.orders.repositories;

import investiments.orders.entities.TipoProvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProventoRepository extends JpaRepository<TipoProvento, Integer> {
}

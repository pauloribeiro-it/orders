package investiments.orders.repositories;

import investiments.orders.entities.TipoAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtivoRepository extends JpaRepository<TipoAtivo, Integer> {
}

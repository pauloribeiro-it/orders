package investiments.orders.repositories;

import investiments.orders.entities.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Integer>, OrdemRepositoryQuery {
}

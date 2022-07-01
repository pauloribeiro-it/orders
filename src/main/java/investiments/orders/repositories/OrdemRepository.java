package investiments.orders.repositories;

import investiments.orders.entities.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Integer>, OrdemRepositoryQuery {

    @Query("select o from Ordem o where o.ativo.idAtivo = :idAtivo")
    List<Ordem> findByIdAtivo(Integer idAtivo);
}

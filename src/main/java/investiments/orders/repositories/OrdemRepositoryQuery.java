package investiments.orders.repositories;

import investiments.orders.entities.Ordem;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrdemRepositoryQuery {
    List<Ordem> filtraOrdens(FiltroOrdem filtroOrdem, Pageable pageable);
}

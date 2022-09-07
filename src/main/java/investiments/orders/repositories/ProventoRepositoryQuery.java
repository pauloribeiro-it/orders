package investiments.orders.repositories;

import investiments.orders.entities.Provento;
import investiments.orders.filtros.FiltroProvento;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProventoRepositoryQuery {
    List<Provento> filtra(FiltroProvento filtro, Pageable pageable);
}

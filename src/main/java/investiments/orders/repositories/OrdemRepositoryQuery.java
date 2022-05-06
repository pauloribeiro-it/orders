package investiments.orders.repositories;

import investiments.orders.entities.Ordem;

import java.util.List;


public interface OrdemRepositoryQuery {
    List<Ordem> filtraOrdens(FiltroOrdem filtroOrdem);
}

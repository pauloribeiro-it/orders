package investiments.orders.repositories;

import investiments.orders.entities.Ativo;
import investiments.orders.entities.Ordem;
import investiments.orders.filtros.FiltroOrdem;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdemRepositoryImpl implements OrdemRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ordem> filtraOrdens(FiltroOrdem filtroOrdem, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ordem> criteria = builder.createQuery(Ordem.class);
        Root<Ordem> root = criteria.from(Ordem.class);

        Join<Ordem, Ativo> join = root.join("ativo");

        criteria.where(adicionarRestricoes(filtroOrdem, builder, root, join));

        TypedQuery<Ordem> query = entityManager.createQuery(criteria);

        adicionarRestricoesPaginacoes(query, pageable);

        return query.getResultList();
    }

    private Predicate[] adicionarRestricoes(FiltroOrdem filtroOrdem, CriteriaBuilder criteriaBuilder, Root<Ordem> root,
                                            Join<Ordem, Ativo> join){
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filtroOrdem.getAno())){
            LocalDate dataInicio = LocalDate.of(filtroOrdem.getAno(), 1, 1);
            LocalDate dataFim = LocalDate.of(filtroOrdem.getAno(), 12, 31);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataOrdem"), dataInicio));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataOrdem"), dataFim));
        }

        if(Objects.nonNull(filtroOrdem.getCodigoAtivo()) && !filtroOrdem.getCodigoAtivo().isEmpty()){
           predicates.add(criteriaBuilder.equal(join.get("codigoAtivo"), filtroOrdem.getCodigoAtivo().toUpperCase()));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesPaginacoes(TypedQuery<Ordem> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
}

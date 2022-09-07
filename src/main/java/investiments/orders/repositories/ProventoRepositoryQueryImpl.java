package investiments.orders.repositories;

import investiments.orders.entities.Ativo;
import investiments.orders.entities.Provento;
import investiments.orders.filtros.FiltroProvento;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProventoRepositoryQueryImpl implements ProventoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Provento> filtra(FiltroProvento filtro, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Provento> criteria = builder.createQuery(Provento.class);
        Root<Provento> root = criteria.from(Provento.class);

        Join<Provento, Ativo> join = root.join("ativo");
        criteria.where(adicionarRestricoes(filtro, builder, root, join));

        TypedQuery<Provento> query = entityManager.createQuery(criteria);
        adicionarRestricoesPaginacoes(query, pageable);

        return query.getResultList();
    }

    private Predicate[] adicionarRestricoes(FiltroProvento filtroProvento, CriteriaBuilder criteriaBuilder,
                                            Root<Provento> root,
                                            Join<Provento, Ativo> join){
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filtroProvento.getAno())){
            LocalDate dataInicio = LocalDate.of(filtroProvento.getAno(), 1, 1);
            LocalDate dataFim = LocalDate.of(filtroProvento.getAno(), 12, 31);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataProvento"), dataInicio));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataProvento"), dataFim));
        }

        if(Objects.nonNull(filtroProvento.getCodigoAtivo()) && !filtroProvento.getCodigoAtivo().isEmpty()){
            predicates.add(criteriaBuilder.equal(join.get("codigoAtivo"), filtroProvento.getCodigoAtivo().toUpperCase()));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesPaginacoes(TypedQuery<Provento> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

}

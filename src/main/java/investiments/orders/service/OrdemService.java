package investiments.orders.service;

import investiments.orders.dtos.OrdemDTO;
import investiments.orders.entities.Ordem;
import investiments.orders.filtros.FiltroOrdem;
import investiments.orders.repositories.OrdemRepository;
import investiments.orders.web.form.OrdemForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdemService {

    private final OrdemRepository repository;
    private final AtivoService ativoService;

    @Transactional
    public OrdemDTO salvaOrdem(OrdemForm ordemForm) {
        Ordem ordem = criaOrdem(ordemForm);
        this.repository.save(ordem);
        return criaOrdemDTO(ordem);
    }
    @Transactional
    public List<OrdemDTO> salvaOrdens(List<OrdemForm> ordens){
        List<Ordem> ordensBD = ordens.stream()
                                     .map(this::criaOrdem)
                                     .collect(Collectors.toList());

        return this.repository.saveAll(ordensBD)
                              .stream()
                              .map(this::criaOrdemDTO)
                              .collect(Collectors.toList());
    }

    public List<OrdemDTO> getAllOrdens(){
        return this.repository.findAll().stream().map(this::criaOrdemDTO).collect(Collectors.toList());
    }

    public List<OrdemDTO> filtraOrdem(FiltroOrdem filtroOrdem, Pageable pageable){
        return this.repository.filtraOrdens(filtroOrdem, pageable).stream().map(this::criaOrdemDTO).collect(Collectors.toList());
    }

    private Ordem criaOrdem(OrdemForm ordemForm) {
        return Ordem.builder().dataOrdem(ordemForm.getDataOrdem())
                .ativo(ativoService.recuperaPorCodigo(ordemForm.getCodAtivo()))
                .preco(ordemForm.getPreco())
                .quantidade(ordemForm.getQuantidade())
                .total(ordemForm.getPreco() * ordemForm.getQuantidade())
                .build();
    }

    private OrdemDTO criaOrdemDTO(Ordem ordem){
        return OrdemDTO.builder()
                       .codigoAtivo(ordem.getAtivo().getCodigoAtivo())
                       .dataOrdem(ordem.getDataOrdem())
                       .idOrdem(ordem.getIdOrdem())
                       .precoAtivo(ordem.getPreco())
                       .qtdItens(ordem.getQuantidade())
                       .totalOrdem(ordem.getTotal())
                       .build();
    }

}

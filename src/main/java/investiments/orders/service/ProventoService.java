package investiments.orders.service;

import investiments.orders.dtos.ProventoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.entities.Provento;
import investiments.orders.entities.TipoProvento;
import investiments.orders.filtros.FiltroProvento;
import investiments.orders.repositories.ProventoRepository;
import investiments.orders.repositories.TipoProventoRepository;
import investiments.orders.web.form.ProventoForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProventoService {

    private ProventoRepository repository;

    private TipoProventoRepository tipoProventoRepository;
    private AtivoService ativoService;

    public ProventoDTO salvaProvento(ProventoForm form) {
        Ativo ativo = this.ativoService.recuperaPorCodigo(form.getCodigoAtivo());
        TipoProvento tipoProvento = this.tipoProventoRepository.getById(form.getTipoProvento().getId());

        Provento provento = new Provento();
        provento.setAtivo(ativo);
        provento.setValor(form.getValor());
        provento.setDataProvento(form.getDataProvento());
        provento.setTipoProvento(tipoProvento);

        this.repository.save(provento);

        return new ProventoDTO(ativo.getCodigoAtivo(),
                               provento.getValor(),
                               provento.getDataProvento(),
                               provento.getTipoProvento().getDescricao());
    }

    public List<ProventoDTO> filtraProventos(FiltroProvento filtro, Pageable pageable){
        return this.repository.filtra(filtro,pageable).stream()
                                                      .map(provento -> new ProventoDTO(provento.getAtivo().getCodigoAtivo(),
                                                                                       provento.getValor(),
                                                                                       provento.getDataProvento(),
                                                                                       provento.getTipoProvento().getDescricao()))
                                                      .collect(Collectors.toList());
    }
}

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

        Provento provento = criaProvento(form);
        this.repository.save(provento);
        return criaProventoDTO(provento);
    }

    public List<ProventoDTO> salvaProventos(List<ProventoForm> proventosForm){
        List<Provento> proventos = proventosForm.stream().map(this::criaProvento).collect(Collectors.toList());
        return this.repository.saveAll(proventos).stream().map(this::criaProventoDTO).collect(Collectors.toList());
    }

    public List<ProventoDTO> filtraProventos(FiltroProvento filtro, Pageable pageable){
        return this.repository.filtra(filtro,pageable).stream()
                                                      .map(this::criaProventoDTO)
                                                      .collect(Collectors.toList());
    }

    private Provento criaProvento(ProventoForm proventoForm){
        Ativo ativo = this.ativoService.recuperaPorCodigo(proventoForm.getCodigoAtivo());
        TipoProvento tipoProvento = this.tipoProventoRepository.getById(proventoForm.getTipoProvento().getId());

        Provento provento = new Provento();
        provento.setAtivo(ativo);
        provento.setValor(proventoForm.getValor());
        provento.setDataProvento(proventoForm.getDataProvento());
        provento.setTipoProvento(tipoProvento);
        return provento;
    }

    private ProventoDTO criaProventoDTO(Provento provento) {
        return new ProventoDTO(provento.getAtivo().getCodigoAtivo(),
                provento.getValor(),
                provento.getDataProvento(),
                provento.getTipoProvento().getDescricao());
    }
}

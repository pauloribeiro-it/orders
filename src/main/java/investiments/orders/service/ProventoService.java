package investiments.orders.service;

import investiments.orders.dtos.ProventoDTO;
import investiments.orders.dtos.ProventoImportadoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.entities.Provento;
import investiments.orders.entities.TipoProvento;
import investiments.orders.filtros.FiltroProvento;
import investiments.orders.poi.PoiReader;
import investiments.orders.repositories.ProventoRepository;
import investiments.orders.repositories.TipoProventoRepository;
import investiments.orders.web.form.ProventoForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProventoService {

    private ProventoRepository repository;

    private TipoProventoRepository tipoProventoRepository;
    private AtivoService ativoService;
    private PoiReader poiReader;

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

    public void importaProventos(MultipartFile multipartFile){
        try {
            List<Provento> proventos = this.poiReader.getProventos(multipartFile.getInputStream())
                                                     .stream()
                                                     .map(p -> Provento.builder()
                                                             .dataProvento(p.getDataPagamento())
                                                             .qtd(p.getQuantidade())
                                                             .tipoProvento(TipoProvento.builder().id(p.getTipoProvento().getId()).build())
                                                             .total(p.getValorLiquido())
                                                             .valor(p.getPrecoUnitario())
                                                             .ativo(ativoService.recuperaPorCodigo(p.getCodigoProduto()))
                                                             .build())
                                                     .toList();
            this.repository.saveAll(proventos);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private ProventoDTO criaProventoDTO(Provento provento) {
        return new ProventoDTO(provento.getAtivo().getCodigoAtivo(),
                provento.getValor(),
                provento.getDataProvento(),
                provento.getTipoProvento().getDescricao());
    }
}

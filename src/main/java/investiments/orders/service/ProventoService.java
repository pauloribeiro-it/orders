package investiments.orders.service;

import investiments.orders.dtos.ProventoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.entities.Provento;
import investiments.orders.repositories.ProventoRepository;
import investiments.orders.web.form.ProventoForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProventoService {

    private ProventoRepository repository;
    private AtivoService ativoService;

    public ProventoDTO salvaProvento(ProventoForm form) {
        Ativo ativo = this.ativoService.recuperaPorCodigo(form.getCodigoAtivo());

        Provento provento = new Provento();
        provento.setAtivo(ativo);
        provento.setValor(form.getValor());
        provento.setDataProvento(form.getDataProvento());

        this.repository.save(provento);

        return new ProventoDTO(ativo.getCodigoAtivo(), provento.getValor(), provento.getDataProvento());
    }


}

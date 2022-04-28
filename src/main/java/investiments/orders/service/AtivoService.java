package investiments.orders.service;

import investiments.orders.dtos.AtivoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.repositories.AtivoRepository;
import investiments.orders.repositories.TipoAtivoRepository;
import investiments.orders.web.form.AtivoForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AtivoService {

    private final AtivoRepository ativoRepository;
    private final TipoAtivoRepository tipoAtivoRepository;

    @Transactional
    public AtivoDTO salvaAtivo(AtivoForm ativoForm) {
        Ativo ativo = new Ativo();
        ativo.setCodigoAtivo(ativoForm.getCodigoAtivo());
        ativo.setDescricaoAtivo(ativoForm.getDescricaoAtivo());
        ativo.setTipoAtivo(tipoAtivoRepository.getById(ativoForm.getIdTipoAtivo()));
        ativoRepository.save(ativo);
        return new AtivoDTO(ativo.getIdAtivo(), ativo.getCodigoAtivo(), ativo.getDescricaoAtivo(), ativo.getTipoAtivo().getDescricao());
    }

    public Ativo recuperaAtivoPorId(Integer id) {
        return this.ativoRepository.getById(id);
    }

}

package investiments.orders.service;

import investiments.orders.dtos.AtivoDTO;
import investiments.orders.dtos.TipoAtivoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.entities.Ordem;
import investiments.orders.repositories.AtivoRepository;
import investiments.orders.repositories.OrdemRepository;
import investiments.orders.repositories.TipoAtivoRepository;
import investiments.orders.web.form.AtivoForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtivoService {

    private final AtivoRepository ativoRepository;
    private final TipoAtivoRepository tipoAtivoRepository;

    private final OrdemRepository ordemRepository;

    @Transactional
    public AtivoDTO salvaAtivo(AtivoForm ativoForm) {
        Ativo ativo = new Ativo();
        ativo.setCodigoAtivo(ativoForm.getCodigoAtivo());
        ativo.setDescricaoAtivo(ativoForm.getDescricaoAtivo());
        ativo.setTipoAtivo(tipoAtivoRepository.getById(ativoForm.getIdTipoAtivo()));
        ativoRepository.save(ativo);
        return new AtivoDTO(ativo);
    }

    public Ativo recuperaAtivoPorId(Integer id) {
        return this.ativoRepository.getById(id);
    }

    public Ativo recuperaPorCodigo(String codigo) {
        return this.ativoRepository.findByCodigoAtivo(codigo);
    }

    public List<TipoAtivoDTO> getTipos() {
        return this.tipoAtivoRepository.findAll().stream()
                .map(t -> new TipoAtivoDTO(t.getIdTipoAtivo(), t.getDescricao()))
                .collect(Collectors.toList());
    }

    public List<AtivoDTO> obtemTodosOsAtivos() {
        return this.ativoRepository.findAll().stream().map(AtivoDTO::new).collect(Collectors.toList());
    }

    public boolean removeAtivo(Integer id) {
        List<Ordem> ordens = ordemRepository.findByIdAtivo(id);
        if (Objects.isNull(ordens) || ordens.isEmpty()) {
            this.ativoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

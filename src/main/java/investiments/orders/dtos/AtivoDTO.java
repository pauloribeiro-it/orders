package investiments.orders.dtos;

import investiments.orders.entities.Ativo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtivoDTO {
    private Integer id;
    private String codigoAtivo;
    private String descricaoAtivo;
    private String descricaoTipoAtivo;

    private Integer idTipoAtivo;

    public AtivoDTO(Ativo ativo){
        this.id = ativo.getIdAtivo();
        this.codigoAtivo = ativo.getCodigoAtivo();
        this.descricaoAtivo = ativo.getDescricaoAtivo();
        this.descricaoTipoAtivo = ativo.getTipoAtivo().getDescricao();
        this.idTipoAtivo = ativo.getTipoAtivo().getIdTipoAtivo();
    }

}

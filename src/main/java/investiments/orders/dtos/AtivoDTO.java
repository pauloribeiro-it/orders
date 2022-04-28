package investiments.orders.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtivoDTO {
    private Integer id;
    private String codigoAtivo;
    private String descricaoAtivo;
    private String descricaoTipoAtivo;
}

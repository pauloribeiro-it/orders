package investiments.orders.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tipo_ativo")
@Data
public class TipoAtivo {

    @Id
    @Column(name="id_tipo_ativo")
    private Integer idTipoAtivo;

    @Column(name="desc_tipo_ativo")
    private String descricao;

}

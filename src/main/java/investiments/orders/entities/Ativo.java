package investiments.orders.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Ativo {

    @Id
    @Column(name="id_ativo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtivo;

    @Column(name="cod_ativo")
    private String codigoAtivo;

    @Column(name="desc_ativo")
    private String descricaoAtivo;

    @ManyToOne
    @JoinColumn(name="id_tipo_ativo")
    private TipoAtivo tipoAtivo;
}

package investiments.orders.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tipo_provento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_provento")
    private Integer id;

    @Column(name = "desc_tipo_provento")
    private String descricao;
}

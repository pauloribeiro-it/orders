package investiments.orders.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name="provento")
@Entity
@Getter
@Setter
public class Provento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_provento")
    private Integer id;

    @Column(name="data_provento")
    private LocalDate dataProvento;

    @Column(name="valor")
    private Float valor;

    @ManyToOne
    @JoinColumn(name="id_ativo")
    private Ativo ativo;

}

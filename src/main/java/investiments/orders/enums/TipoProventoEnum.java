package investiments.orders.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TipoProventoEnum {

    RENDIMENTO(1, "Rendimento"),
    DIVIDENDO(2, "Dividendo"),
    JCP(3, "Juros sobre capital próprio");

    private final Integer id;
    private final String descricao;

    TipoProventoEnum(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoProventoEnum getByDescricao(String descricao){
        return Arrays.stream(TipoProventoEnum.values())
              .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
              .findFirst()
              .orElseThrow( () -> new IllegalArgumentException("Tipo de provento "+descricao+" não encontrado."));
    }
}

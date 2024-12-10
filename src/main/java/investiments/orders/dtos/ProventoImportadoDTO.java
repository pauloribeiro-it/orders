package investiments.orders.dtos;

import investiments.orders.enums.HeaderArquivoProventos;
import investiments.orders.enums.TipoProventoEnum;
import investiments.orders.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
public class ProventoImportadoDTO {
    private String produto;
    private LocalDate dataPagamento;
    private TipoProventoEnum tipoProvento;
    private String instituicao;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorLiquido;

    public ProventoImportadoDTO(Map<HeaderArquivoProventos, Object> values) {
        values.forEach((key, value) -> {
            switch (key) {
                case PRODUTO -> setProduto((String) value);
                case DATA_PAGAMENTO -> setDataPagamento(DateUtils.parseDate((String) value));
                case TIPO_EVENTO -> setTipoProvento(TipoProventoEnum.getByDescricao((String) value));
                case INSTITUICAO -> setInstituicao((String) value);
                case QUANTIDADE -> setQuantidade(StringUtils.isNumeric(value.toString()) ? new BigDecimal(value.toString()).intValue() : null);
                case PRECO_UNITARIO -> setPrecoUnitario(StringUtils.isNumeric(value.toString()) ? (BigDecimal) value : null);
                case VALOR_LIQUIDO -> setValorLiquido((BigDecimal) value);
            }
        });
    }

    public String getCodigoProduto() {
        return Arrays.stream(this.produto.split("-")).findFirst().map(String::trim).orElseThrow(() -> new IllegalArgumentException("Código de produto inválido."));
    }
}

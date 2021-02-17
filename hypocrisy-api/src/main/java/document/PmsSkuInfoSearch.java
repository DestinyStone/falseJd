package document;

import bean.PmsSkuAttrValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/10 20:13
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(indexName = "pms_sku_info_search")
public class PmsSkuInfoSearch implements Serializable {
    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text)
    private String spuId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String skuName;

    @Field(type = FieldType.Text)
    private String catalog3Id;

    @Field(type = FieldType.Text)
    private String skuDefaultImg;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String skuDesc;

    @Field(type = FieldType.Double)
    @JsonFormat
    private BigDecimal price;

    @JsonFormat
    private List<PmsSkuAttrValue> pmsSkuAttrValueList;
}

package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PmsProductSaleAttr implements Serializable {

  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private String productId;

  @Column
  private String saleAttrId;

  @Column
  private String saleAttrName;

  @Transient
  private List<PmsProductSaleAttrValue> spuSaleAttrValueList;
}

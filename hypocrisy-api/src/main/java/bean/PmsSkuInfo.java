package bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PmsSkuInfo implements Serializable {


  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private String spuId;

  @Transient
  private String spuName;

  @Column
  private BigDecimal price;

  @Column
  private String skuName;

  @Column
  private String skuDesc;

  @Column
  private String weight;

  @Column
  private String tmId;

  @Column
  private String catalog3Id;

  @Column
  private String skuDefaultImg;

  @Column
  private Integer status;

  @Transient
  private Integer repositoryTotal;

  @Transient
  private List<PmsSkuImage> pmsSkuImageList;

  @Transient
  private List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues;

  @Transient
  private List<PmsSkuAttrValue> pmsSkuAttrValueList;
}

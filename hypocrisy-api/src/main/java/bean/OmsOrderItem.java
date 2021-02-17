package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OmsOrderItem implements Serializable {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column
  private String id;

  @Column
  private String orderId;

  @Column
  private String orderSn;

  @Column
  private String productId;

  @Column
  private String productPic;

  @Column
  private String productName;

  @Column
  private String productBrand;

  @Column
  private String productSn;

  @Column
  private BigDecimal productPrice;

  @Column
  private Integer productQuantity;

  @Column
  private String productSkuId;

  @Column
  private String productSkuCode;

  @Column
  private String productCategoryId;

  @Column
  private String sp1;

  @Column
  private String sp2;

  @Column
  private String sp3;

  @Column
  private String promotionName;

  @Column
  private BigDecimal promotionAmount;

  @Column
  private BigDecimal couponAmount;

  @Column
  private BigDecimal integrationAmount;

  @Column
  private BigDecimal realAmount;

  @Column
  private String giftIntegration;

  @Column
  private String giftGrowth;

  @Column
  private String productAttr;

  @Transient
  private PmsSkuInfo pmsSkuInfo;
}

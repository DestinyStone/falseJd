package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OmsCartItem implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column
  private String id;

  @Column
  private String productId;

  @Column
  private String productSkuId;

  @Column
  private String memberId;

  @Column
  private Integer quantity;

  @Column
  private BigDecimal price;

  @Column
  private String sp1;

  @Column
  private String sp2;

  @Column
  private String sp3;

  @Column
  private String productPic;

  @Column
  private String productName;

  @Column
  private String productSubTitle;

  @Column
  private String productSkuCode;

  @Column
  private String memberNickname;

  @Column
  private Date createDate;

  @Column
  private Date modifyDate;

  @Column
  private Long deleteStatus;

  @Column
  private Long productCategoryId;

  @Column
  private String productBrand;

  @Column
  private String productSn;

  @Column
  private String productAttr;

  @Transient
  private String isChecked;
}

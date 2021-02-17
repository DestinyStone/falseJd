package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OmsOrder implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column
  private String id;

  @Column
  private String memberId;

  @Column
  private String couponId;

  @Column
  private String orderSn;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @Column
  private String memberUsername;

  @Column
  private BigDecimal totalAmount;

  @Column
  private BigDecimal payAmount;

  @Column
  private BigDecimal freightAmount;

  @Column
  private BigDecimal promotionAmount;

  @Column
  private BigDecimal integrationAmount;

  @Column
  private BigDecimal couponAmount;

  @Column
  private BigDecimal discountAmount;

  @Column
  private Integer payType;

  @Column
  private Integer sourceType;

  @Column
  private Integer status;

  @Column
  private Integer orderType;

  @Column
  private String deliveryCompany;

  @Column
  private String deliverySn;

  @Column
  private Integer autoConfirmDay;

  @Column
  private Integer integration;

  @Column
  private Integer growth;

  @Column
  private String promotionInfo;

  @Column
  private Integer billType;

  @Column
  private String billHeader;

  @Column
  private String billContent;

  @Column
  private String billReceiverPhone;

  @Column
  private String billReceiverEmail;

  @Column
  private String receiverName;

  @Column
  private String receiverPhone;

  @Column
  private String receiverPostCode;

  @Column
  private String receiverProvince;

  @Column
  private String receiverCity;

  @Column
  private String receiverRegion;

  @Column
  private String receiverDetailAddress;

  @Column
  private String note;

  @Column
  private Integer confirmStatus;

  @Column
  private Integer deleteStatus;

  @Column
  private Integer useIntegration;

  @Column
  private Date paymentTime;

  @Column
  private Date deliveryTime;

  @Column
  private Date receiveTime;

  @Column
  private Date commentTime;

  @Column
  private Date modifyTime;

  @Transient
  private List<OmsOrderItem> omsOrderItems;

  @Transient
  private Long expireTime;
}

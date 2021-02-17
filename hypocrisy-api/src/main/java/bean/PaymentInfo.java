package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentInfo implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column
  private Long id;

  @Column
  private String orderSn;

  @Column
  private Long orderId;

  @Column
  private String alipayTradeNo;

  @Column
  private Double totalAmount;

  @Column
  private String subject;

  @Column
  private String paymentStatus;

  @Column
  private Date createTime;

  @Column
  private Date confirmTime;

  @Column
  private String callbackContent;

  @Column
  private Date callbackTime;
}

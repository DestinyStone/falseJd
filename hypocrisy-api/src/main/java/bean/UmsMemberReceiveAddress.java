package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UmsMemberReceiveAddress implements Serializable {

  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private String memberId;

  @Column
  @NotBlank
  private String name;

  @Column
  @Pattern(regexp = "^((13[0-9])|(17[0-1,6-8])|(15[^4,\\\\D])|(18[0-9]))\\d{8}$")
  private String phoneNumber;

  @Column
  private Integer defaultStatus;

  @Column
  private String postCode;

  @Column
  @NotBlank
  private String province;

  @Column
  @NotBlank
  private String city;

  @Column
  @NotBlank
  private String region;

  @Column
  @NotBlank
  private String detailAddress;
}

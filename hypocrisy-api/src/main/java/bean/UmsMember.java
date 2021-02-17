package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UmsMember implements Serializable {

  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private String memberLevelId;

  @Column
  private String permissionId;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String nickname;

  @Column
  private String phone;

  @Column
  @Email
  private String email;

  @Column
  private Integer status;

  @Column
  private Date createTime;

  @Column
  private String icon;

  @Column
  private Integer gender;

  @Column
  private Date birthday;

  @Column
  private String city;

  @Column
  private String job;

  @Column
  private String personalizedSignature;

  @Column
  private Long sourceType;

  @Column
  private Long integration;

  @Column
  private Long growth;

  @Column
  private Long luckeyCount;

  @Column
  private Long historyIntegration;
}

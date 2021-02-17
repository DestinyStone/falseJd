package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PmsSkuAttrValue implements Serializable {

  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private String attrId;

  @Column
  private String valueId;

  @Column
  private String skuId;

  @Transient
  private String attrName;

  @Transient
  private String valueName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PmsSkuAttrValue that = (PmsSkuAttrValue) o;
    return Objects.equals(attrId, that.attrId) &&
            Objects.equals(valueId, that.valueId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attrId, valueId);
  }
}

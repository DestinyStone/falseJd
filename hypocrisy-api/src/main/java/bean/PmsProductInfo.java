package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @return
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PmsProductInfo implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String spuName;

    @Column
    private String description;

    @Column
    private  String catalog1Id;

    @Transient
    private String catalog1Name;

    @Column
    private  String catalog2Id;

    @Transient
    private String catalog2Name;

    @Column
    private  String catalog3Id;

    @Transient
    private String catalog3Name;

    @Column
    private Integer status;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column
    private String comment;

    @Transient
    private String statesDescription;

    @Transient
    private List<PmsProductImage> pmsProductImageList;

    @Transient
    private List<PmsProductAttr> pmsProductAttrList;
}



package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PmsSearchParam implements Serializable{

    private Long catalog3Id;

    private String keyword;

    private String[] valueId;
}

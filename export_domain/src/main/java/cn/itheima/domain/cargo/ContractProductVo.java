package cn.itheima.domain.cargo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-30 15:58
 **/
@Data
public class ContractProductVo implements Serializable {

    private String customName;
    private String contractNo;
    private String productNo;
    private Integer cNumber;
    private String factoryName;
    private Date deliveryDate;
    private Date shipDate;
    private String tradeTerms;

}

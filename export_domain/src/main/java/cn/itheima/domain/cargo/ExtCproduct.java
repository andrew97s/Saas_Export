package cn.itheima.domain.cargo;

import cn.itheima.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-02 16:19
 **/
@Data
public class ExtCproduct extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String productNo;			//产品号
    private String productImage;		//图片
    private String productDesc;			//产品描述
    private String packingUnit;			//  包装单位   PCS/SETS
    private Integer cnumber;			//数量
    private Double price;			    //单价
    private Double amount;				//总金额 　自动计算: 数量x单价
    private String productRequest;		//要求
    private Integer orderNo;		   //排序号
    private String contractProductId;
    private String factoryId;
    private String factoryName;//工厂名
    private String contractId;
}

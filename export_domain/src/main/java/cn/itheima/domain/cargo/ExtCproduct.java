package cn.itheima.domain.cargo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ExtCproduct implements Serializable {
    private String id;

    private String contractProductId;

    private String contractId;

    private String factoryId;

    private String factoryName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String packingUnit;

    private Long cnumber;

    private Double price;

    private Double amount;

    private Long orderNo;

    private String companyId;

    private String companyName;

    private String productRequest;

    private static final long serialVersionUID = 1L;


}
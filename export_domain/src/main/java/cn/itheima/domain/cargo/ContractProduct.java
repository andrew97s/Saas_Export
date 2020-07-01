package cn.itheima.domain.cargo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ContractProduct implements Serializable {
    private String id;

    private String contractId;

    private String factoryId;

    private String factoryName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private Long boxNum;

    private String packingUnit;

    private Long cnumber;//货物总数量

    private Long outNumber;

    private Long finished;

    private String productRequest;

    private Double price;//货物单价

    private Double amount;//货物总金额

    private Long orderNo;

    private String companyId;

    private String companyName;

    private String createBy;

    private String createDept;

    private Date createTime;

    private List<ExtCproduct> extCproducts;

    private static final long serialVersionUID = 1L;


}
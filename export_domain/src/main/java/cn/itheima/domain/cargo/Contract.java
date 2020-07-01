package cn.itheima.domain.cargo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Contract implements Serializable {
    private String id;

    private String offeror;

    private String contractNo;

    private Date signingDate;

    private String inputBy;

    private String checkBy;

    private String inspector;

    private Double totalAmount;

    private String crequest;

    private String customName;

    private Date shipTime;

    private Long importNum;

    private Date deliveryPeriod;

    private Integer oldState;

    private Integer outState;

    private String tradeTerms;

    private String printStyle;

    private String remark;

    private Integer state;

    private Integer proNum;

    private Integer extNum;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String companyId;

    private String companyName;

    private static final long serialVersionUID = 1L;

}
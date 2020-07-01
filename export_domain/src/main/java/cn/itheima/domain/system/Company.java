package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * <h3>export_parent</h3>
 * <p>to describe the company</p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:15
 **/

@Data
public class Company implements Serializable {
    private String id;
    private String name;
    private Date expirationDate;
    private String address;
    private String licenseId;
    private String representative;
    private String phone;
    private String companySize;
    private String industry;
    private String remarks;
    private Integer state;
    private Double balance;
    private String city;

}

package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-22 19:57
 **/

@Data
public class User implements Serializable {

    private String id;
    private String deptId;
    private String email;
    private String userName;
    private String station;
    private String password;
    private String state;
    private String companyId;
    private String companyName;
    private String deptName;
    private String managerId;
    private String gender;
    private String telephone;
    private String birthday;
    private Integer  degree;

    private Double salary;
    private String joinDate;
    private Integer orderNo;
    private String createBy;
    private String createDept;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}

package cn.itheima.domain.cargo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Factory implements Serializable {
    private String id;

    private String ctype;

    private String fullName;

    private String factoryName;

    private String contacts;

    private String phone;

    private String mobile;

    private String fax;

    private String address;

    private String inspector;

    private String remark;

    private Long orderNo;

    private String state;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}
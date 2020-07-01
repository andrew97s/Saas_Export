package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-23 09:37
 **/
@Data
public class Role implements Serializable {

    private String id;
    private String name;
    private String remark;
    private Double orderNo;
    private String createBy;
    private String createDept;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String companyId;
    private String companyName;

}

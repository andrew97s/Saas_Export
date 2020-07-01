package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;

/**
 * <h3>export_parent</h3>
 * <p>class of department</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 08:13
 **/
@Data
public class Dept implements Serializable {


    private String id;
    private String deptName;
    private Dept parent;
    private Integer state;
    private String companyId;
    private String companyName;
    private String parentId;
}

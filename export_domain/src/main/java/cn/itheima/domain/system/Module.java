package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;

/**
 * <h3>export_parent</h3>
 * <p>pojo of module</p>
 *
 * @author : Andrew
 * @date : 2020-06-23 14:31
 **/
@Data
public class Module implements Serializable {

    private String id;
    private String parentId;
    private String parentName;
    private String name;
    private Integer layerNum;
    private Integer isLeaf;
    private String ico;
    private String cPermission;
    private String cUrl;
    private Integer cType;
    private Integer state;
    private String belong;
    private String cWhich;
    private Integer quoteNum;
    private String remark;
    private Integer orderNo;
}

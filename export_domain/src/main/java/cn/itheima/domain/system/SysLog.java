package cn.itheima.domain.system;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-25 09:19
 **/
@Data
public class SysLog implements Serializable {

    private String id;
    private String username;
    private String ip;
    private Date time;
    private String method;
    private String action;
    private String companyId;
    private String companyName;

}

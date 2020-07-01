package cn.itheima.dao.system;

import cn.itheima.domain.system.SysLog;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of system log</p>
 *
 * @author : Andrew
 * @date : 2020-06-25 09:40
 **/
public interface ISysLogDao {

    List<SysLog> findAll();

    void add(SysLog sysLog);
}

package cn.itheima.service;

import cn.itheima.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-25 09:46
 **/
public interface ISysLogService {

    List<SysLog> findAllSysLog();

    void add(SysLog sysLog);

    PageInfo<SysLog> findSysLogByPage(Integer currentPage,Integer pageSize);
}

package cn.itheima.service.impl;

import cn.itheima.dao.system.ISysLogDao;
import cn.itheima.domain.system.SysLog;
import cn.itheima.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-25 09:47
 **/
@Service
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAllSysLog() {
        return sysLogDao.findAll();
    }

    @Override
    public void add(SysLog sysLog) {
        sysLogDao.add(sysLog);
    }

    @Override
    public PageInfo<SysLog> findSysLogByPage(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<SysLog> list = sysLogDao.findAll();

        return new PageInfo<>(list,8);
    }
}

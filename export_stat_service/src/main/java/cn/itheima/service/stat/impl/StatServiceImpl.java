package cn.itheima.service.stat.impl;

import cn.itheima.dao.stat.StatDao;
import cn.itheima.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-03 22:05
 **/
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    @Override
    public List<Map> findByCompanyAmount(String companyId) {
        return statDao.findByCompanyAmount(companyId);
    }

    @Override
    public List<Map> findByProductNum(String companyId) {
        return statDao.findByProductNum(companyId);
    }

    @Override
    public List<Map> findBySysLoad(String companyId) {
        return statDao.findBySysLoad(companyId);
    }
}

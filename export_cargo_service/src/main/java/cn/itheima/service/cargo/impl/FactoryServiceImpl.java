package cn.itheima.service.cargo.impl;

import cn.itheima.dao.cargo.FactoryDao;
import cn.itheima.domain.cargo.Factory;
import cn.itheima.service.cargo.FactoryService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 15:35
 **/

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;

    @Override
    public int deleteById(String id) {
        return factoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(Factory record) {
        return factoryDao.insertSelective(record);
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Factory> findByCtype(String ctype) {
        return factoryDao.findByCtype(ctype);
    }

    @Override
    public int update(Factory record) {
        return factoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Factory> findByPage(Integer currentPage,Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<Factory> list = factoryDao.findAll();

        return new PageInfo<>(list,8);
    }
}

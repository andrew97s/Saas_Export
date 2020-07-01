package cn.itheima.service.impl;

import cn.itheima.dao.company.IDeptDao;
import cn.itheima.domain.system.Dept;
import cn.itheima.service.IDeptService;
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
 * @date : 2020-06-22 08:37
 **/

@Service
public class IDeptServiceImpl implements IDeptService {

    @Autowired
    private IDeptDao deptDao;

    @Override
    public PageInfo<Dept> findByPage(Integer currentPage,Integer pageSize,String  companyId) {

        PageHelper.startPage(currentPage,pageSize);

        List<Dept> list = deptDao.findAll(companyId);


        return new PageInfo<>(list,8);
    }

    @Override
    public Dept findDeptById(String id) {

        return deptDao.findById(id);
    }

    @Override
    public void deleteDeptById(String id) {
        deptDao.delete(id);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.add(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public List<Dept> findAllDept(String companyId) {
        return deptDao.findAll(companyId);
    }
}

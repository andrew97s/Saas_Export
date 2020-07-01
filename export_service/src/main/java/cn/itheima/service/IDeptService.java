package cn.itheima.service;

import cn.itheima.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>service of department</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 08:35
 **/
public interface IDeptService {

    PageInfo<Dept> findByPage(Integer currentPage, Integer pageSize,String companyId);

    Dept findDeptById(String id);

    void deleteDeptById(String id);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    List<Dept> findAllDept(String companyId);
}

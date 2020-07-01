package cn.itheima.service;

import cn.itheima.domain.system.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>the interface of  companyService</p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:42
 **/

public interface ICompanyService {

    List<Company> findAllCompany();

    void saveCompany(Company company);

    void deleteCompanyById(String id);

    Company findCompanyById(String id);

    void updateCompany(Company company);

    PageInfo<Company> findByPage(Integer currentPage, Integer pageSize);
}

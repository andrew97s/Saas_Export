package cn.itheima.service.impl;

import cn.itheima.dao.company.ICompanyDao;
import cn.itheima.domain.system.Company;
import cn.itheima.service.ICompanyService;
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
 * @date : 2020-06-27 08:32
 **/

@Service
public class ICompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDao companyDao;

    @Override
    public List<Company> findAllCompany() {

        return companyDao.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        companyDao.save(company);
    }

    @Override
    public void deleteCompanyById(String id) {
        companyDao.delete(id);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.update(company);
    }

    @Override
    public PageInfo<Company> findByPage(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<Company> list = companyDao.findAll();

        return new PageInfo<>(list,5);
    }
}

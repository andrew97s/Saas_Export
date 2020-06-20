package cn.itheima.service.impl;

import cn.itheima.dao.company.ICompanyDao;
import cn.itheima.domain.Company;
import cn.itheima.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:44
 **/

@Service
public class ICompanyServiceImpl implements ICompanyService {

    @Autowired
    private ICompanyDao companyDao;

    @Override
    public List<Company> findAllCompany() {
        return companyDao.findAll();
    }
}

package cn.itheima.service;

import cn.itheima.domain.Company;
import org.springframework.stereotype.Service;

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
}

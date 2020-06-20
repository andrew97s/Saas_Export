package cn.itheima.dao.company;

import cn.itheima.domain.Company;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>the Dao of company</p>
 *
 * @author : Andrew
 * @date : 2020-06-19 20:10
 **/
public interface ICompanyDao {

    @Select("select * from ss_company")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "expirationDate",column = "expiration_date"),
            @Result(property = "licenseId",column = "license_id"),
            @Result(property = "companySize",column = "company_size")
    })
    List<Company> findAll();
}

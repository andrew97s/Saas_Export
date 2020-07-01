package cn.itheima.dao.company;

import cn.itheima.domain.system.Company;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into ss_company values(#{id},#{name}," +
            "#{expirationDate},#{address}," +
            "#{licenseId},#{representative}," +
            "#{phone},#{companySize}," +
            "#{industry},#{remarks}," +
            "#{state},#{balance},#{city})")
    void save(Company company);

    @Delete("delete from ss_company where id = #{id}")
    void delete(String id);

    @Select("select * from ss_company where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "expirationDate",column = "expiration_date"),
            @Result(property = "licenseId",column = "license_id"),
            @Result(property = "companySize",column = "company_size")
    })
    Company findById(String id);

    @Update("update ss_company set name=#{name},expiration_date=#{expirationDate}," +
            "address=#{address},license_id=#{licenseId}," +
            "representative=#{representative},phone=#{phone}," +
            "company_size=#{companySize},industry=#{industry}," +
            "remarks=#{remarks},state=#{state}," +
            "balance=#{balance},city=#{city} where id=#{id}")
    void update(Company company);
}

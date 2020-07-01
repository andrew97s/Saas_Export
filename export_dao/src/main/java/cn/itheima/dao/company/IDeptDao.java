package cn.itheima.dao.company;

import cn.itheima.domain.system.Dept;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p>dao of department</p>
 *
 * @author : Andrew
 * @date : 2020-06-22 08:21
 **/
public interface IDeptDao {

    @Select("select * from pe_dept where company_id=#{companyId} and is_deleted=0")
    @Results({
            @Result(id = true,property = "id",column = "dept_id"),
            @Result(property = "deptName",column = "dept_name"),
            @Result(property = "parent",column = "parent_id",one = @One(select = "findById",fetchType = FetchType.LAZY)),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "companyName",column = "company_name"),
            @Result(property = "parentId",column = "parent_id")
    })
    List<Dept> findAll(String companyId);

    @Select("select * from pe_dept where dept_id=#{id} and is_deleted=0")
    @Results({
            @Result(id = true,property = "id",column = "dept_id"),
            @Result(property = "deptName",column = "dept_name"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "companyName",column = "company_name"),
            @Result(property = "parentId",column = "parent_id")
    })
    Dept findById(String id);

    @Insert("insert into pe_dept values(#{id},#{deptName},#{parentId},#{state},#{companyId},#{companyName},0)")
    void add(Dept dept);

    /**
     * @param id
     * 删除指定行数据 通过将is_deleted属性改为1 代表该条数据已被删除
     */
    @Update("update pe_dept set is_deleted = 1 where dept_id=#{id}")
    void delete(String id);

    @Update("update pe_dept set dept_name=#{dept_name}," +
            "parent_id=#{parent.id},state=#{state}," +
            "company_id=#{companyId},company_name=#{companyName}")
    void update(Dept dept);

}

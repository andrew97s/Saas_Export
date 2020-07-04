package cn.itheima.dao.stat;

import java.util.List;
import java.util.Map;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-03 21:50
 **/
public interface StatDao {

    List<Map> findByCompanyAmount(String companyId);

    List<Map> findByProductNum(String companyId);

    List<Map> findBySysLoad(String companyId);
}

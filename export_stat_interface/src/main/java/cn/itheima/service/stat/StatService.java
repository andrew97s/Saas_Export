package cn.itheima.service.stat;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-03 21:38
 **/
public interface StatService {

    List<Map> findByCompanyAmount(String companyId);

    List<Map> findByProductNum(String companyId);

    List<Map> findBySysLoad(String companyId);
}

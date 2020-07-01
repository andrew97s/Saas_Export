package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.Factory;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-06-28 15:32
 **/
public interface FactoryService {

    int deleteById(String id);

    int save(Factory record);

    Factory findById(String id);

    List<Factory> findByCtype(String ctype);

    int update(Factory record);

    PageInfo<Factory> findByPage(Integer currentPage,Integer pageSize);
}

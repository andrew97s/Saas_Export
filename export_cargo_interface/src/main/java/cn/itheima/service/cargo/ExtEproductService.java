package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.ExtEproduct;
import cn.itheima.domain.cargo.ExtEproductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExtEproductService {
    int deleteById(String id);


    int save(ExtEproduct record);

    List<ExtEproduct> findByExample(ExtEproductExample example);

    PageInfo<ExtEproduct> findByExampleAndPage(Integer currentPage,Integer pageSize,ExtEproductExample example);

    ExtEproduct findById(String id);

    int update(ExtEproduct record);

}
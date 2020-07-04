package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.ExportProduct;
import cn.itheima.domain.cargo.ExportProductExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExportProductService {

    int deleteById(String id);

    int insert(ExportProduct record);

    int save(ExportProduct record);

    List<ExportProduct> findByExample(ExportProductExample example);

    PageInfo<ExportProduct> findByExampleAndPage(Integer currentPage,Integer pageSize,ExportProductExample example);

    ExportProduct findById(String id);

    int update(ExportProduct record);

}
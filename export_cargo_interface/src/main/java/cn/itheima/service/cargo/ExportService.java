package cn.itheima.service.cargo;

import cn.itheima.domain.cargo.Export;
import cn.itheima.domain.cargo.ExportExample;
import cn.itheima.domain.cargo.ExportResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExportService {

    Export selectByPrimarykey(String id);

    int deleteById(String id);

    int save(Export record);

    List<Export> findByExample(ExportExample example);

    PageInfo<Export> findByExampleAndPage(Integer currentPage,Integer pageSize,ExportExample example);

    Export findByIds(String id);

    int update(Export record);

    void updateE(ExportResult exportResult);

}
package cn.itheima.service.cargo.impl;

import cn.itheima.domain.cargo.ExtEproduct;
import cn.itheima.domain.cargo.ExtEproductExample;
import cn.itheima.service.cargo.ExtEproductService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>export_parent</h3>
 * <p></p>
 *
 * @author : Andrew
 * @date : 2020-07-02 11:37
 **/
public class ExtEproductServiceImpl implements ExtEproductService {
    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int save(ExtEproduct record) {
        return 0;
    }

    @Override
    public List<ExtEproduct> findByExample(ExtEproductExample example) {
        return null;
    }

    @Override
    public PageInfo<ExtEproduct> findByExampleAndPage(Integer currentPage, Integer pageSize, ExtEproductExample example) {
        return null;
    }

    @Override
    public ExtEproduct findById(String id) {
        return null;
    }

    @Override
    public int update(ExtEproduct record) {
        return 0;
    }
}

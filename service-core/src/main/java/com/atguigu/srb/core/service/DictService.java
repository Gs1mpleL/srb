package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author wanfeng
 * @since 2022-04-30
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);
    List<ExcelDictDTO> listDictData();
    List<Dict> listByParentId(Long parentId);
}

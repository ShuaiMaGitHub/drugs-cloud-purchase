package com.ms.item.service.impl;

import com.ms.item.mapper.SpecGroupMapper;
import com.ms.item.mapper.SpecParamMapper;
import com.ms.item.pojo.SpecGroup;
import com.ms.item.pojo.SpecParam;
import com.ms.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 14:45
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询参数组
     *
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return this.specGroupMapper.select(record);
    }

    /**
     * 根据条件查询规格参数
     *
     * @param gid
     * @return
     */
    @Override
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);

    }



    /**
     * 新增参数
     * @param specParam
     * @return
     */
    @Override
    public void savespecParam(SpecParam specParam) {
        specParam.setId(null);
        this.specParamMapper.insertSelective(specParam);
    }

    /**
     * 修改参数
     * @param specParam
     * @return
     */
    @Override
    public void updateSpecParam(SpecParam specParam) {
        this.specParamMapper.updateByPrimaryKey(specParam);
    }

    /**
     * 根据cid查询规格参数组
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupWithParam(Long cid) {
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(group -> {
            List<SpecParam> params = this.queryParams(group.getId(), null, null, null);
            group.setParams(params);
        });
        return groups;
    }

    /**
     * 删除规格参数
     * @param id
     * @return
     */
    @Override
    public void deleteSpecParam(Long id) {
        this.specParamMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增分组
     * @param specGroup
     * @return
     */
    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        this.specGroupMapper.insert(specGroup);
    }

    /**
     * 修改参数组
     * @param specGroup
     * @return
     */
    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        this.specGroupMapper.updateByPrimaryKeySelective(specGroup);
    }

    /**
     * 删除规格参数组
     * @param id
     */
    @Override
    public void deleteSpecGroup(Long id) {
        this.specGroupMapper.deleteByPrimaryKey(id);
    }



}

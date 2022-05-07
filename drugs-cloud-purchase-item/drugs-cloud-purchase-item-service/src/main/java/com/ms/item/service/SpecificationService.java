package com.ms.item.service;

import com.ms.item.pojo.SpecGroup;
import com.ms.item.pojo.SpecParam;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 14:44
 */
public interface SpecificationService {
    List<SpecGroup> queryGroupsByCid(Long cid);

    List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching);

    void saveSpecGroup(SpecGroup specGroup);

    void savespecParam(SpecParam specParam);

    void updateSpecGroup(SpecGroup specGroup);

    void deleteSpecParam(Long id);

    void deleteSpecGroup(Long id);

    void updateSpecParam(SpecParam specParam);

    List<SpecGroup> queryGroupWithParam(Long cid);
}

package com.ms.item.api;

import com.ms.item.pojo.SpecGroup;
import com.ms.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 14:46
 */

@RequestMapping("/spec")
public interface SpecificationApi {


    /**
     * 根据条件查询规格参数
     *
     * @param gid
     * @return
     */
    @GetMapping("/params")
    List<SpecParam> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    );

    /**
     * 根据cid查询规格参数组
     * @param cid
     * @return
     */
    @GetMapping("group/param/{cid}")
    List<SpecGroup> queryGroupWithParam(@PathVariable("cid") Long cid);

}

package com.ms.item.controller;

import com.ms.item.pojo.SpecGroup;
import com.ms.item.pojo.SpecParam;
import com.ms.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 14:46
 */
@Controller
@RequestMapping("/spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类id查询参数组
     *
     * @param cid
     * @return
     */
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * 根据条件查询规格参数
     *
     * @param gid
     * @return
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    ) {
        List<SpecParam> params = this.specificationService.queryParams(gid, cid, generic, searching);
        if (CollectionUtils.isEmpty(params)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    /**
     * 新增参数组
     *
     * @param specGroup
     * @return
     */
    @PostMapping("/group")
    public ResponseEntity<Void> saveSpecGroup(SpecGroup specGroup) {
        this.specificationService.saveSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改参数组
     *
     * @param specGroup
     * @return
     */
    @PutMapping("/group")
    public ResponseEntity<Void> updateSpecGroup(SpecGroup specGroup) {
        this.specificationService.updateSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除规格参数组
     *
     * @param id
     * @return
     */
    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteSpecGroup(@PathVariable("id") Long id) {
        this.specificationService.deleteSpecGroup(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 新增参数
     *
     * @param specParam
     * @return
     */
    @PostMapping("/param")
    public ResponseEntity<Void> saveSpecParam(SpecParam specParam) {
        this.specificationService.savespecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改参数
     *
     * @param specParam
     * @return
     */
    @PutMapping("/param")
    public ResponseEntity<Void> updateSpeParam( SpecParam specParam) {
        this.specificationService.updateSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除规格参数
     *
     * @param id
     * @return
     */
    @DeleteMapping("/param/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable("id") Long id) {

        this.specificationService.deleteSpecParam(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据cid查询规格参数组
     * @param cid
     * @return
     */
    @GetMapping("group/param/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupWithParam(@PathVariable("cid") Long cid){
        List<SpecGroup> groups=this.specificationService.queryGroupWithParam(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

}

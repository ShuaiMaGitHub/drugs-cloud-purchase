package com.ms.item.controller;

import com.ms.common.pojo.PageResult;
import com.ms.item.pojo.Brand;
import com.ms.item.pojo.Category;
import com.ms.item.service.BrandService;
import com.ms.item.service.CategoryService;
import org.bouncycastle.voms.VOMSAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import sun.awt.geom.AreaOp;

import java.util.List;

/**
 * 品牌管理
 *
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/15 9:26
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    /**
     * 更新品牌
     *
     * @param cids
     * @param brand
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updataBrand(@RequestParam("cids") List<Long> cids, Brand brand) {
        this.brandService.updateBrand(cids, brand);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 删除品牌
     *
     * @param bid
     * @return
     */
    @DeleteMapping("/bid/{bid}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable("bid") String bid) {
        String spearator = "-";
        if (bid.contains((spearator))) {
            String[] ids = bid.split(spearator);
            for (String id : ids) {
                this.brandService.deleteBrand(Long.parseLong(id));
            }
        } else {
            this.brandService.deleteBrand(Long.parseLong(bid));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据分类id查询品牌列表
     * @param cid
     * @return
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        List<Brand> brands=this.brandService.queryBrandByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }

    /**
     * 根据id查询品牌名称
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id")Long id){
        Brand brand=this.brandService.queryBrandById(id);
        if(brand==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }

}

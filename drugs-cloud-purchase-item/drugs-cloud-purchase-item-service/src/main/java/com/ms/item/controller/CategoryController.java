package com.ms.item.controller;

import com.ms.item.pojo.Category;
import com.ms.item.service.CategoryService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

/**
 * 分类管理
 *
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 14：29
 */

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询子节点
     *
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            //400:参数不合法
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            //404：资源服务未找到
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
        //200：查询成功
        return ResponseEntity.ok(categories);
    }

    /**
     * 增加分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCateGory(Category category) {
        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("/cid/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateCategory(Category category){
        this.categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 用于修改品牌信息时，商品分类信息进行回显
     *
     * @param bid
     * @return
     */
    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if (list == null || list.size() < 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 根据多个id查询分类信息
     * @param ids
     * @return
     */
    @GetMapping
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids){
        List<String> names = this.categoryService.queryNamesByIds(ids);
        if (CollectionUtils.isEmpty(names)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);

    }
}

package com.ms.item.pojo;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * 规格参数组
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 14:21
 */
@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private String name;
    @Transient
    private List<SpecParam> params;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecParam> getParams() {
        return params;
    }

    public void setParams(List<SpecParam> params) {
        this.params = params;
    }
}

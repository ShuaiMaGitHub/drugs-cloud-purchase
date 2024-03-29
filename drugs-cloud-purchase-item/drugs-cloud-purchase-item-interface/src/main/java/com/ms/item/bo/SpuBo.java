package com.ms.item.bo;

import com.ms.item.pojo.Sku;
import com.ms.item.pojo.Spu;
import com.ms.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/17 15:48
 */
public class SpuBo extends Spu {

    private String cname;

    private String bname;

    private SpuDetail spuDetail;

    private List<Sku> skus;



    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public SpuDetail getSpuDetail() {
        return spuDetail;
    }

    public void setSpuDetail(SpuDetail spuDetail) {
        this.spuDetail = spuDetail;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}

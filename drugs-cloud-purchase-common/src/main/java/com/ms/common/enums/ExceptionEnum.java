package com.ms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ExceptionEnums
 * @Description: TODO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOUND(400,"品牌不存在!"),
    CATEGORY_NOT_FOUND(404,"商品分类没有查到!"),
    SPEC_GROUP_NOT_FOUND(404,"商品规则组不存在"),
    SPEC_GROUP_SAVE_ERROR(500,"商品规则组存储失败"),
    SPEC_GROUP_UPDATE_ERROR(500,"商品规则组更新失败"),
    SPEC_GROUP_DELETE_ERROR(500,"商品规则组删除失败"),
    SPEC_PARAM_NOT_FOUND(404,"商品规则参数不存在"),
    SPEC_PARAM_SAVE_ERROR(500,"商品规则参数保存失败"),
    SPEC_PARAM_UPDATE_ERROR(500,"商品规则参数更新失败"),
    SPEC_PARAM_DELETE_ERROR(500,"商品规则参数删除失败"),
    GOODS_NOT_FOUND(404,"没有查询到商品信息"),
    GOODS_SAVE_ERROR(500,"新增商品失败"),
    GOODS_DETAIL_NOT_FOUND(404,"商品详情不存在"),
    GOODS_SKU_NOT_FOUND(404,"商品SKU不存在"),
    GOODS_STOCK_NOT_FOUND(404,"商品库存不存在"),
    GOODS_UPDATE_ERROR(500,"更新商品失败"),
    GOODS_ID_CANNOT_BE_NULL(400,"商品id不能为空!"),
    GOODS_DELETE_ERROR(500,"删除商品失败"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    BRAND_UPDATE_ERROR(500,"更新品牌失败"),
    BRAND_DELETE_ERROR(500,"删除品牌失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败!"),
    INVALID_FILE_TYPE(400,"无效的文件类型"),
    USER_CODE_ERROR(400,"验证码不正确"),
    INVALID_USER_DATA_TYPE(400,"用户数据类型无效"),
    USER_REGISTER_ERROR(400,"用户注册失败!"),
    USER_NAME_ERROR(400,"用户名校验失败"),
    USER_PASSWORD_ERROR(400,"密码校验失败"),
    USER_LOGIN_ERROR(400,"用户登录失败"),
    UNAUTHORIZED(403,"未授权"),
    CART_NOT_FOUND(404,"购物车为空"),
    CREATE_ORDER_ERROR(500,"创建订单失败"),
    STOCK_NOT_ENOUGH(500,"库存不足"),
    ORDER_NOT_FOUND(404,"订单不存在"),
    ORDER_DETAIL_NOT_FOUND(404,"订单详情不存在"),
    ORDER_STATUS_NOT_FOUND(404,"订单状态不存在"),
    WX_PAY_ORDER_FAIL(500,"微信下单失败"),
    ORDER_STATUS_ERROR(400,"订单状态异常"),
    INVALID_SIGN_ERROR(400,"无效的签名"),
    INVALID_ORDER_PARAM(400,"订单参数异常"),
    UPDATE_ORDER_STATUS_ERROR(400,"更新订单状态失败");
    private int code;
    private String msg;
}

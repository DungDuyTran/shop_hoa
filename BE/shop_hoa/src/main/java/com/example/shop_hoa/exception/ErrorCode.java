package com.example.shop_hoa.exception;

public enum ErrorCode {
    PRODUCT_NOT_FOUND(1001,"Sản phẩm không tồn tại"),
        CATEGORY_NOT_FOUND(1002,"Danh mục không tồn tại");

    private final int code;
    private final String message;
    ErrorCode(int code, String message){
        this.code = code; this.message = message;
    }
    public  int getCode(){return  code;}
    public String getMessage(){
        return message;
    }
}

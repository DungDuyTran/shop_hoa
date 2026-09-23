package com.example.shop_hoa.app.dto.response;
import com.example.shop_hoa.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data @AllArgsConstructor public class AuthResponse { private String accessToken; private User user; }
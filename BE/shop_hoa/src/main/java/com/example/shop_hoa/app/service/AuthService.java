package com.example.shop_hoa.app.service;

import com.example.shop_hoa.app.dto.request.LoginRequest;
import com.example.shop_hoa.app.dto.request.RegisterRequest;
import com.example.shop_hoa.app.dto.response.AuthResponse;
import com.example.shop_hoa.app.entity.User;
import com.example.shop_hoa.app.repository.UserRepository;
import com.example.shop_hoa.core.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtils jwtUtils;

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        User user = new User();
        user.setHoTen(request.getHoTen());
        user.setEmail(request.getEmail());
        user.setSdt(request.getSdt());
        // Mã hóa mật khẩu bằng BCrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Sai email hoặc mật khẩu"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai email hoặc mật khẩu");
        }

        String token = jwtUtils.generateToken(user.getEmail(), user.getId());
        user.setPassword(null); // Không trả về password cho FE
        return new AuthResponse(token, user);
    }
}
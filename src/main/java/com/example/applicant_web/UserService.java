package com.example.applicant_web;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<User> findAll() {
        return mapper.findAll();
    }

    // 登録(入力に問題があれば、IllegalArgumentExceptionを投げる)
    public void add(User user) {
        if (user.getUserCd() == null || user.getUserCd().isBlank()) {
            throw new IllegalArgumentException("IDコードを入力してください");
        }
        if (user.getUserName() == null || user.getUserName().isBlank()) {
            throw new IllegalArgumentException("ユーザー名を入力してください");
        }

        // 前後の空白を取り除く
        user.setUserCd(user.getUserCd().trim());
        user.setUserName(user.getUserName().trim());

        try {
            mapper.insert(user);
        } catch (DuplicateKeyException e) {
            // 同じIDコードが、すでにDBにある場合
            throw new IllegalArgumentException("このIDコードはすでに登録されています");
        }
    }
}
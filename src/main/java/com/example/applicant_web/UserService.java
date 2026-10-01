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

    // idで1件取得(見つからなければ例外を投げる)
    public User findById(Long id) {
        User user = mapper.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("ユーザーが見つかりません。id=" + id);
        }
        return user;
    }

    // 登録
    public void add(User user) {
        validateAndTrim(user);
        try {
            mapper.insert(user);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("このIDコードはすでに登録されています");
        }
    }

    // 更新
    public void update(User user) {
        validateAndTrim(user);
        findById(user.getId()); // 存在しないidなら、ここで例外になる
        try {
            mapper.update(user);
        } catch (DuplicateKeyException e) {
            // 他のユーザーが使っているIDコードに変えようとした場合
            throw new IllegalArgumentException("このIDコードはすでに登録されています");
        }
    }

    // 入力チェックと、前後の空白の除去(登録・更新で共通)
    private void validateAndTrim(User user) {
        if (user.getUserCd() == null || user.getUserCd().isBlank()) {
            throw new IllegalArgumentException("IDコードを入力してください");
        }
        if (user.getUserName() == null || user.getUserName().isBlank()) {
            throw new IllegalArgumentException("ユーザー名を入力してください");
        }
        user.setUserCd(user.getUserCd().trim());
        user.setUserName(user.getUserName().trim());
    }
}
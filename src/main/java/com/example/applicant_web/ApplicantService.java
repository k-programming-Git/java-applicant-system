package com.example.applicant_web;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    private final ApplicantMapper mapper;

    public ApplicantService(ApplicantMapper mapper) {
        this.mapper = mapper;
    }

    // キーワードが空なら全件、あれば部分一致で検索
    public List<Applicant> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return mapper.findAllOrderByName();
        }
        return mapper.findByNameContaining(keyword);
    }

    public void add(String name) {
        mapper.insert(new Applicant(name));
    }

    // 見つからなければ例外を投げる
    public Applicant findById(Long id) {
        Applicant applicant = mapper.findById(id);
        if (applicant == null) {
            throw new IllegalArgumentException("応募者が見つかりません。id=" + id);
        }
        return applicant;
    }

    public void update(Long id, String name) {
        Applicant applicant = findById(id);
        applicant.setName(name);
        mapper.update(applicant);
    }

    public void delete(Long id) {
        mapper.deleteById(id);
    }
}
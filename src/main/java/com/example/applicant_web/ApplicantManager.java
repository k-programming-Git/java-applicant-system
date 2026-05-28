package com.example.applicant_web;

import java.util.ArrayList;

public class ApplicantManager {

    private ArrayList<Applicant> applicants;

    public ApplicantManager() {

        applicants = new ArrayList<>();

        applicants.add(new Applicant("田中"));
        applicants.add(new Applicant("佐藤"));
        applicants.add(new Applicant("鈴木"));

    }

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public void addApplicant(String name) {
    applicants.add(new Applicant(name));
    }
}
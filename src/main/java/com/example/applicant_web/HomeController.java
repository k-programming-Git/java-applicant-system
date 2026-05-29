package com.example.applicant_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//「下のクラスは特別なクラス！」というメモ
public class HomeController {

private ApplicantRepository repository;
//ApplicantRepositoryの変数repositoryは、DBと会話する担当

public HomeController(ApplicantRepository repository) {
    this.repository = repository;
}

@GetMapping("/")
//@GetMapping("/")は、
// ブラウザが「URLに/deleteってあったら、Springは以下のことして！」というメモ
//「下のクラスは特別なクラス！」というメモ

public String home(

        @RequestParam(required = false)
        String keyword,

        @RequestParam(required = false)
        String message, Model model) {

    if(keyword == null || keyword.isBlank()){
    // 「SpringがURLからkeywordを受け取ってない」もしくは「Keywordが空」のときは、
        model.addAttribute("applicants", repository.findAllByOrderByNameAsc());
    // reposiitoryがDBから全部取得して、名前順にして
    // それを、modelがHTMLへ渡すよ。
    // modelはHTMLへの受け渡し担当　repositoryはDBと会話担当
    }else{

        model.addAttribute("applicants", repository.findByNameContainingOrderByNameAsc(keyword));

    }

    //repository.findAll()は、引数なので値である
    //なんの値かというと、DBと会話する担当のrepositoryが、
    //DBからすべてを取得した値

    model.addAttribute("message", message);

    return "home";
}

@GetMapping("/add")
//@GetMapping("/add")は、
// ブラウザが「URLに/addってあったら、Springは以下のことして！」というメモ
//「下のクラスは特別なクラス！」というメモ

public String addApplicant(
        @RequestParam String name) {
            //@RequestParmは、
            //ブラウザが「URLでこの値もらったから、SpringはStringの変数に、その値をいれて！」というメモ

    if(name.isBlank()){
        return "redirect:/?message=Please+input+name";
    }

    repository.save(new Applicant(name));
    //9行上の@RequestParmで、name変数には「その値」が入っている　
    //もし9行上で"Tanaka"と受け取っていたら、その値は"Tanaka"なので
    //ここではnew Applicant("Tanaka")となっている。

    return "redirect:/";
}

@GetMapping("/delete")
//@GetMapping("/delete")は、
// ブラウザが「URLに/deleteってあったら、Springは以下のことして！」というメモ
//「下のクラスは特別なクラス！」というメモ

public String deleteApplicant(
        @RequestParam Long id) {
        //@RequestParmは、
        //ブラウザが「URLでこの値もらったから、SpringはLongの変数に、その値をいれて！」というメモ

    repository.deleteById(id);
    //repository.deleteById(id);は、メソッドである
    //なんのメソッドかというと、DBと会話する担当のrepositoryが、
    //引数IDをもとに、DBでdeleteするメソッド

    return "redirect:/";
}

@GetMapping("/edit")
//@GetMapping("/edit")は、
// ブラウザが「URLに/editってあったら、Springは以下のことして！」というメモ
// 「下のクラスは特別なクラス！」というメモ
public String editPage(
    @RequestParam Long id, Model model){
    //@RequestParmは、
    //ブラウザが「URLでこの値もらったから、SpringはLong modelの変数に、その値をいれて！」というメモ

Applicant applicant = repository.findById(id).get();

model.addAttribute("applicant", applicant);

return "edit";


}

@GetMapping("/update")
//@GetMapping("/update")は、
// ブラウザが「URLに/Updateってあったら、Springは以下のことして！」というメモ
// 「下のクラスは特別なクラス！」というメモ
public String updateApplicant(
@RequestParam Long id,
@RequestParam String name
//@RequestParmは、
//ブラウザが「URLでこの値もらったから、SpringはLong Stringの変数に、この値をいれて！」というメモ
){


Applicant applicant =repository.findById(id).get();

applicant.setName(name);
//repository.setName(name);は、メソッドである
//なんのメソッドかというと、DBと会話する担当のrepositoryが、
//引数nameをもとに、DBにsetNameするメソッド

repository.save(applicant);
//repository.save(applicant);は、メソッドである
//なんのメソッドかというと、DBと会話する担当のrepositoryが、
//引数applicantをもとに、DBにsaveするメソッド

return "redirect:/";


}

}

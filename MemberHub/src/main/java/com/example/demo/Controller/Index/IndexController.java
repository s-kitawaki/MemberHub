package com.example.demo.Controller.Index;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.form.UserForm;

@Controller
public class IndexController {

	// ログの定数宣言
	private static final Logger logger = Logger.getLogger(IndexController.class.getName());

	@GetMapping("index")
	public String showIndex(UserForm userForm, Model model) {
		logger.info("ログイン画面 初期処理 開始");

		model.addAttribute("userFrom", userForm);

		logger.info("ログイン画面 初期処理 終了");
		return "Index/index";
	}
	


}



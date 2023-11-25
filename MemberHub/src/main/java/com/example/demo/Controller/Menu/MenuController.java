package com.example.demo.Controller.Menu;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserForm;

@Controller
public class MenuController {
	// ログの定数宣言
	private static final Logger logger = Logger.getLogger(MenuController.class.getName());
	
    @PostMapping("/menu")
    public String nextPage(UserForm userForm,Model model) {
        // モデルにデータを設定
    	logger.info("showMenu処理 開始");
    	logger.info("showMenu処理 終了");
        return "Menu/menu";
    }
    
    @GetMapping("/menu")
    public String backPage(UserForm userForm,Model model) {
        // モデルにデータを設定
    	logger.info("ホーム画面遷移処理 開始");
    	logger.info("ホーム画面遷移処理 終了");
        return "Menu/menu";
    }
}

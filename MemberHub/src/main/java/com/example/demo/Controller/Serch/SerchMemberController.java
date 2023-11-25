package com.example.demo.Controller.Serch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Common.ComMessage;
import com.example.demo.Model.SerchMemberModel;
import com.example.demo.Service.SerchMemberService;
import com.example.demo.form.UserForm;

@Controller
public class SerchMemberController {
	// ログの定数宣言
	private static final Logger logger = Logger.getLogger(SerchMemberController.class.getName());
	
	/** SerchMemberサービス */
	@Autowired
	SerchMemberService serchMemberService;

	/** メッセージ取得共通処理 */
	@Autowired
	ComMessage comMessage;
	
    @GetMapping("/serchMember")
    public String nextPage(UserForm userForm,Model model) {
        // モデルにデータを設定
    	logger.info("serchMember一覧処理 開始");
    	List<SerchMemberModel> resutlList = new ArrayList<>();
    	List<Map<String, Object>> results = new ArrayList<>();
    	Map<String, Object> errList = new HashMap<>();
    	
    	errList = serchMemberService.serch(model);
    	
    	logger.info("serchMember一覧処理 終了");
        return "Serch/serch";
    }
}

package com.example.demo.Controller.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Common.ComAcceseDbSelect;
import com.example.demo.Common.ComMessage;
import com.example.demo.form.UserForm;

/**
 * ログイン画面　ログインボタン押下後処理
 */

@RestController
public class IndexAjaxController {
	
	// ログの定数宣言
	private static final Logger logger = Logger.getLogger(IndexController.class.getName());
	
	/** ユーザーマスタ検索SQLファイル名 */
	final String FIND_USER_INFO = "FIND_USER_INFO.sql";

	/** メッセージ取得共通処理 */
	@Autowired
	ComMessage comMessage;
	
	/** DBアクセス(select専用) */
	@Autowired
	ComAcceseDbSelect comAcceseDbSelect;
	
	@PostMapping("/ajaxEndpoint")
	public Map<String, Object>  handleAjaxRequest(@Validated @ModelAttribute("userForm") UserForm userForm, 
			                                       BindingResult bindingResult, Model model) {
		logger.info("ログイン画面 ユーザーチェック処理 開始");		
		List<Map<String, Object>> results = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();

		// 単項目チェック
		if (bindingResult.hasErrors()) {
			for (FieldError  error : bindingResult.getFieldErrors()) {
				// エラー対象項目
				String fieldName  = error.getField();
				// エラー対象メッセージ
				String errorMessage = error.getDefaultMessage();
				logger.info(fieldName+"："+errorMessage);
			}
			// エラーがあった場合
			logger.info("ログイン画面 ユーザーチェック処理 単項目チェックエラー発生");	
			String getMessage = comMessage.getMessage("msgNo001");
			response.put("error", "error");
			response.put("message", getMessage);
			return response;
		}
		
		String sqlPath = FIND_USER_INFO;
		Map<String, Object> placeholder = new HashMap<>();
		placeholder.put("userId", userForm.getUserId());
		placeholder.put("password", userForm.getPassword());
		
		// ユーザーマスタにDB検索する処理を作成
		logger.info("ログイン画面 ユーザーチェック処理 共通処理呼び出し");
		results = comAcceseDbSelect.executeSqlQuery(sqlPath, placeholder);
		
		if (((Long) results.get(0).get("count")).intValue() == 0) {
			logger.info("ログイン画面 ユーザーチェック処理 入力項目エラー発生");	
			String getMessage = comMessage.getMessage("msgNo002");
			response.put("error", "error");
			response.put("message", getMessage);
		}else {
			response.put("success", "success");			
		}
		
        logger.info("ログイン画面 ユーザーチェック処理 終了");
        return response;
	}
}

package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Common.ComAcceseDbSelect;
import com.example.demo.Common.ComMessage;
import com.example.demo.Model.SerchMemberModel;

@Service
public class SerchMemberService {
	/** ユーザーマスタ検索SQLファイル名 */
	final String FIND_MEMBER_INFO = "FIND_MEMBER_INFO.sql";
	
	/** DBアクセス(select専用) */
	@Autowired
	ComAcceseDbSelect comAcceseDbSelect;
	
	/** メッセージ取得共通処理 */
	@Autowired
	ComMessage comMessage;
	
	public Map<String, Object> serch(Model model) {
		
		List<Map<String, Object>> results = new ArrayList<>();
		List<SerchMemberModel> resutlList = new ArrayList<>();
		Map<String, Object> errList = new HashMap<>();
		
		String sqlPath = FIND_MEMBER_INFO;
    	// パラメータなしなので宣言のみ
    	Map<String, Object> placeholder = new HashMap<>();
    	
		// ユーザーマスタにDB検索する処理を作成
		results = comAcceseDbSelect.executeSqlQuery(sqlPath, placeholder);
		
		// DBのカラムを決めてそのカラムをSerchMemberModelに宣言する
		// builderの使い方を確認する
		
		
		if(results.size() == 0) {
			errList.put("errorMsg", comMessage.getMessage("msgNo002"));
		}
		
		return errList;
	}

}

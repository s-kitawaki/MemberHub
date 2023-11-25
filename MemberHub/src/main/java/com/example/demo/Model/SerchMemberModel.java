package com.example.demo.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerchMemberModel {
	// ユーザID
	@NotBlank(message="必須項目です")
	private String userId;
	// パスワード
	@NotBlank(message="必須項目です")
	private String password;
}

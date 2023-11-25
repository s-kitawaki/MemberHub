package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	// ユーザID
	@NotBlank(message="必須項目です")
	private String userId;
	// パスワード
	@NotBlank(message="必須項目です")
	private String password;
}

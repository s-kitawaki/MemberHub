// ログインボタン押下時発火
function buttonClick() {
	// ユーザーIDとパスワード取得
	var formData = new FormData();
	formData.append("userId", $(".userId").val());
	formData.append("password", $(".password").val());

	$.ajax({
		type: "POST",
		url: "/ajaxEndpoint",
		data: formData,
		processData: false, // 必須: FormDataを処理しないように設定
		contentType: false, // 必須: コンテントタイプを設定しないように設定
		dataType: "json",
		success: function(data) {
			if (data.success == "success") {
				var form = $("<form>").attr({method: "post",action: "/menu"});
				// フォームをページに追加（任意の要素に追加できます）
				$("#loginButton").append(form);
				// フォームを送信
				form.submit();
			} else if (data.error == "error") {
				$(".errMsgArea").text(data.message);
			}
		},
		error: function() {
		}
	});
}

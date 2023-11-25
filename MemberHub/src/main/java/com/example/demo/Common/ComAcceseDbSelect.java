package com.example.demo.Common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ComAcceseDbSelect {

	// ログの定数宣言
	private static final Logger logger = Logger.getLogger(ComAcceseDbSelect.class.getName());

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ComAcceseDbSelect(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public List<Map<String, Object>> executeSqlQuery(String path, Map<String, Object> placeholder) {
		logger.info("共通処理 SQL実行処理 開始");

		List<Map<String, Object>> results = new ArrayList<>();
		try {
			// SQLクエリをファイルから読み込む
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/" + path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder query = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				query.append(line).append("\n");
			}

			// <参考>
			// プレースホルダーに値を設定(複数のパラメータを送る場合）
			//			SqlParameterSource parameters = new MapSqlParameterSource("id", 1)
			//            .addValue("name", "Bill")
			//            .addValue("gender", "male");

			if (placeholder.size() > 0) {
				// SqlParameterSourceにパラメータを設定する
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				for (Map.Entry<String, Object> entry : placeholder.entrySet()) {
					parameters.addValue(entry.getKey(), entry.getValue());
				}
				// SQLクエリを実行
				results = jdbcTemplate.queryForList(query.toString(), parameters);
			}else {
				// SQLクエリを実行
				results = jdbcTemplate.queryForList(query.toString(), EmptySqlParameterSource.INSTANCE);
			}

			// 実行SQLの取得(ログ出力用)
			String executedSqLog = sqlLog(query.toString());
			logger.info("共通処理 SQL実行処理 実行SQL"+ executedSqLog);
			
		} catch (Exception e) {
			logger.info("共通処理 SQL実行処理 エラー発生"+ e);
			return null;
		} finally {			
			logger.info("共通処理 SQL実行処理 終了");
		}
		return results;
	}
	
	/**
	 * 実行SQLの取得(ログ出力用)
	 */
	public String sqlLog(String strSql) {
		String executedSql = "";
		try {
			executedSql = DataSourceUtils.getConnection(jdbcTemplate.getJdbcTemplate().getDataSource())
				    .prepareStatement(strSql)
				    .toString();
		} catch (CannotGetJdbcConnectionException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// 改行文字を空白に置換
		executedSql = executedSql.replace("\n", " ").replace("\r", "");
		return executedSql;
		
	}
}

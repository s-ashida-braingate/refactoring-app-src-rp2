package jp.co.sss.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 社員情報　DAO インターフェイス
 */
public interface IEmployeeDAO {

	/**
	 * CRUD実行結果のSetの有無
	 * @param resultSet
	 * @return resultSet 引数がそのまま返る
	 * @throws SQLException
	 */
	public abstract ResultSet checkResult(ResultSet resultSet) throws SQLException;

	/**
	 * DBを切断（SELECT文以外の場合）
	 * @param connection
	 * @param preparedStatement
	 * @throws SQLException
	 */
	public abstract void closeDB(Connection connection, PreparedStatement preparedStatement)
			throws SQLException;

	/**
	 * DBを切断（SELECT文の場合）
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 * @throws SQLException
	 */
	public abstract void closeDB(Connection connection, PreparedStatement preparedStatement,
			ResultSet resultSet) throws SQLException;
}

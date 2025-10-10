package jp.co.sss.crud.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.db.DBManager;
import jp.co.sss.crud.dto.Department;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.Constants;

public class EmployeeDAO implements IEmployeeDAO {

	/**
	 * 全件検索
	 * @return 全社員
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Employee> findAll() throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Employee> empList = new ArrayList<>();

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQLの実行
			resultSet = preparedStatement.executeQuery();

			// レコードを取得
			empList = resultSetToEmployees(resultSet);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			// DBとの接続を切断
			closeDB(connection, preparedStatement, resultSet);
		}

		return empList;
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public List<Employee> findByName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = br.readLine();

		List<Employee> empList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + searchWord + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(Constants.MSG_EMP_NOT_FOUND);
				return null;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {

			}

			System.out.println("");

		} finally {
			// DBとの接続を切断
			closeDB(connection, preparedStatement, resultSet);
		}

		return empList;
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public void findByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer sql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			sql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(sql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				if (gender == 0) {
					System.out.print("回答なし");
				} else if (gender == 1) {
					System.out.print("男性");

				} else if (gender == 2) {
					System.out.print("女性");

				} else if (gender == 9) {
					System.out.print("その他");

				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				String deptIdString = resultSet.getString("dept_id");
				int deptId2 = Integer.parseInt(deptIdString);
				if (deptId2 == 1) {
					System.out.println("営業部");
				} else if (deptId2 == 2) {
					System.out.println("経理部");
				} else if (gender == 3) {
					System.out.println("総務部");

				}
			}

			System.out.println("");
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public void insert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println("社員情報を登録しました");
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public void update(String empId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print("社員名：");
			String empName = br.readLine(); // emp_nameから変更
			// 性別を入力
			System.out.print("性別(0:回答しない, 1:男性, 2:女性, 9:その他):");
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print("生年月日(西暦年/月/日)：");
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print("部署ID(1：営業部、2：経理部、3：総務部)：");
			String deptId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, sdf.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));
			preparedStatement.setInt(5, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// DBとの接続を切断
			closeDB(connection, preparedStatement);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public void delete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String empId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(empId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println("社員情報を削除しました");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// DBとの接続を切断
			closeDB(connection, preparedStatement);
		}
	}

	/**
	 * DBから取得したレコードをリストで渡す
	 * @param resultSet
	 * @return 社員リスト
	 */
	public List<Employee> resultSetToEmployees(ResultSet resultSet) {
		List<Employee> empList = new ArrayList<>();
		try {
			while (resultSet.next()) {

				Employee emp = new Employee();
				emp.setEmpId(resultSet.getInt(Constants.DB_EMP_ID));
				emp.setEmpName(resultSet.getString(Constants.DB_EMP_NAME));
				emp.setGender(resultSet.getInt(Constants.DB_GENDER));
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate date = LocalDate.parse(resultSet.getString(Constants.DB_BIRTHDAY), dtf);
				emp.setBirthday(date);
				Department dept = new Department();
				//dept.setDeptId(resultSet.getInt(Constants.DB_DEPT_ID));
				dept.setDeptId(null);
				dept.setDeptName(resultSet.getString(Constants.DB_DEPT_NAME));
				emp.setDepartment(dept);

				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empList;
	}

	@Override
	public ResultSet checkResult(ResultSet resultSet) throws SQLException {
		//resultSetの結果Setがない場合はfalse
		if (!resultSet.isBeforeFirst()) {
			ConsoleWriter.showMessage(Constants.MSG_EMP_NOT_FOUND);
		}

		return resultSet;
	}

	@Override
	public void closeDB(Connection connection, PreparedStatement preparedStatement) {
		try {
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeDB(Connection connection, PreparedStatement preparedStatement,
			ResultSet resultSet) {
		try {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

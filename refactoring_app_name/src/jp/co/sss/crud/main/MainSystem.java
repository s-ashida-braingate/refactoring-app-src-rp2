package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.dao.EmployeeDAO;
import jp.co.sss.crud.service.EmployeeFindAllService;
import jp.co.sss.crud.util.Constants;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		EmployeeDAO employeeDAO = new EmployeeDAO();

		int menuNum = 0; // menuNoから変更

		do {
			// メニューの表示
			System.out.println(Constants.TITLE);
			System.out.println(Constants.MENU_1);
			System.out.println(Constants.MENU_2);
			System.out.println(Constants.MENU_3);
			System.out.println(Constants.MENU_4);
			System.out.println(Constants.MENU_5);
			System.out.println(Constants.MENU_6);
			System.out.println(Constants.MENU_7);
			System.out.print(Constants.MSG_MENU);

			// メニュー番号の入力
			String inputMenuNum = br.readLine();
			menuNum = Integer.parseInt(inputMenuNum);

			// 機能の呼出
			switch (menuNum) {
			case 1:
				// 全件表示機能の呼出
				EmployeeFindAllService employeeFindAllService = new EmployeeFindAllService();
				employeeFindAllService.showAllEmployees();
				break;

			case 2:
				// 社員名検索
				System.out.print(Constants.INPUT_EMPNAME);

				// 検索機能の呼出
				employeeDAO.findByName();
				break;

			case 3:
				// 検索する部署IDを入力
				System.out.print(Constants.MSG_INPUT_DEPTID);
				String inputDeptId = br.readLine();

				// 検索機能の呼出
				employeeDAO.findByDeptId(inputDeptId);
				break;

			case 4:
				// 登録する値を入力
				System.out.print(Constants.INPUT_EMPNAME);
				String empName = br.readLine();
				System.out.print(Constants.INPUT_GENDER);
				String gender = br.readLine();
				System.out.print(Constants.INPUT_BIRTHDAY);
				String birthday = br.readLine();
				System.out.print(Constants.INPUT_DEPTID);
				String deptId = br.readLine();

				// 登録機能の呼出
				employeeDAO.insert(empName, gender, birthday, deptId);
				break;

			case 5:
				// 更新する社員IDを入力
				System.out.print(Constants.MSG_INPUT_UPDATE_EMPID);

				// 更新する値を入力する
				String inputEmpId = br.readLine();
				Integer.parseInt(inputEmpId);

				// 更新機能の呼出
				employeeDAO.update(inputEmpId);
				System.out.println(Constants.MSG_UPDATE_COMPLETE);

				break;

			case 6:
				// 削除する社員IDを入力
				System.out.print(Constants.MSG_INPUT_DELETE_EMPID);

				// 削除機能の呼出
				employeeDAO.delete();
				break;

			}
		} while (menuNum != 7);
		System.out.println(Constants.MSG_EXIT);
	}
}

package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
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

		int menuNum = 0; // menuNoから変更

		do {
			// メニューの表示
			System.out.println(Constants.TITLE); // ↓↓定数化
			System.out.println(Constants.MENU_1);
			System.out.println(Constants.MENU_2);
			System.out.println(Constants.MENU_3);
			System.out.println(Constants.MENU_4);
			System.out.println(Constants.MENU_5);
			System.out.println(Constants.MENU_6);
			System.out.println(Constants.MENU_7);
			System.out.print(Constants.MSG_MENU); // ↑↑

			// メニュー番号の入力
			String inputMenuNum = br.readLine(); // menuNoStrから変更
			menuNum = Integer.parseInt(inputMenuNum);

			// 機能の呼出
			switch (menuNum) {
			case 1:
				// 全件表示機能の呼出
				DBController.findAll(); // find()から変更
				break;

			case 2:
				// 社員名検索
				System.out.print("社員名:");

				// 検索機能の呼出
				DBController.findByName(); // findB()から変更
				break;

			case 3:
				// 検索する部署IDを入力
				System.out.print(Constants.MSG_INPUT_DEPTID); // 定数化
				String inputDeptId = br.readLine(); // deptIdAから変更

				// 検索機能の呼出
				DBController.findByDeptId(inputDeptId); // find()から変更
				break;

			case 4:
				// 登録する値を入力
				System.out.print("社員名:");
				String empName = br.readLine(); // emp_nameから変更
				System.out.print("性別(0:その他, 1:男性, 2:女性, 9:回答なし):");
				String gender = br.readLine(); // Seibetsuから変更
				System.out.print("生年月日(西暦年/月/日):");
				String birthday = br.readLine();
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
				String deptId = br.readLine(); // deptIdBから変更

				// 登録機能の呼出
				DBController.insert(empName, gender, birthday, deptId);
				break;

			case 5:
				// 更新する社員IDを入力
				System.out.print(Constants.MSG_INPUT_UPDATE_EMPID); // 定数化

				// 更新する値を入力する
				String inputEmpId = br.readLine(); //empId_1から変更
				Integer.parseInt(inputEmpId);

				// 更新機能の呼出
				DBController.update(inputEmpId);
				System.out.println(Constants.MSG_UPDATE_COMPLETE); // 定数化

				break;

			case 6:
				// 削除する社員IDを入力
				System.out.print(Constants.MSG_INPUT_DELETE_EMPID);

				// 削除機能の呼出
				DBController.delete();
				break;

			}
		} while (menuNum != 7);
		System.out.println(Constants.MSG_EXIT);
	}
}

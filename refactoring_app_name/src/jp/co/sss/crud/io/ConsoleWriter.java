package jp.co.sss.crud.io;

import java.util.List;

import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.util.Constants;

/**
 * 画面入出力クラス
 */
public class ConsoleWriter {

	/**
	 * ヘッダー（列名）を出力
	 */
	public static void showHeader() {

		System.out.println(Constants.EMP_COLUMNS);
	}

	public static void showMessage(String msg) {
		System.out.println(msg);
	}

	public static void showEmployees(List<Employee> empList) {

		showHeader();
		for (Employee emp : empList) {
			System.out.println(emp.toString());
		}
		System.out.println("");

	}
}

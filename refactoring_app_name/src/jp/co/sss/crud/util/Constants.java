package jp.co.sss.crud.util;

/**
 * 設定値をまとめたクラス
 * 
 * @author 芦田
 * 
 */
public class Constants {

	private Constants() {
	}

	public static final String TITLE = "=== 社員管理システム ===";
	public static final String MENU_1 = "1.全件表示";
	public static final String MENU_2 = "2.社員名検索";
	public static final String MENU_3 = "3.部署ID検索";
	public static final String MENU_4 = "4.新規登録";
	public static final String MENU_5 = "5.更新";
	public static final String MENU_6 = "6.削除";
	public static final String MENU_7 = "7.終了";
	public static final String INPUT_EMPNAME = "社員名:";
	public static final String INPUT_GENDER = "性別(0:その他, 1:男性, 2:女性, 9:回答なし):";
	public static final String INPUT_BIRTHDAY = "生年月日(西暦年/月/日):";
	public static final String INPUT_DEPTID = "部署ID(1:営業部、2:経理部、3:総務部):";
	public static final String MSG_MENU = "メニュー番号を入力してください：";

	public static final String MSG_INPUT_DEPTID = "部署ID(1:営業部、2:経理部、3:総務部)を入力してください:";
	public static final String MSG_INPUT_UPDATE_EMPID = "更新する社員の社員IDを入力してください：";
	public static final String MSG_UPDATE_COMPLETE = "社員情報を更新しました";
	public static final String MSG_INPUT_DELETE_EMPID = "削除する社員の社員IDを入力してください：";
	public static final String MSG_EXIT = "システムを終了します。";
	public static final String MSG_EMP_NOT_FOUND = "該当者はいませんでした";
	public static final String EMP_COLUMNS = "社員ID\t社員名\t性別\t生年月日\t部署名";
	public static final String MALE = "男性";
	public static final String FEMALE = "女性";
	public static final String OTHER = "その他";
	public static final String NO_ANSWER = "回答なし";
	public static final String DB_EMP_ID = "emp_id";
	public static final String DB_EMP_NAME = "emp_name";
	public static final String DB_GENDER = "gender";
	public static final String DB_BIRTHDAY = "birthday";
	public static final String DB_DEPT_ID = "dept_id";
	public static final String DB_DEPT_NAME = "dept_name";
}

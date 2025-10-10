package jp.co.sss.crud.dto;

import java.time.LocalDate;

import jp.co.sss.crud.util.Constants;

/**
 * Employee DTO
 * @author 芦田
 */
public class Employee {

	private Integer empId;
	private String empName;
	private Integer gender;
	private LocalDate birthday;
	private Department department;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee() {

	}

	@Override
	public String toString() {
		String genderJa = "";
		if (gender == 1) {
			genderJa = Constants.MALE;
		} else if (gender == 2) {
			genderJa = Constants.FEMALE;
		}
		return "" + empId + "\t" + empName + "\t" + genderJa + "\t"
				+ birthday + "\t" + department.getDeptName();
	}
}

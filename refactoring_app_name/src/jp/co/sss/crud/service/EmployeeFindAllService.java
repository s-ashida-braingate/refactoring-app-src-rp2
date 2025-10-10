package jp.co.sss.crud.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.dao.EmployeeDAO;
import jp.co.sss.crud.dto.Employee;
import jp.co.sss.crud.io.ConsoleWriter;

public class EmployeeFindAllService implements IEmployeeService {

	public void showAllEmployees() throws ClassNotFoundException, SQLException {

		List<Employee> empList = new ArrayList<>();
		EmployeeDAO employeeDAO = new EmployeeDAO();

		empList = employeeDAO.findAll();

		ConsoleWriter.showEmployees(empList);
	}

}

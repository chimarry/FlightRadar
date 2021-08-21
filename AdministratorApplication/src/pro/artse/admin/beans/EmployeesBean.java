package pro.artse.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pro.artse.admin.util.Pages;
import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.services.IAccountService;
import pro.artse.dal.services.ServiceFactory;

@ManagedBean(name = "employeesBean")
@ViewScoped
public class EmployeesBean implements Serializable {
	private static final long serialVersionUID = -7552884429813493097L;

	private IAccountService accountService = ServiceFactory.getAccountService();

	private List<AccountDTO> employees = getAll();
	private AccountDTO selectedEmployee;
	private String password;
	private AccountDTO newEmployee = new AccountDTO();

	public List<AccountDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<AccountDTO> employees) {
		this.employees = employees;
	}

	public AccountDTO getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(AccountDTO selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public AccountDTO getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(AccountDTO newEmployee) {
		this.newEmployee = newEmployee;
	}

	public List<AccountDTO> getAll() {
		return accountService.getAll(AccountRole.Employee).stream().map(x -> (AccountDTO) (x))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String add() {
		if (newEmployee != null && password != null) {
			newEmployee.setRole(AccountRole.Employee);
			accountService.addEmployee(newEmployee, password).getStatus();
			newEmployee = new AccountDTO();
		}
		setEmployees(getAll());
		setPassword(null);
		return Pages.SAME_PAGE;
	}

	public String update() {
		if (selectedEmployee != null) {
			if (password == null || password.isBlank())
				setPassword(null);
			accountService.updateEmployee(selectedEmployee, getPassword()).getStatus();
		}
		setEmployees(getAll());
		setPassword(null);
		return Pages.SAME_PAGE;
	}

	public String delete() {
		if (selectedEmployee != null) {
			System.out.println(selectedEmployee.getAccountId());
			accountService.deleteEmployee(selectedEmployee.getAccountId());
		}
		setEmployees(getAll());
		return Pages.SAME_PAGE;
	}
}

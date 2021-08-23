package pro.artse.admin.beans;

import java.io.Serializable;
import java.util.List;

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
		return accountService.getEmployees();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String add() {
		if (password != null) {
			newEmployee.setRole(AccountRole.Employee);
			accountService.addEmployee(newEmployee, password);
			newEmployee = new AccountDTO();
		}
		return refresh();
	}

	public String update() {
		if (selectedEmployee != null) {
			if (password == null || password.isBlank())
				setPassword(null);
			accountService.updateEmployee(selectedEmployee, getPassword());
		}

		return refresh();
	}

	public String delete() {
		if (selectedEmployee != null)
			accountService.deleteEmployee(selectedEmployee.getAccountId());
		return refresh();
	}

	private String refresh() {
		setEmployees(getAll());
		setPassword(null);
		return Pages.SAME_PAGE;
	}
}

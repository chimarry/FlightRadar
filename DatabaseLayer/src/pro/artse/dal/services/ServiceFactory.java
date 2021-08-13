package pro.artse.dal.services;

public final class ServiceFactory {

	public static IAccountService getAccountService() {
		return new AccountService();
	}
}

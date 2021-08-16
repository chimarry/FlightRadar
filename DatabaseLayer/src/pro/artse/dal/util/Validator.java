package pro.artse.dal.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

import pro.artse.dal.dto.AccountDTO;
import pro.artse.dal.dto.AccountRole;
import pro.artse.dal.dto.InputFlightReservationDTO;

public final class Validator {

	private static final String EMAIL_VERIFICATION_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

	/**
	 * Checks if string is null or empty.
	 * 
	 * @param item String to check.
	 * @return True if it is null or empty, false otherwise.
	 */
	public static boolean isNullOrEmpty(String item) {
		return item == null || item.equals("");
	}

	/**
	 * Checks if strings are null or empty.
	 * 
	 * @param item Strings to check.
	 * @return True if one of items is null or empty, false otherwise.
	 */
	public static boolean areNullOrEmpty(String... items) {
		for (String item : items)
			if (isNullOrEmpty(item))
				return true;

		return false;
	}

	public static boolean isNull(Object item) {
		return item == null;
	}

	public static boolean areNull(Object... items) {
		for (Object item : items)
			if (isNull(item))
				return true;

		return false;
	}

	public static boolean areValidDates(LocalDate date1, LocalDate date2) {
		return !((date1 == null) || (date2 == null) || (date1.compareTo(date2) > 0));
	}

	public static boolean isInvalidAccount(AccountDTO account) {
		return areNullOrEmpty(account.getUsername(), account.getName(), account.getLastName(), account.getEmail(),
				account.getCountry(), account.getAddress())
				|| !Pattern.matches(EMAIL_VERIFICATION_REGEX, account.getEmail());
	}

	public static boolean isInvalidFlightReservation(InputFlightReservationDTO dto, AccountRole role) {
		if (role == AccountRole.Passenger)
			return dto.getSeatNumber() == 0;
		else if (role == AccountRole.Transport)
			return dto.getCargoDescription() == null || dto.getFileSpecification() == null;
		return false;
	}
}

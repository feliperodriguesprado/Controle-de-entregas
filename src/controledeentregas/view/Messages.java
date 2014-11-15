package controledeentregas.view;

import javax.swing.JOptionPane;

import controledeentregas.controller.LanguageResources;

public class Messages extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public static boolean getMessageOptionClose() {

		int result = showConfirmDialog(null,
				LanguageResources.getResource("want_to_close"),
				LanguageResources.getResource("message"), YES_NO_OPTION,
				QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else if (result == JOptionPane.NO_OPTION
				|| result == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		return false;
	}
	
	public static boolean getMessageOptionCloseSystem() {

		int result = showConfirmDialog(null,
				LanguageResources.getResource("want_to_close_system"),
				LanguageResources.getResource("message"), YES_NO_OPTION,
				QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else if (result == JOptionPane.NO_OPTION
				|| result == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		return false;
	}
	
	public static boolean getMessageOptionCancel() {

		int result = showConfirmDialog(null,
				LanguageResources.getResource("want_to_cancel"),
				LanguageResources.getResource("message"), YES_NO_OPTION,
				QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else if (result == JOptionPane.NO_OPTION
				|| result == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		return false;
	}

	public static void getMessageUnique() {
		showMessageDialog(null,
				LanguageResources.getResource("existing_registration"),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageFieldsIsEmpty() {
		showMessageDialog(null,
				LanguageResources.getResource("complete_all_fields"),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageFiltersIsEmpty() {
		showMessageDialog(null,
				LanguageResources.getResource("complete_all_filters"),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static boolean getMessageSaveRegistration() {
		int result = showConfirmDialog(null,
				LanguageResources.getResource("save_registration"),
				LanguageResources.getResource("message"), YES_NO_OPTION,
				QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else if (result == JOptionPane.NO_OPTION
				|| result == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		return false;
	}
	
	public static boolean getMessageRemoveRegistration(String registration) {
		int result = showConfirmDialog(null,
				(LanguageResources.getResource("remove_registration") + " " + registration + "?"),
				LanguageResources.getResource("message"), YES_NO_OPTION,
				QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			return true;
		} else if (result == JOptionPane.NO_OPTION
				|| result == JOptionPane.CLOSED_OPTION) {
			return false;
		}
		return false;
	}
	
	public static void getMessageItemAlreadyInsered(String item) {
		showMessageDialog(null,
				(item + " " + LanguageResources.getResource("already_inserted")),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageNoItemInsered() {
		showMessageDialog(null,
				LanguageResources.getResource("no_item_inserted"),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageErrorFloat() {
		showMessageDialog(null,
				"Casa decimal deve ser separada por ponto\nExemplo: 1.50\n",
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageQuantityEmpty(String item) {
		showMessageDialog(null,
				(LanguageResources.getResource("quantity_empty") + " " + item),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	public static void getMessageDeliveryRegisteredSuccessfully() {
		showMessageDialog(null,
				LanguageResources.getResource("delivery_registered_successfully"),
				LanguageResources.getResource("message"), INFORMATION_MESSAGE);
	}
	
	
}

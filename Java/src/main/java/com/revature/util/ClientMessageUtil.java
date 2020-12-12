package com.revature.util;

import com.revature.ajax.ClientMessage;

public class ClientMessageUtil {

	public static final ClientMessage REGISTRATION_SUCCESSFUL = new ClientMessage("REGISTRATION SUCCESSFUL");
	public static final ClientMessage REGISTRATION_UNSUCCESSFUL = new ClientMessage("REGISTRATION UNSUCCESSFUL");
	public static final ClientMessage LOGIN_SUCCESSFUL = new ClientMessage("LOGIN SUCCESSFUL");
	public static final ClientMessage LOGIN_UNSUCCESSFUL = new ClientMessage("LOGIN UNSUCCESSFUL");
	public static final ClientMessage UPDATE_SUCCESSFUL = new ClientMessage("UPDATE SUCCESSFUL");
	public static final ClientMessage UPDATE_UNSUCCESSFUL = new ClientMessage("UPDATE UNSUCCESSFUL");
	public static final ClientMessage PURCHASE_SUCCESSFUL = new ClientMessage("PURCHASE SUCCESSFUL");
	public static final ClientMessage PURCHASE_UNSUCCESSFUL = new ClientMessage("PURCHASE UNSUCCESSFUL");
	public static final ClientMessage STOCK_SAVED = new ClientMessage("STOCK_SAVED");
	public static final ClientMessage STOCK_WAS_NOT_SAVED = new ClientMessage("STOCK_WAS_NOT_SAVED");
}

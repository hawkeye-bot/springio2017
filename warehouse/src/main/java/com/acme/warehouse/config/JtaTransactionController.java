package com.acme.warehouse.config;

import org.eclipse.persistence.transaction.JTATransactionController;

import javax.transaction.TransactionManager;

/**
 * Jetty specific {@link JTATransactionController} as a EclipseLink target server.
 *
 * @author ajorritsma
 */
public class JtaTransactionController extends JTATransactionController {
	private static TransactionManager tm;

	@Override
	protected TransactionManager acquireTransactionManager()
			throws Exception {
		return tm;
	}

	public static TransactionManager getTm() {
		return tm;
	}

	public static void setTm(TransactionManager tm) {
		JtaTransactionController.tm = tm;
	}
}

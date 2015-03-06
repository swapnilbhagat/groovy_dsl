/**
 * 
 */
package com.groovy.dsl.accounting

import com.groovy.dsl.accounting.domain.ACCOUNT_TYPE
import com.groovy.dsl.accounting.domain.Amount
import com.groovy.dsl.accounting.domain.CURRENCY
import com.groovy.dsl.accounting.service.AccountingService


/**
 * Class to test accounting DSL.
 * 
 * @author SwapnilB
 *
 */
class AccountingDSLCaller {

	public void call(AccountingService service) {
		Number.metaClass.getUsd{
			->
			new Amount(delegate, CURRENCY.DOLLAR)
		}
		Number.metaClass.getRs{
			->
			new Amount(delegate, CURRENCY.RUPEE)
		}

		def binding = new Binding([
			create : service.&createAccount,
			rule : service.&createRule,
			transfer : service.&transfer,
			status : service.&findStatus,
			share : service.&share,
			personal : ACCOUNT_TYPE.personal,
			checkings : ACCOUNT_TYPE.checkings,
			savings : ACCOUNT_TYPE.savings
		])
		def shell = new GroovyShell(binding)
		shell.evaluate(new File("src/main/groovy/com/groovy/dsl/accounting/AccountingConsole.groovy"))
	}
}
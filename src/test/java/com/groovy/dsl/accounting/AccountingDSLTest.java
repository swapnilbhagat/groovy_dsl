/**
 * 
 */
package com.groovy.dsl.accounting;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import com.groovy.dsl.accounting.service.AccountingService;

/**
 * Accounting DSL test class.
 * 
 * @author SwapnilB
 *
 */
public class AccountingDSLTest {
	@Test
	@Rollback(true)
	public void test() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:test_config.xml");
		ctx.refresh();

		AccountingService service = ctx.getBean(AccountingService.class);
		Assert.notNull(service);
		new AccountingDSLCaller().call(service);
	}
}

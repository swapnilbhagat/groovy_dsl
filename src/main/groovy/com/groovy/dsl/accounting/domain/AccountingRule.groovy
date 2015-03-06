/**
 * 
 */
package com.groovy.dsl.accounting.domain


/**
 * Represents accounting rule.
 * 
 * @author SwapnilB
 *
 */
class AccountingRule {
	def Account from
	def BigDecimal amount
	def transferTo = new ArrayList<To>()

	class To{
		def Account to
		def float allocation
	}
}

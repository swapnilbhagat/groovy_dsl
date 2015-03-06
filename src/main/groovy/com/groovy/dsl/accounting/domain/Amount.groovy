/**
 * 
 */
package com.groovy.dsl.accounting.domain

/**
 * @author SwapnilB
 *
 */
class Amount {
	def BigDecimal value
	def CURRENCY currency

	Amount(Integer value,CURRENCY currency){
		this.currency = currency
		this.value = new BigDecimal(value)
	}
}

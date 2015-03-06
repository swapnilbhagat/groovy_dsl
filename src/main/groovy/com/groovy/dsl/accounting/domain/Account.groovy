/**
 * 
 */
package com.groovy.dsl.accounting.domain

/**
 * Represents Account.
 * 
 * @author SwapnilB
 *
 */
class Account {
	private String name
	private String type
	private BigDecimal balance = BigDecimal.ZERO

	def debit(amount) {
		balance = balance+amount
	}

	def credit(amount){
		balance = balance-amount
	}

	Object invokeMethod(String name, Object args){
		println "invokeMethod"
		if(name.startsWith("printIn")){
			def split = name.split("printIn")
			println "Your language is now ${split[1]}"
		}
	}

	Object methodMissing(String name, Object args){
		println "methodMissing"
		if(name.startsWith("printIn")){
			def split = name.split("printIn")
			println "Your language is now ${split[1]}"
		}
	}
}

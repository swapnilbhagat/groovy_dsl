/**
 * 
 */
package com.groovy.dsl.calculator

/**
 * Simple Calculator.
 * 
 * @author SwapnilB
 *
 */
class SimpleCalculator implements Calculator{

	@Override
	def sum(a,b) {
		println a+b
		return a+b
	}

	@Override
	def substract(a,b) {
		println a-b
		return a-b
	}

	@Override
	def multiply(a,b) {
		println a*b
		return a*b
	}

	@Override
	def divide(a,b) {
		println a/b
		return a/b
	}
}

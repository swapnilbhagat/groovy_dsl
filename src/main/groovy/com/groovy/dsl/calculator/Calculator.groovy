/**
 * 
 */
package com.groovy.dsl.calculator

/**
 * Calculator interface.
 * 
 * @author SwapnilB
 *
 */
interface Calculator {

	/**
	 * Returns the summation of given parameters.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	def sum(a,b)

	/**
	 * Returns the subtraction of given parameters.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	def substract(a,b)

	/**
	 * Returns the multiplication of given parameters.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	def multiply(a,b)

	/**
	 * Returns the division of given parameters.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	def divide(a,b)
}

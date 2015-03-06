/**
 * This script calls Console.groovy script.
 */

package com.groovy.dsl.calculator

static void call(){
	SimpleCalculator action = new SimpleCalculator()
	def binding = new Binding([
		action : action,
		sum : action.&sum,
		substract : action.&substract,
		multiply : action.&multiply,
		divide : action.&divide
	])
	def shell = new GroovyShell(binding)
	shell.evaluate(new File("src/main/groovy/com/groovy/dsl/calculator/CalculatorConsole.groovy"))
}
package com.groovy.dsl.accounting.domain

enum CURRENCY {
	DOLLAR('usd'), RUPEE('rs')

	String symbol
	CURRENCY(String symbol) {
		this.symbol = symbol
	}
}

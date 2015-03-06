/**
 * 
 */
package com.groovy.dsl.accounting.repository

import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

import com.groovy.dsl.accounting.domain.Account

/**
 * Repository class for Account.
 * 
 * @author SwapnilB
 *
 */
class AccountRepository {
	private java.sql.Connection connection

	/**
	 * Initialises the AccountRepository, prepares the DB connection
	 */
	void init(){
		Class.forName("com.mysql.jdbc.Driver")
		connection = DriverManager.getConnection("jdbc:mysql://localhost/accounting_dsl?user=root&password=test")
	}

	/**
	 * Returns all the accounts.
	 * 
	 * @return
	 */
	List<Account> findAll(){
		List<Account> accounts = new ArrayList<Account>()
		PreparedStatement preparedStatement = connection.prepareStatement("select * from account")
		ResultSet resultSet = preparedStatement.executeQuery()
		while(resultSet.next()){
			def name  = resultSet.getString("name")
			def type  = resultSet.getString("type")
			def balance  = resultSet.getBigDecimal("balance")
			Account account  = new Account()
			account.name = name
			account.type = type
			account.balance = balance
			accounts.add(account)
		}
		return accounts
	}

	/**
	 * Insert entry in account table for given parameters.
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	Account create(String name, String type){
		PreparedStatement prepareStatement = connection.prepareStatement("insert into account values (default,?,?,?)")
		prepareStatement.setString(1, name)
		prepareStatement.setString(2, type)
		prepareStatement.setInt(3, 0)
		prepareStatement.executeUpdate()
		Account account = new Account()
		account.name = name
		account.type = type
		account.balance = BigDecimal.ZERO
		return account
	}

	/**
	 * Updates the given Account in DB.
	 * 
	 * @param account
	 */
	void update(Account account){
		PreparedStatement preparedStatement = connection.prepareStatement("update account set balance=? where name=?")
		preparedStatement.setBigDecimal(1, account.balance)
		preparedStatement.setString(2, account.name)
		preparedStatement.executeUpdate()
	}
}

/**
 * 
 */
package com.groovy.dsl.accounting.service

import java.math.RoundingMode

import org.springframework.beans.factory.annotation.Autowired

import com.groovy.dsl.accounting.domain.ACCOUNT_TYPE
import com.groovy.dsl.accounting.domain.Account
import com.groovy.dsl.accounting.domain.AccountingRule
import com.groovy.dsl.accounting.domain.Amount
import com.groovy.dsl.accounting.repository.AccountRepository

/**
 * Service to perform accounting operations.
 * 
 * @author SwapnilB
 *
 */
class AccountingService{

	private List<Account> accounts = new ArrayList<Account>()
	private Map accountingRules = new HashMap<AccountingRule>()
	private AccountRepository accountRepository

	public void setAccountRepository(AccountRepository accountRepository){
		this.accountRepository = accountRepository
	}

	/**
	 * Initiates the AccountService, finds all the accounts from DB
	 */
	void init(){
		accounts.addAll(accountRepository.findAll())
	}


	/**
	 * Creates Account for the given parameters.
	 * 
	 * @param acc
	 * @return
	 */
	def createAccount(def name, ACCOUNT_TYPE type){
		def account = accountRepository.create(name, type.toString())
		accounts.add(account)
	}

	/**
	 * Create accounting rule for given parameters.
	 * 
	 * @param rule
	 * @return
	 */
	def createRule(rule){
		println "create rule from ${rule.from} to ${rule.to}"
		assert accounts.name.contains(rule.from)
		assert accounts.name.contains(rule.to)
		accountingRules.put(rule.from, rule.to)
	}

	private Amount amountToBeTransferred
	private String fromAccount
	def transfer(Amount amount){
		this.amountToBeTransferred = amount
		return this
	}

	def from(String fromAccount){
		this.fromAccount = fromAccount
		return this
	}

	def to(String toAccount){
		assert accounts.name.contains(toAccount)
		assert accounts.name.contains(fromAccount)

		Account accountFrom = findAccount(fromAccount)
		Account accountTo = findAccount(toAccount)
		accountFrom.credit(amountToBeTransferred.value)
		accountTo.debit(amountToBeTransferred.value)

		accountRepository.update(accountTo)
		accountRepository.update(accountFrom)

		this.amountToBeTransferred = null
		this.fromAccount = null
	}

	/**
	 * Returns the status of given account name.
	 * 
	 * @param accountName
	 * @return
	 */
	def findStatus(accountName){
		Account account = findAccount(accountName)
		println "Name:"+account.name+" " +"Balance:"+ account.balance
	}

	/**
	 * Finds the account from account name.
	 * 
	 * @param accountName
	 * @return
	 */
	private Account findAccount(String accountName){
		Account account = accounts.find {account ->
			if(account.name == accountName) {
				return account
			}
		}
		return account
	}

	private Amount amountToBeShared
	private String[] sharedBetweenAccounts
	def share(Amount amount){
		this.amountToBeShared = amount
		return this
	}

	def between(String ... accountNames){
		this.sharedBetweenAccounts = accountNames
		return this
	}

	def and(String accountName){
		List<Account> accounts = new ArrayList<Account>()
		sharedBetweenAccounts.each {acc ->
			Account account = findAccount(acc)
			accounts.add(account)
		}
		accounts.add(findAccount(accountName))
		int totalShare = sharedBetweenAccounts.length+1

		BigDecimal totalAmount = amountToBeShared.getValue()
		BigDecimal eachShare = totalAmount.divide(new BigDecimal(totalShare), 7, RoundingMode.FLOOR)

		this.accounts.each {Account account ->
			account.debit(eachShare)
			accountRepository.update(account)
		}

		this.amountToBeShared = null
		this.sharedBetweenAccounts = null
	}
}

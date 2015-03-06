/**
 * Accounting Console
 */
create "Swapnil", personal
create "Ankit", personal
create "Ashish", personal

transfer 100.usd from "Swapnil" to "Ankit"
transfer 100.usd from "Ankit" to "Ashish"
transfer 200.usd from "Swapnil" to "Ankit"

status "Ankit"
status "Ashish"
status "Swapnil"

rule name:"my_rule", from:"Swapnil", to:"Ankit"

share 100.usd between "Swapnil","Ankit" and "Ashish"

status "Ankit"
status "Ashish"
status "Swapnil"
package ru.job4j.safe.bank

class BankService {
    private val users: HashMap<User, ArrayList<Account?>> = HashMap()

    fun addUser(user: User) {
        users.putIfAbsent(user, ArrayList())
    }

    private fun findByRequisite(passport: String?, requisite: String?): Account? {
        val user: User = findByPassport(passport) ?: return null
        return users[user]!!.stream()
            .filter { account: Account? ->
                account?.requisite == requisite
            }
            .findFirst()
            .orElse(null)
    }

    fun addAccount(passport: String?, account: Account?) {
        findByPassport(passport).let { users[it]?.add(account) }
    }

    fun findByPassport(passport: String?): User? {
        for (user in users.keys) {
            if (user.passport == passport) {
                return user
            }
        }
        return null
    }

    fun transferMoney(
        srcPassport: String?, srcRequisite: String?,
        destPassport: String?, descRequisite: String?, amount: Double
    ): Boolean {
        val source = findByRequisite(srcPassport, srcRequisite)
        val dest = findByRequisite(destPassport, descRequisite)
        val rsl = source != null && dest != null && source.balance > amount
        if (rsl) {
            source?.let { it.balance -= - amount }
            dest?.let { it.balance += amount }
        }
        return rsl
    }
}

fun main() {
    val bank = BankService()
    bank.addUser(User("321", "Petr Arsentev"))
    var user = bank.findByPassport("3211")
    println(user?.name)
    user = bank.findByPassport("321")
    println(user?.name)
}
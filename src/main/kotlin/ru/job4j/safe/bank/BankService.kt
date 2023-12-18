package ru.job4j.safe.bank

import kotlin.jvm.optionals.getOrNull

class BankService {
    private val users = HashMap<User, ArrayList<Account?>>()

    fun addUser(user: User) {
        users.putIfAbsent(user, ArrayList())
    }

    private fun findByRequisite(passport: String?, requisite: String?): Account? {
        val user: User = findByPassport(passport) ?: return null
        return users[user]!!
            .stream()
            .filter { it?.requisite == requisite }
            .findFirst()
            .orElse(null)
    }

    fun addAccount(passport: String?, account: Account?) {
        findByPassport(passport).let { users[it]?.add(account) }
    }

    fun findByPassport(passport: String?): User? {
        return users
            .keys
            .stream()
            .filter { it.passport == passport }
            .findFirst()
            .getOrNull()
    }

    fun transferMoney(
        srcPassport: String?,
        srcRequisite: String?,
        destPassport: String?,
        descRequisite: String?,
        amount: Double,
    ): Boolean {
        val source = findByRequisite(srcPassport, srcRequisite)
        val dest = findByRequisite(destPassport, descRequisite)
        if (source != null && dest != null && source.balance > amount) {
            return false
        }
        source?.let { it.balance -= amount }
        dest?.let { it.balance += amount }
        return true
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

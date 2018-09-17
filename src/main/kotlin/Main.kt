import br.com.swipe.context.EnvironmentFactory
import br.com.swipe.context.createAccount
import br.com.swipe.context.getAllAccounts
import br.com.swipe.models.Lang

fun main(args: Array<String>) {
    val environment = EnvironmentFactory()
    val wallet = environment.initSandbox("140d631950cd9f8cc964794228ea7ee56295e5a1860975f52e4a665815fee29b",
            "12bba2c89baa3a770db410dfacdb70893e5b36a001bd4885c827bc48e96dbbab",
            Lang.PT_BR)

    val account = wallet.createAccount()
    val getAccount = wallet.getAllAccounts()

}
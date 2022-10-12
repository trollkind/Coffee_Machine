package machine

enum class StartValues(val sValue: Int) {
    MONEY(550), WATER(400), MILK(540), BEANS(120), CUPS(9);
}

class CoffeeMachine {
    companion object {
        enum class CoffeeTypes(val printName: String, val water: Int, val milk: Int, val beans: Int, val price: Int) {
            ESPRESSO("espresso", 250, 0, 16, 4),
            LATTE("latte", 350, 75, 20, 7),
            CAPPUCCINO("cappuccino", 200, 100, 12, 6);
        }
    }

    private var money: Int = StartValues.MONEY.sValue
    private var water: Int = StartValues.WATER.sValue
    private var milk: Int = StartValues.MILK.sValue
    private var beans: Int = StartValues.BEANS.sValue
    private var cups: Int = StartValues.CUPS.sValue

    fun input(input: String) {
        val inpList = input.split(' ')
        when (inpList[0]) {
            "choose" -> chooseCoffee()
            "fill" -> fill(inpList[1], inpList[2])
            "take" -> takeMoney()
            "printRes" -> printRes()
        }
    }

    private fun printRes() {
        val resTally = "The coffee machine has:\n$water ml of water\n$milk ml of milk\n$beans g of coffee beans\n$cups disposable cups\n${'$'}$money of money"
        println("\n$resTally\n")
    }

    private fun takeMoney() {
        val tempMoney = money
        money = 0
        println("\nI gave you \$$tempMoney\n")
    }

    private fun fill(resToFill: String, amountSt: String) {
        val amount = amountSt.toIntOrNull()
        if (amount != null) {
            when (resToFill) {
                "water" -> water += amount
                "milk" -> milk += amount
                "beans" -> beans += amount
                "cups" -> cups += amount
            }
        } else println("Failed to refill $resToFill by $amountSt")
    }

    private fun checkResources(cType: CoffeeTypes): Boolean {
        val missingRes = mutableListOf<String>()
        if (cType.water > water) missingRes.add("water")
        if (cType.milk > milk) missingRes.add("milk")
        if (cType.beans > beans) missingRes.add("beans")
        if (cups < 1) missingRes.add("cups")
        return if (missingRes.isNotEmpty()) {
            println("Sorry, not enough ${missingRes.joinToString()}!\n")
            false
        } else true
    }

    private fun dispenseCoffee(cType: CoffeeTypes) {
        if (checkResources(cType)) {
            println("I have enough resources, making you a coffee!\n")
            money += cType.price
            water -= cType.water
            milk -= cType.milk
            beans -= cType.beans
            cups--
        }
    }

    private fun chooseCoffee() {
        val typeList = CoffeeTypes.values()
        print("\nWhat do you want to buy?")
        for (cofType in typeList) {
            print(" ${cofType.ordinal + 1} - ${cofType.printName},")
        }
        print(" back - to main menu:\n")
        val choice = readln()
        if (choice == "back") return else {
            if (choice.toIntOrNull() in 1..typeList.size) {
                val chosen: Int = choice.toInt() - 1
                dispenseCoffee(typeList[chosen])
            } else println("Not a known coffee type\n")
        }
    }
}


fun fillRes() {
    println("\nWrite how many ml of water you want to add:")
    cofMach.input("fill water ${readln()}")
    println("Write how many ml of milk you want to add:")
    cofMach.input("fill milk ${readln()}")
    println("Write how many grams of coffee beans you want to add:")
    cofMach.input("fill beans ${readln()}")
    println("Write how many disposable cups you want to add:")
    cofMach.input("fill cups ${readln()}")
    println()
}


val cofMach = CoffeeMachine()

fun main() {
    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> cofMach.input("choose")
            "fill" -> fillRes()
            "take" -> cofMach.input("take")
            "remaining" -> cofMach.input("printRes")
            "exit" -> break
            else -> println("Not a known command\n")
        }
    }
}

package nim.services.models

import kotlin.random.Random

class Game(var numMatches: Int?, var maxTake: Int?) {
    private var defaultNumMatches = 13
    private var defaultMaxTake = 3

    init {
        numMatches = numMatches ?: defaultNumMatches
        maxTake = maxTake ?: defaultMaxTake
    }

    fun playTurn(numMatchesToRemove: Int?): String {
        var validations = validations(numMatchesToRemove)

        if (validations.isNotEmpty()) return validations

        // player may remove matches
        numMatches = numMatches!! - numMatchesToRemove!!;

        // computer turn now.

        // Rule: Leave the opponent with one more than the multiple of the maxTake + 1.

        // multiple to divide with
        var multiple = maxTake!! + 1;

        // we can reach 1 position further since we return + 1 if we find the multiple
        var reach = maxTake!! + 2;

        // we start in 2 since we return + 1, hence, if we started at 1 we could be removing 0 matches
        for (i in 2 until reach) {
            if ((numMatches!! - i) % (multiple) == 0) {
                numMatches = numMatches!! - i + 1
                if (numMatches == 1) {
                    return "The computer won the game :(. Better luck next time!"
                }
                return "Computer removed ${i-1} matches, matches remaining are $numMatches."
            }
        }

        var random = Random.nextInt(1, maxTake!!)
        numMatches = numMatches!! - random

        return "Computer removed $random matches, matches remaining are $numMatches."
    }

    private fun validations(numMatchesToRemove: Int?): String {
        if (numMatches!! == 1) {
            return "This game is already over."
        } else if (
            numMatchesToRemove!! > maxTake!! ||
            numMatchesToRemove <= 0 ||
            numMatchesToRemove > numMatches!!
        ) {
            return "The number of matches to remove \"$numMatchesToRemove\" is not allowed."
        } else if (numMatchesToRemove == numMatches!!) {
            return "Sorry, you've lost the game :(."
        } else if (numMatches!! - numMatchesToRemove == 1) {
            numMatches = numMatches!! - numMatchesToRemove
            return "Congratulations!!! You won the game!"
        }
        return String()
    }
}
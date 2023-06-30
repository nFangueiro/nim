package nim.services.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GameTest {

    @ParameterizedTest(name = "given a game with \"{0}\" numMatches, \"{1}\" maxTake, when taking \"{2}\" matches, then it should return \"{3}\"")
    @MethodSource("plays")
    fun `given a game and input plays, should return correct play result`(
        numMatches: Int?,
        maxTake: Int?,
        play: Int?,
        expected: String
    ) {
        val game = Game(numMatches, maxTake)

        val result = game.playTurn(play)

        assertEquals(expected, result)
    }

    private companion object {
        @JvmStatic
        fun plays(): Stream<Arguments> = Stream.of(
            Arguments.of(-1, -1, 1, "Computer removed 3 matches, matches remaining are 9."),
            Arguments.of(1, null, 1, "This game is already over."),
            Arguments.of(null, 3, 4, "The number of matches to remove \"4\" is not allowed."),
            Arguments.of(null, null, -1, "The number of matches to remove \"-1\" is not allowed."),
            Arguments.of(2, null, 3, "The number of matches to remove \"3\" is not allowed."),
            Arguments.of(2, null, 2, "Sorry, you've lost the game :(."),
            Arguments.of(2, null, 1, "Congratulations!!! You won the game!"),
            Arguments.of(null, null, 1, "Computer removed 3 matches, matches remaining are 9."),
            Arguments.of(6, null, 3, "The computer won the game :(. Better luck next time!"),
        )
    }
}
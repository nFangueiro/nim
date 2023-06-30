package nim.controllers

import jakarta.validation.Valid
import nim.controllers.requests.StartGameRequest
import nim.services.interfaces.IGameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Validated
class GameController @Autowired constructor(private val gameService: IGameService) {

    @PostMapping("/start-game")
    fun startGame(@RequestBody @Valid startGameRequest: StartGameRequest): String =
        gameService.startGame(startGameRequest)

    @GetMapping("/all-games")
    fun allGames() = gameService.allGames()

    @PostMapping("/play-turn")
    fun playTurn(numMatches: Int): String = gameService.playTurn(numMatches)
}

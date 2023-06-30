package nim.services.interfaces

import nim.controllers.requests.StartGameRequest
import nim.repositories.entities.GameEntity

interface IGameService {
    fun allGames(): List<GameEntity>

    fun startGame(startGameRequest: StartGameRequest): String

    fun playTurn(numMatchesToRemove: Int): String
}
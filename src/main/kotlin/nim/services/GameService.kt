package nim.services

import nim.controllers.requests.StartGameRequest
import nim.mappers.entityFromGame
import nim.mappers.gameFromEntity
import nim.mappers.gameFromRequest
import nim.repositories.IGameRepository
import nim.repositories.entities.GameEntity
import nim.services.interfaces.IGameService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class GameService(val db: IGameRepository) : IGameService {

    override fun allGames(): List<GameEntity> = db.findAll().toList()

    override fun startGame(startGameRequest: StartGameRequest): String {

        var game = gameFromRequest(startGameRequest)

        var gameEntity = entityFromGame(game)

        db.save(gameEntity)

        return "Game has started!"
    }

    override fun playTurn(numMatchesToRemove: Int): String {

        var gameEntity: GameEntity

        try {
            gameEntity = db.findTopByOrderByTimestampDesc()
        } catch (e: EmptyResultDataAccessException) {
            return "No Game is in progress."
        }

        val game = gameFromEntity(gameEntity)

        val response = game.playTurn(numMatchesToRemove);

        gameEntity = entityFromGame(game);

        db.save(gameEntity)

        return response;
    }
}
package nim.mappers

import nim.controllers.requests.StartGameRequest
import nim.repositories.entities.GameEntity
import nim.services.models.Game
import java.sql.Timestamp
import java.time.Instant

fun gameFromRequest(gameRequest: StartGameRequest): Game = Game(gameRequest.numMatches, gameRequest.maxTake)

fun entityFromGame(game: Game): GameEntity = GameEntity(null, game.numMatches, game.maxTake, Timestamp.from(Instant.now()))

fun gameFromEntity(gameEntity: GameEntity) = Game(gameEntity.numMatches, gameEntity.maxTake);
package nim.repositories

import nim.repositories.entities.GameEntity
import org.springframework.data.repository.CrudRepository

interface IGameRepository : CrudRepository<GameEntity, String> {

    fun findTopByOrderByTimestampDesc() : GameEntity

}
package nim.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("GAME")
data class GameEntity(@Id var id: String?, var numMatches: Int?, var maxTake: Int?, val timestamp: Timestamp) {
}
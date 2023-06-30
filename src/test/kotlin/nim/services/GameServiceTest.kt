package nim.services

import nim.controllers.requests.StartGameRequest
import nim.repositories.IGameRepository
import nim.repositories.entities.GameEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.springframework.dao.EmptyResultDataAccessException
import java.sql.Timestamp
import java.time.Instant
import java.util.*

class GameServiceTest {
    @Mock
    private lateinit var gameRepository: IGameRepository

    @InjectMocks
    private lateinit var gameService: GameService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this) // Initializes the mocks
    }

    @Test
    fun `get all games from repository should return list`() {
        // Arrange
        val mockData = listOf(GameEntity("awd", 2, 2, Timestamp.from(Instant.now())))

        Mockito.`when`(gameRepository.findAll())
            .thenReturn(
                mockData
            )
        // Act
        val games = gameService.allGames();

        // Assert
        assertEquals(mockData, games)
        Mockito.verify(gameRepository).findAll() // Verify method invocation on the mock
    }


    @Test
    fun `given a StartGameRequest, the response should be game has started`() {
        // Arrange
        Mockito.`when`(gameRepository.save(ArgumentMatchers.any()))
            .thenReturn(null)

        val gameRequest = StartGameRequest(null, null);

        // Act
        val result = gameService.startGame(gameRequest);

        // Assert
        assertEquals("Game has started!", result)
    }

    @Test
    fun `given that there exists a game, the result should be the response of the play`() {
        // Arrange
        Mockito.`when`(gameRepository.findTopByOrderByTimestampDesc())
            .thenReturn(GameEntity(UUID.randomUUID().toString(), 13, 3, Timestamp.from(Instant.now())))

        Mockito.`when`(gameRepository.save(ArgumentMatchers.any()))
            .thenReturn(null)

        // Act
        val result = gameService.playTurn(1)

        // Assert
        assertEquals("Computer removed 3 matches, matches remaining are 9.", result)
    }

    @Test
    fun `given that there is no game, the result should be that there exists no game`() {
        // Arrange
        Mockito.`when`(gameRepository.findTopByOrderByTimestampDesc())
            .thenThrow(EmptyResultDataAccessException(0))

        Mockito.`when`(gameRepository.save(ArgumentMatchers.any()))
            .thenReturn(null)

        // Act
        val result = gameService.playTurn(1)

        // Assert
        assertEquals("No Game is in progress.", result)
    }
}
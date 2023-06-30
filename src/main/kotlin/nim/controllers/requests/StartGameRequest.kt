package nim.controllers.requests

import jakarta.validation.constraints.Min

data class StartGameRequest(
    @field:Min(1, message = "Minimum value is one")
    val numMatches: Int?,
    @field:Min(1, message = "Minimum value is one")
    var maxTake: Int?
) {}
package core.model

import core.model.base.Poll
import core.model.base.PollID
import core.model.base.PollTag
import core.model.base.VotingTime
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class OneToNPoll(
    override val id: PollID,
    override val question: String,
    override val votingTime: VotingTime,
    override val author: PollAuthor,
    override val showResponses: Boolean,
    override val isAnonymous: Boolean,
    override val isFinished: Boolean,
    override val audience: PollAudience,
    override val tags: List<PollTag>,
    val number: Int
) : Poll {
    override val pollType: PollType = PollType.ONE_TO_N

    override val options = OPTIONS_RANGE
        .take(number)
        .map {
            PollOption(UUID.randomUUID().toString(), it.toString())
        }

    companion object {
        fun options(number: Int): List<PollOption> {
            return OPTIONS_RANGE.take(number).map {
                PollOption(it.toString(), it.toString())
            }
        }

        val OPTIONS_RANGE = 1..10
    }
}

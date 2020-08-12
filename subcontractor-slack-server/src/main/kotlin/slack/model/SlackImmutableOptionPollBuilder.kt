package slack.model

import core.model.PollAuthor
import core.model.PollOption
import core.model.PollType
import core.model.base.PollTag
import java.time.LocalDateTime

class SlackImmutableOptionPollBuilder(
    id: String,
    author: PollAuthor,
    type: PollType,
    private val immutableOptions: List<PollOption>
) : SlackPollBuilder(id, author, type) {

    override var question: String = ""
    override var description: String? = null
    override var options: List<PollOption>
        get() = immutableOptions
        set(_) {}

    override var startTime: LocalDateTime? = null
    override var finishTime: LocalDateTime? = null
    override var tags: List<PollTag> = listOf()
    override var isFinished: Boolean = false
    override var advancedOption: PollAdvancedOption = DEFAULT_SETTINGS
    override var number: Int = defaultNumber(type)
    override var audience: SlackAudience = SlackAudience.EMPTY
}
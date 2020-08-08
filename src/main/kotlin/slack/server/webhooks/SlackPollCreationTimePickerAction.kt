package slack.server.webhooks

import com.slack.api.bolt.context.builtin.ActionContext
import com.slack.api.bolt.request.builtin.BlockActionRequest
import slack.model.PollBuilder
import slack.server.base.SlackBlockActionCommandWebhook
import slack.server.base.SlackBlockActionDataFactory
import slack.service.SlackPollCreationRepository
import slack.service.SlackRequestProvider
import slack.ui.create.CreationConstant
import slack.ui.create.CreationMetadata
import java.time.LocalDateTime
import java.time.LocalTime

abstract class SlackPollCreationTimePickerAction(
    provider: SlackRequestProvider,
    private val creationRepository: SlackPollCreationRepository
) : SlackBlockActionCommandWebhook<SlackPollCreationTimePickerData, CreationMetadata>(
    provider,
    SlackPollCreationTimePickerData.Companion,
    CreationMetadata::class.java
) {

    override fun handle(metadata: CreationMetadata, content: SlackPollCreationTimePickerData) {
        val pollBuilder = creationRepository.get(metadata.pollID) ?: throw IllegalArgumentException()
        updateBuilder(pollBuilder, content)
    }

    abstract fun updateBuilder(builder: PollBuilder, content: SlackPollCreationTimePickerData)
}

data class SlackPollCreationTimePickerData(val selectedTime: LocalTime) {
    companion object : SlackBlockActionDataFactory<SlackPollCreationTimePickerData> {
        override fun fromRequest(request: BlockActionRequest, context: ActionContext): SlackPollCreationTimePickerData {
            val selectedTimeString = request.payload.actions.first().selectedOption.value
            val selectedTime = LocalTime.parse(selectedTimeString, CreationConstant.TIME_FORMATTER)
            return SlackPollCreationTimePickerData(selectedTime)
        }
    }
}

class SlackPollCreationStartTimePickerAction(
    provider: SlackRequestProvider,
    creationRepository: SlackPollCreationRepository
) : SlackPollCreationTimePickerAction(provider, creationRepository) {
    override val actionID: String = CreationConstant.ActionID.START_TIME_PICKER

    override fun updateBuilder(builder: PollBuilder, content: SlackPollCreationTimePickerData) {
        builder.apply {
            startTime = (startTime ?: LocalDateTime.now())
                .withHour(content.selectedTime.hour)
                .withMinute(content.selectedTime.minute)
                .withSecond(0)
        }
    }
}


class SlackPollCreationFinishTimePickerAction(
    provider: SlackRequestProvider,
    creationRepository: SlackPollCreationRepository
) : SlackPollCreationTimePickerAction(provider, creationRepository) {
    override val actionID: String = CreationConstant.ActionID.FINISH_TIME_PICKER

    override fun updateBuilder(builder: PollBuilder, content: SlackPollCreationTimePickerData) {
        builder.apply {
            finishTime = (finishTime ?: LocalDateTime.now())
                .withHour(content.selectedTime.hour)
                .withMinute(content.selectedTime.minute)
                .withSecond(0)
        }
    }
}

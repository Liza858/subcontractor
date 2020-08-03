package slack.ui.create

import com.slack.api.model.view.View
import com.slack.api.model.view.Views.*
import slack.ui.base.SlackViewUIRepresentable

class AddOptionsPollView(choices: List<String>) : SlackViewUIRepresentable {
    private val blockView = AddOptionsPollBlockView(choices)

    override fun representIn(builder: View.ViewBuilder) {
        builder
            .type("modal")
            .submit(viewSubmit { it.text(VIEW_CREATE_BUTTON_TITLE).type("plain_text") })
            .close(viewClose { it.text(VIEW_CLOSE_BUTTON_TITLE).type("plain_text") })
            .title(viewTitle { it.text(VIEW_TITLE).type("plain_text") })
            .blocks(blockView.representation())
    }

    companion object {
        const val VIEW_TITLE = "Add options"
        const val VIEW_CLOSE_BUTTON_TITLE = "Close"
        const val VIEW_CREATE_BUTTON_TITLE = "Submit"
    }
}

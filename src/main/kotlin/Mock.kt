import core.model.*
import core.model.base.OptionID
import core.model.base.VotingTime

object MOCK {
    val OPTIONS = listOf(
        PollOption(
            "1",
            "Ace Wasabi Rock-n-Roll Sushi Bar",
            "Some additional information"
        ),
        PollOption(
            "2",
            "Super Hungryman Hamburgers"
        ),
        PollOption(
            "3",
            "Kagawa-Ya Udon Noodle Shop"
        )
    )
    val POLL = SingleChoicePoll(
        "1",
        "Where should we order lunch from?",
        null,
        OPTIONS,
        VotingTime.Unlimited,
        listOf(),
        false,
        showResponses = false,
        isAnonymous = false,
        author = PollAuthor("1", "some_name")
    )

    val VOTERS: Map<OptionID, List<VoterInfo>> = mapOf(
        "1" to listOf(
            VoterInfo(
                Voter("-1", VoteWork.Vote("1")),
                "https://api.slack.com/img/blocks/bkb_template_images/profile_1.png",
                "Michael Scott"
            ),
            VoterInfo(
                Voter("-2", VoteWork.Vote("1")),
                "https://api.slack.com/img/blocks/bkb_template_images/profile_2.png",
                "Dwight Schrute"
            ),
            VoterInfo(
                Voter("-3", VoteWork.Vote("1")),
                "https://api.slack.com/img/blocks/bkb_template_images/profile_3.png",
                "Pam Beasely"
            )
        ),
        "2" to listOf(
            VoterInfo(
                Voter("-4", VoteWork.Vote("2")),
                "https://api.slack.com/img/blocks/bkb_template_images/profile_4.png",
                "Angela"
            ),
            VoterInfo(
                Voter("-5", VoteWork.Vote("2")),
                "https://api.slack.com/img/blocks/bkb_template_images/profile_2.png",
                "Dwight Schrute"
            )
        ),
        "3" to listOf()
    )
}

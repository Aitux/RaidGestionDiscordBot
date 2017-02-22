package command;

/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
import net.dv8tion.jda.core.entities.AudioChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import persistance.MetaData;

public class SetRaidHere extends ICommand {

	public SetRaidHere(GuildMessageReceivedEvent e) {
		super(e);
	}

	@Override
	public void execute() {
		AudioChannel set = this.event.getMember().getVoiceState().getAudioChannel();
		if (set == null) {
			out.sendMessage(MetaData.PRB_MSG_STRT
					+ ">\tAudiochannel is null\n>\tYou probably need to go in an Audio channel to be able to set one :spy:\n")
					.queue();
		} else {
			md.setRaid(set);
			out.sendMessage("Raid's channel set to: "
					+ this.event.getGuild().getVoiceChannelById(md.getRaid().getId()).getName()).queue();
		}
	}

}

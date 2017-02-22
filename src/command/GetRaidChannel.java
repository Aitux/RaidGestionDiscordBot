package command;

/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class GetRaidChannel extends ICommand {

	public GetRaidChannel(GuildMessageReceivedEvent e) {
		super(e);
	}

	@Override
	public void execute() {
		if (md.getRaid() != null) {
			out.sendMessage(
					"Raid's channel is: " + event.getGuild().getVoiceChannelById(md.getRaid().getId()).getName())
					.queue();
		} else {
			out.sendMessage(
					"At the moment there is no raid's channel you can go in an Audio channel and type !setRaidHere to set the position of your raid.")
					.queue();
		}
	}
}

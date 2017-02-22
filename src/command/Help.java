package command;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import persistance.MetaData;

/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
public class Help extends ICommand{

	public Help(GuildMessageReceivedEvent event) {
		super(event);
	}

	@Override
	public void execute() {
		StringBuilder strB = new StringBuilder();
		TextChannel out = event.getChannel();
		out.sendMessage("If you need explanation on a command just use !man").queue();
		strB.append("```");
		md.getCommand().forEach(item->{
			strB.append(item+" | ");
		});
		strB.append("```");
		if(strB.length() > MetaData.MAX_MSG_SIZE ){
			out.sendMessage("Length of the string is > 2000 Discord can't handle such a big message.").queue();
		}
		strB.setLength(MetaData.MAX_MSG_SIZE);
		out.sendMessage("Here's a list of all the documented command.").queue();
		out.sendMessage(strB.toString()).queue();
	}

}

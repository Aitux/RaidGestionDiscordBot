package command;

/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import persistance.MetaData;

public class SetRaidLeader extends ICommand {
	private String req;

	public SetRaidLeader(GuildMessageReceivedEvent event, String req) {
		super(event);
		this.req = req;
	}

	@Override
	public void execute() {
		String[] part = req.split(" ");
		int flagNbNamePerServer = 0;
		if (part.length < 2 || part.length > 2) {
			out.sendMessage(MetaData.PRB_MSG_STRT
					+ ">\tThere is a problem of argument.\n>\tTry something like: \n```!setRaidLeader NameOfAGreatLeader```")
					.queue();
		} else {

			String leader = part[1];

			List<Member> listMem = event.getGuild().getMembers();
			for (Member mem : listMem) {
				if (mem.getEffectiveName().equals(leader) && !mem.getUser().isBot()) {
					flagNbNamePerServer++;
				}
			}

			if (flagNbNamePerServer == 1) {
				out.sendMessage("The new leader is: " + leader).queue();
				md.setLeader(leader);
			}

			if (flagNbNamePerServer > 1) {
				out.sendMessage(MetaData.PRB_MSG_STRT
						+ ">\tIt seems that there are several people with the same name on the server."
						+ "\n>\tIt is impossible to instantiate a raid leader under these conditions."
						+ "\n>\tI'm not speaking about nicknames, but about effective names.").queue();
			}

			if (!md.getLeader().equals(leader)) {
				out.sendMessage(MetaData.PRB_MSG_STRT + ">\tThere is a problem of name.\n>\t" + leader
						+ " not found on the server.\n>\tOr it is a bot (and obviously bot cannot be Great raid leader).")
						.queue();
			}
		}
	}

}

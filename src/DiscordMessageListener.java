import java.util.List;

import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.entities.impl.VoiceChannelImpl;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.managers.GuildManager;

public class DiscordMessageListener extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		// TODO Auto-generated method stub
		super.onGuildMessageReceived(e);
		DiscordStream out = new DiscordStream(e.getChannel());
		if (e.getMessage().getContent().startsWith("!rl")) {
			out.printToDiscord("__Mon premier message donc forcement: Hello World__");
			GuildManager gm = new GuildManager(e.getGuild());
			VoiceChannel vc = e.getGuild().getVoiceStatusOfUser(e.getAuthor()).getChannel();
			if (vc != null) {
				List<User> userlist = vc.getUsers();
				for (User u : userlist) {
					if (!u.equals(e.getAuthor()))
						gm.mute(u);
				}
			} else {
				out.printToDiscord(
						"Tu ne peux pas mute ton channel si tu n'es pas dans un channel toi même fdp. :expressionless:");
			}

			if (e.getAuthor().getUsername().equals("Tidwen")) {
				e.getMessage().deleteMessage();
				out.printToDiscord(
						"__" + e.getAuthor().getUsername() + " vous n'avez pas le droit à la parole ici !__");
				out.setPrivate(e.getAuthor().getPrivateChannel());
				out.printPrivate("Veuillez ne pas troubler l'ordre sur le serveur sous peine de bannissement !");
			}

		}
	}
}
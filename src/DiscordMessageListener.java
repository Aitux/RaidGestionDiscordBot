import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class DiscordMessageListener extends ListenerAdapter{
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		// TODO Auto-generated method stub
		super.onGuildMessageReceived(e);
		DiscordStream out = new DiscordStream(e.getChannel());
		if(e.getMessage().getContent().startsWith("!rl")){
			out.printToDiscord("__Mon premier message donc forcement: Hello World__");
		}
	}
}

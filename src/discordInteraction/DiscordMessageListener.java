package discordInteraction;
/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.3
*
* Date: 22 févr. 2017
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import command.GetRaidChannel;
import command.Help;
import command.ICommand;
import command.Man;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import persistance.MetaData;

public class DiscordMessageListener extends ListenerAdapter {

	public List<String> command;
	public boolean commandsetup = false;
	List<ICommand> useCommand;
	MetaData md = MetaData.getInstance();

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		super.onGuildMessageReceived(e);
		String req = e.getMessage().getContent();

		if (!commandsetup) {
			setup(e);
			md.setup(command);
			
		}
		if (!e.getAuthor().isBot()) {
			if(req.startsWith("!help")){
				new Help(e).execute();
			}
			
			if (req.startsWith("!man")) {
				new Man(e, req).execute();
			}
			if (req.startsWith("!setRaidChannel")) {
				new command.SetRaidHere(e).execute();
			}
			if (req.startsWith("!getRaidChannel")) {
				new GetRaidChannel(e).execute();
			}
			if(req.startsWith("!setRaidLeader")){
				
			}
		}

	}
	
	void setup(GuildMessageReceivedEvent e) {
		command = new ArrayList<>();
		File[] files = new File("c:/manualD/").listFiles();
		for (int i = 0; i < files.length; i++) {
			String fdName = files[i].getName();
			fdName = fdName.substring(0, fdName.length() - 3);
			command.add(fdName);
		}

		// command.forEach(System.out::println);
		this.commandsetup = true;
	}


}

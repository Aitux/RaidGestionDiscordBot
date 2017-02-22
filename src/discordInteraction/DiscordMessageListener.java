package discordInteraction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import command.GetRaidChannel;
import command.Help;
import command.ICommand;
import command.Man;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import persistance.MetaData;

public class DiscordMessageListener extends ListenerAdapter {

	public List<String> command;
	public boolean commandsetup = false;
	List<ICommand> useCommand;
	String chemin;
	MetaData md = MetaData.getInstance();

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		super.onGuildMessageReceived(e);
		String req = e.getMessage().getContent();

		if (!commandsetup) {
			setup(e);
			md.setup(command, chemin);
			
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
		File config = new File("config.ini");
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(config);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			prop.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String chemin = prop.getProperty("man-page-position");
		File[] files = new File(chemin).listFiles();
		for (int i = 0; i < files.length; i++) {
			String fdName = files[i].getName();
			fdName = fdName.substring(0, fdName.length() - 3);
			command.add(fdName);
		}

		command.forEach(System.out::println);
		this.commandsetup = true;
		this.chemin = chemin;
	}


}

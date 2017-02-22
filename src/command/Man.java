package command;
/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.5
*
* Date: 22 févr. 2017
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import persistance.MetaData;

public class Man extends ICommand{

	private String req;
	
	public Man(GuildMessageReceivedEvent event, String req) {
		super(event);
		this.req = req;
	}

	@Override
	public void execute() {

		String str = "";
		String[] part = req.split(" ");
		if (part.length < 2 || part.length > 2) {
			out.sendMessage(MetaData.PRB_MSG_STRT
					+ ">\tThere is a problem of argument.\n>\tTry something like: \n```!man setRaidHere```").queue();
		}

//		System.out.println(md.getPathToCommand());
		File manpage = new File(md.getPathToCommand() + part[1] + ".md");
		if (manpage.exists()) {
			try {
				BufferedReader bfR = new BufferedReader(new FileReader(manpage));
				while ((str = bfR.readLine()) != null) {
//					System.out.println(str);
					try {
						out.sendMessage(str).queue();
					} catch (UnsupportedOperationException e3) {
						str = ">";
						out.sendMessage(str).queue();
					}
				}
				bfR.close();

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			out.sendMessage(MetaData.PRB_MSG_STRT
					+ ">\tThere is a problem of argument.\n>\tLooks like the command you are looking for does not exist.")
					.queue();
		}
	
	}
	
}

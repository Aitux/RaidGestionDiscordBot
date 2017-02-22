package discordInteraction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class DiscordMain {

	public static void main(String[] args) {
		Properties prop = new Properties();
		File f = new File("C:\\Users\\simon\\Documents\\propertiesRaid.txt");
		
		try {
			FileInputStream fl = new FileInputStream(f);
			prop.load(fl);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		try{
			final JDA jda = new JDABuilder(AccountType.BOT).setToken(prop.getProperty("token")).setBulkDeleteSplittingEnabled(false).buildBlocking();
			jda.addEventListener(new DiscordMessageListener());
		}catch(Exception e){
			System.exit(0);
		}
	}
}
	
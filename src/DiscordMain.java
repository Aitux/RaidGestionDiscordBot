import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

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
			final JDA jda = new JDABuilder().setBotToken(prop.getProperty("token")).setBulkDeleteSplittingEnabled(false).buildBlocking();
		}catch(Exception e){
			System.exit(0);
		}
	}
}

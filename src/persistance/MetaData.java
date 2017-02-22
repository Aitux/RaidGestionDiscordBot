package persistance;
/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
import java.util.List;

import net.dv8tion.jda.core.entities.AudioChannel;

public class MetaData
{
	public static final String PRB_MSG_STRT = "Looks like there is a problem ! :innocent:\n";
	public static final int MAX_MSG_SIZE = 2000;
	private AudioChannel raid;
	private List<String> commandName;
	private String pathToCommand = "";
	
	/** Constructeur privé */
	private MetaData()
	{
		
	}
 
	/** Instance unique non préinitialisée */
	private static MetaData INSTANCE = null;
 
	/** Point d'accès pour l'instance unique du singleton */
	public static synchronized MetaData getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new MetaData();	
		}
		return INSTANCE;
	}
	
	public void setup(List<String> commandName, String path){
		this.commandName = commandName;
		this.pathToCommand = path;
	}

	public AudioChannel getRaid() {
		return raid;
	}

	public List<String> getCommand() {
		return commandName;
	}

	public void setRaid(AudioChannel raid) {
		this.raid = raid;
	}

	public String getPathToCommand() {
		return pathToCommand;
	}
}
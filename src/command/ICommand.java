package command;
/**
* @author Simon "Aitux" Vandeputte
*
* @version v0.1
*
* Date: 22 févr. 2017
*/
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import persistance.MetaData;


public abstract class ICommand {
	protected GuildMessageReceivedEvent event;
	protected MetaData md = MetaData.getInstance();
	protected TextChannel out;
	
	
	/**
	 * Ce constructeur permet d'initialiser les attributs nécessaires au fonctionnement des commandes.
	 * @param event
	 */
	public ICommand(GuildMessageReceivedEvent event){
		this.event = event;
		this.out = event.getChannel();
	}
	
	/**
	 * Le traitement de la commande se fera dans cette méthode. 
	 */
	public abstract void execute();
	
	
	
}

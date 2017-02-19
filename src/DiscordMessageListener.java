import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

public class DiscordMessageListener extends ListenerAdapter {
	List<Squatter> relou = new ArrayList<>();

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		super.onGuildMessageReceived(e);
		int i = 0;
		boolean aituxIsHere = false;
		GuildController gm = e.getGuild().getController();
		VoiceChannel vc = e.getGuild().getVoiceChannelById("146727327567577088");
		List<Member> listMember = vc.getMembers();
		if (!listMember.isEmpty()) {
			for (Member m : listMember) {
				if (m.isOwner())
					aituxIsHere = true;
			}
			if (!aituxIsHere) {
				listMember = vc.getMembers();
				for (Member m : listMember) {
					boolean isInside = false;
					PrivateChannel pc = null;
					try {
						for (Squatter sq : relou) {
							if (sq.getName().equals(m.getEffectiveName()))
								isInside = true;
							if (m.getEffectiveName().equals(sq.getName())) {
								if (sq.isDegageable()) {
									List<Role> lr = m.getRoles();
									List<Role> role = e.getGuild().getRolesByName("prisonnier", true);	
									gm.addRolesToMember(m,role).queue();
									gm.removeRolesFromMember(m, lr).queue();
									pc = sq.getMember().getUser().openPrivateChannel().block();
									pc.sendMessage("Dehors !!! :point_right: ").queue();
									pc.sendMessage("Pour récuperer ton ancien statut envoie moi: !pardonAitux").queue();
									gm.moveVoiceMember(m, e.getGuild().getVoiceChannelById("271397739357667328"))
											.queue();
									sq.pardon();

								} else {
									pc = sq.getMember().getUser().openPrivateChannel().block();
									int rektanceLevel = sq.rekt();
									switch (rektanceLevel) {
									case 0:
										pc.sendMessage(
												"L'empereur n'étant pas présent je vous demanderais de bien vouloir sortir du palais.")
												.queue();
										pc.sendMessage("Ceci est votre premier avertissement.").queue();
										break;
									case 1:
										pc.sendMessage("Ceci est votre deuxième avertissement.").queue();
										break;
									case 2:
										pc.sendMessage("Ceci est votre dernier avertissement.").queue();
										break;
									}
								}
							}
						}
						if (!isInside) {
							relou.add(new Squatter(m));
						}

					} catch (RateLimitedException e1) {
						e1.printStackTrace();
					}

				}
			}
		}
	}

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		super.onPrivateMessageReceived(event);
		if (!event.getAuthor().isBot()) {
			try {
				PrivateChannel pc = event.getAuthor().openPrivateChannel().block();

				if (event.getMessage().getContent().contains("!pardonAitux")) {
					pc.sendMessage(
							":regional_indicator_f: :regional_indicator_u: :regional_indicator_c: :regional_indicator_k:      :regional_indicator_y: :regional_indicator_o: :regional_indicator_u:   :middle_finger: ")
							.queue();
				} else {
					pc.sendMessage("J'entend pas les rageux :sunglasses:").queue();
				}
			} catch (RateLimitedException e) {
				e.printStackTrace();
			}
		}
	}
}

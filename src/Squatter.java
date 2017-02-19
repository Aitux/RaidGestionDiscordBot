import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

public class Squatter {
	private Member m;
	private String name;
	private int rappelOrdre;
	private List<Role> role;
	
	public Squatter(Member m){
		this.m = m;
		this.name = m.getEffectiveName();
		this.role = m.getRoles();
		rappelOrdre = 0;
	}
	
	public void pardon(){
		this.rappelOrdre = 0;
	}
	public boolean isDegageable(){
		return rappelOrdre >= 3;
	}

	public int rekt() {
		return this.rappelOrdre++;
	}

	public Member getMember() {
		return m;
	}

	public String getName() {
		return name;
	}
	
}

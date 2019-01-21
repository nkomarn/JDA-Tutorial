package techtoolbox.Television;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import techtoolbox.Television.Commands.Clear;
import techtoolbox.Television.Events.GuildMemberJoin;
import techtoolbox.Television.Events.GuildMemberLeave;

public class Television {
	public static JDA jda;
	public static String prefix = "~";
	
	// Main method
	public static void main(String[] args) throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("NTI4MDA2MjM4MTMxNDUzOTgz.DwmJsw.DlFSxU9QNiT4jgW65H3DumHSa0c").build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setGame(Game.watching("boring comedy shows."));
		
		jda.addEventListener(new Clear());
		jda.addEventListener(new GuildMemberJoin());
		jda.addEventListener(new GuildMemberLeave());
	}
}

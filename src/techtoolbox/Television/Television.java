package techtoolbox.Television;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Television {
	public static JDA jda;
	public static String prefix = "~";
	
	// Main method
	public static void main(String[] args) throws LoginException {
		// Have fun with my token ;D
		jda = new JDABuilder(AccountType.BOT).setToken("NTI4MDA2MjM4MTMxNDUzOTgz.DwhdRw.RK_7hRdJwfuu_6ny-EDdFJ_pSEQ").build();
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		jda.getPresence().setGame(Game.watching("boring comedy shows."));
		
		jda.addEventListener(new Commands());
	}
}

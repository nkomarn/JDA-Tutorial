package mykyta.JDA;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class Main {
	public static void main(String[] args) throws LoginException {
		JDABuilder builder = new JDABuilder("NTI4MDA2MjM4MTMxNDUzOTgz.D3kykg.R8_nANCEa50CjGkQcxUExKfk7QA");
		builder.setStatus(OnlineStatus.IDLE);
		builder.setGame(Game.listening("1's and 0's."));
		builder.addEventListener(new GuildMessageReceived());
		builder.build();
	}
}

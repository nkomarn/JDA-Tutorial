package techtoolbox.Television.Events;

import java.util.Random;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildMemberLeave extends ListenerAdapter {
	
	String[] messages = {
			"[member] left, the party's over."
	};
	
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		Random rand = new Random();
		int number = rand.nextInt(messages.length);
		
		EmbedBuilder join = new EmbedBuilder();
		join.setColor(0xf48342);
		join.setDescription(messages[number].replace("[member]", event.getMember().getAsMention()));
	
		event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
		
	}
}

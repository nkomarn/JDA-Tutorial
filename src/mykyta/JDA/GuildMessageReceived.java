package mykyta.JDA;


import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildMessageReceived extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase("test")) {
			
			// Get channel name and send in current channel
			String channelName = event.getChannel().getName();
			event.getChannel().sendMessage(channelName).queue();
			
			// Upload and send an image to current channel
			try {
				URL url = new URL("https://i.imgur.com/Jc4HEGN.jpg");
				BufferedImage img = ImageIO.read(url);
				File file = new File("temp.jpg"); // change the '.jpg' to whatever extension the image has
				ImageIO.write(img, "jpg", file); // again, change 'jpg' to the correct extension
				event.getChannel().sendFile(file).queue();
			}
			catch (Exception e) {
				event.getChannel().sendMessage("Error fetching image.").queue();
			}
		}
		
		if (args[0].equalsIgnoreCase("~mute")) {
			if (args.length > 1 && args.length < 3) {
				Member member = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				Role role = event.getGuild().getRoleById("547944686191640596");
				
				if (!member.getRoles().contains(role)) {
					// Mute user
					event.getChannel().sendMessage("Muted " + args[1] + ".").queue();
					event.getGuild().getController().addRolesToMember(member, role).complete();
				}
				else {
					// Unmute user
					event.getChannel().sendMessage("Unmuted " + args[1] + ".").queue();
					event.getGuild().getController().removeRolesFromMember(member, role).complete();
				}
			}
			else if (args.length == 3) {
				Member member = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">", ""));
				Role role = event.getGuild().getRoleById("547944686191640596");
				
				event.getChannel().sendMessage("Muted " + args[1] + " for " + args[2] + " seconds.").queue();
				event.getGuild().getController().addRolesToMember(member, role).complete();
				
				// Unmute after a few seconds
				new java.util.Timer().schedule(
					new java.util.TimerTask() {
						@Override
						public void run() {
							event.getChannel().sendMessage("Unmuted " + args[1] + ".").queue();
							event.getGuild().getController().removeRolesFromMember(member, role).complete();
						}
					},
					Integer.parseInt(args[2]) * 1000
				);
			}
			else {
				event.getChannel().sendMessage("Incorrect syntax. Use `~mute [user mention] [time {optional}]`").queue();
			}
		}
	}
}

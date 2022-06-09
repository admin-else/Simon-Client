package de.sqrt.simon.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import de.sqrt.simon.Simon;
import de.sqrt.simon.hack.HackList;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class Command {
	
	static String hack;
	static String setting;
	static double value;
	
	public static void init() {
		ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("simon").executes(context -> {
			manager(0);
			hack=null;
			setting=null;
			value=-4321;
			return 1;
		}).then(ClientCommandManager.argument("hack", StringArgumentType.string()).executes(ctx -> {
			hack = StringArgumentType.getString(ctx, "hack");
			manager(1);
			return 1;
		}).then(ClientCommandManager.argument("setting", StringArgumentType.string()).executes(ctx -> {
			StringArgumentType.getString(ctx, "setting");
			hack = StringArgumentType.getString(ctx, "hack");
			setting = StringArgumentType.getString(ctx, "setting");
			manager(2);
			return 1;
		}).then(ClientCommandManager.argument("value", IntegerArgumentType.integer()).executes(ctx ->{
			IntegerArgumentType.getInteger(ctx, "value");
			hack = StringArgumentType.getString(ctx, "hack");
			setting = StringArgumentType.getString(ctx, "setting");
			value = IntegerArgumentType.getInteger(ctx, "value");
			manager(3);
			return 1;
		})))));
	}
	
	private static void manager(int status) {
		
		String msg = "No feed back.";
		HackList list = Simon.getSimon().getList();
		
		if(hack!=null){
		switch (hack) {
		case "fly":
			msg = list.fly.interact(setting, value);
			break;
		default:
			break;
		}
		}else msg = "Simon Client bullet points:/n"
				+ " - 1.18.2/n"
				+ " - Fabric/n"
				+ " - no GUI/n"
				+ " - Made by Simon (:/n"
				+ "/n"
				+ "Hacks: /n"
				+ " - Fly";
		sendMessage(msg);
	}
	
	public static void sendMessage(String msg) {
		MinecraftClient mc = MinecraftClient.getInstance();
		String[] msgs = msg.split("\n");
		for (int i = 0; i < msgs.length; i++) {
			mc.inGameHud.addChatMessage(MessageType.SYSTEM,new LiteralText(msgs[i]), mc.player.getUuid());
		}
	}
}

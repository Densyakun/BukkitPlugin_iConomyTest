package org.densyakun.bukkit.iconomytest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.iCo6.iConomy;
import com.iCo6.system.Account;
import com.iCo6.util.Messaging;
import com.iCo6.util.Template;
public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	@EventHandler
	public void BlockBreak(BlockBreakEvent e) {
		//iConomy.Database.getDatabase().setArgument(e.getPlayer().getName(), "balance", 1000, true);
		//e.getPlayer().sendMessage(iConomy.Database.getDatabase().getArguments(e.getPlayer().getName()).getValue("balance"));
		Account account = new Account(e.getPlayer().getName());
		account.getHoldings().add(1000);

		iConomy.Template.set(Template.Node.PLAYER_CREDIT);
		iConomy.Template.add("name", e.getPlayer().getName());
		iConomy.Template.add("amount", 1000);

		Messaging.send(e.getPlayer(), iConomy.Template.color(Template.Node.TAG_MONEY) + iConomy.Template.parse());
	}
}

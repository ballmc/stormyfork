package dev.stormy.client.module.modules.client;

import dev.stormy.client.clickgui.ClickGui;
import dev.stormy.client.main.Stormy;
import dev.stormy.client.module.Module;
import dev.stormy.client.module.setting.impl.ComboSetting;
import dev.stormy.client.utils.Utils;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.weavemc.loader.api.event.SubscribeEvent;
import net.weavemc.loader.api.event.TickEvent;

public class ClickGuiModule extends Module {
    public static ComboSetting<Colors> clientTheme;

    public ClickGuiModule() {
        super("ClickGui", Module.ModuleCategory.Client, 28);
        this.registerSetting(clientTheme = new ComboSetting<>("Theme", Colors.Amethyst));
    }

    private final KeyBinding[] moveKeys = new KeyBinding[]{
            mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint
    };

    @Override
    public void onEnable() {
        if (Utils.Player.isPlayerInGame() && mc.currentScreen != Stormy.clickGui) {
            mc.displayGuiScreen(Stormy.clickGui);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        for (KeyBinding bind : moveKeys) {
            bind.pressed = GameSettings.isKeyDown(bind);
        }
    }

    @Override
    public void onDisable() {
        if (Utils.Player.isPlayerInGame() && mc.currentScreen instanceof ClickGui) {
            mc.displayGuiScreen(null);
        }
    }

    public enum Colors {
        Tryfle, Sassan, Gold, Steel, Emerald, Orange, Amethyst, Lily
    }
}
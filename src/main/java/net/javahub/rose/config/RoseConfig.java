package net.javahub.rose.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import java.util.ArrayList;
import java.util.List;

@Config(name = "rose")
@Config.Gui.Background("minecraft:textures/block/sandstone_bottom.png")
public class RoseConfig implements ConfigData, ModMenuApi {

    @ConfigEntry.Gui.Excluded
    public static final RoseConfig CONFIG = AutoConfig.register(RoseConfig.class, GsonConfigSerializer::new).getConfig();

    public final List<Source> resourcePacks = new ArrayList<>();
    public final List<Source> worldSaves = new ArrayList<>();

    public static class Source {
        public String path = "";
        public boolean isEnabled = true;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(RoseConfig.class, parent).get();
    }
}

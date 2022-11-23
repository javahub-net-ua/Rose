package net.javahub.rose.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@Config(name = "rose")
@Config.Gui.Background("minecraft:textures/block/sandstone_top.png")
public class RoseConfig implements ConfigData {

    public final List<Source> sources = new ArrayList<>();

    public static class Source {
        public String path = "";
        public boolean isEnabled = true;
    }
}

package net.javahub.rose.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.javahub.rose.Rose;

import java.util.ArrayList;
import java.util.List;

@Config(name = Rose.MOD_ID)
public class RoseConfig implements ConfigData {

    public List<Source> sources = new ArrayList<>();

    public static class Source {
        private String path;
        private boolean isEnabled;

        public String getPath() {
            return path;
        }

        public boolean isEnabled() {
            return isEnabled;
        }
    }
}

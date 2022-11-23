package net.javahub.rose;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.javahub.rose.config.RoseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rose implements ModInitializer {

    public static final String MOD_ID = "rose";
    public static final RoseConfig CONFIG = AutoConfig.register(RoseConfig.class, Toml4jConfigSerializer::new).getConfig();
    public static final Logger LOGGER = LoggerFactory.getLogger(Rose.MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("JAVAHUB");
    }
}

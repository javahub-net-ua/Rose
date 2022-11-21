package net.javahub.rose.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import net.javahub.rose.Rose;

import java.util.ArrayList;
import java.util.List;

@Modmenu(modId = Rose.MOD_ID)
@Config(name = Rose.MOD_ID, wrapperName = "RoseConfig")
public class RoseConfigModel {
    public List<String> sources = new ArrayList<>();
}

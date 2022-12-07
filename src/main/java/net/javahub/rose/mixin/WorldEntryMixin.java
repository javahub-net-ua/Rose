package net.javahub.rose.mixin;

import net.javahub.rose.resources.WorldSavesProvider;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.world.level.storage.LevelSummary;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.nio.file.Path;

@Mixin(WorldListWidget.WorldEntry.class)
public class WorldEntryMixin {

    @Shadow
    @Final
    private LevelSummary level;

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 0)
    public String getDisplayName(String name) {
        return level.getDisplayName()
                + (WorldSavesProvider.isFromRose(level.getName())
                ? " (via Rose)" : " (local)");
    }

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 1)
    public String getName(String sign) {
        String name = sign.replaceAll(" \\(.*\\)", "");
        String time = StringUtils.difference(name, sign);
        return Path.of(name).getFileName() + time;
    }
}

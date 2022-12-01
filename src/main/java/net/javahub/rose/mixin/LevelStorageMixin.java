package net.javahub.rose.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.javahub.rose.resources.WorldSavesProvider;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(LevelStorage.class)
public class LevelStorageMixin {

    @Inject(method = "getLevelList", at = @At("RETURN"), cancellable = true)
    private void getLevelList(CallbackInfoReturnable<LevelStorage.LevelList> cir) throws IOException {
        List<LevelStorage.LevelSave> saves = WorldSavesProvider.getLevelSaves();
        cir.setReturnValue(new LevelStorage.LevelList(saves));
    }
}

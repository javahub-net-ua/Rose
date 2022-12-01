package net.javahub.rose.mixin;

import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(LevelStorage.LevelSave.class)
public class LevelSaveMixin {

    @Shadow @Final
    private Path path;

    @Inject(method = "getRootPath", at = @At("TAIL"), cancellable = true)
    public void getRootPath(CallbackInfoReturnable<String> cir) {
        String path = this.path.toAbsolutePath().toString();
        cir.setReturnValue(path);
    }
}

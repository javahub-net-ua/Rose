package net.javahub.rose.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.javahub.rose.resources.ResourcesProvider;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(ResourcePackManager.class)
public class ResourcePacksMixin {
    @Mutable
    @Shadow
    @Final
    private Set<ResourcePackProvider> providers;

    @Inject(method = "<init>(Lnet/minecraft/resource/ResourcePackProfile$Factory;" +
            "[Lnet/minecraft/resource/ResourcePackProvider;)V", at = @At("TAIL"))
    private void registerLoader(CallbackInfo info) {
        this.providers = new HashSet<>(this.providers);
        this.providers.add(new ResourcesProvider());
    }
}
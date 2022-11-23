package net.javahub.rose.resources;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.javahub.rose.config.RoseConfig;
import net.minecraft.resource.*;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ResourcesProvider implements ResourcePackProvider {

    public static final RoseConfig CONFIG = AutoConfig.register(RoseConfig.class, Toml4jConfigSerializer::new).getConfig();

    public static Set<File> getSources() {
        return CONFIG.sources.stream().filter(source -> source.isEnabled)
                .map(source -> source.path).map(File::new).collect(Collectors.toSet());
    }

    private Supplier<ResourcePack> getResourceResource(File file) {
        return file.isDirectory() ?
                () -> new DirectoryResourcePack(file) :
                () -> new ZipResourcePack(file);
    }

    private ResourcePackProfile registerResourcePack(String id, File file, ResourcePackProfile.Factory factory) {
        return ResourcePackProfile.of(id, false, getResourceResource(file), factory,
                ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_NONE);
    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        Set<File> files = getSources();
        files.stream().map(f -> registerResourcePack(String.valueOf(f.hashCode()), f, factory))
                .filter(Objects::nonNull).forEach(profileAdder);
    }
}

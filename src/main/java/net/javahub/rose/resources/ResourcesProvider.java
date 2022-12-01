package net.javahub.rose.resources;

import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ZipResourcePack;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static net.javahub.rose.config.RoseConfig.CONFIG;

public class ResourcesProvider implements ResourcePackProvider {

    public static Set<File> getSources() {
        return CONFIG.resourcePacks.stream().filter(source -> source.isEnabled)
                .map(source -> source.path).map(File::new).collect(Collectors.toSet());
    }

    private Supplier<ResourcePack> getResourcesType(File file) {
        return file.isDirectory() ?
                () -> new DirectoryResourcePack(file) :
                () -> new ZipResourcePack(file);
    }

    private ResourcePackProfile registerResourcePack(String id, File file, ResourcePackProfile.Factory factory) {
        return ResourcePackProfile.of(id, false, getResourcesType(file), factory,
                ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_NONE);
    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        Set<File> files = getSources();
        files.stream().map(f -> registerResourcePack(String.valueOf(f.hashCode()), f, factory))
                .filter(Objects::nonNull).forEach(profileAdder);
    }
}

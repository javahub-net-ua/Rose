package net.javahub.rose.resources;

import net.minecraft.resource.*;

import java.io.File;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static net.javahub.rose.Rose.CONFIG;
import static net.javahub.rose.Rose.LOGGER;

public class ResourcesProvider implements ResourcePackProvider {

    public static Set<File> getSources() {
        return CONFIG.sources().stream().map(File::new).collect(Collectors.toSet());
    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        Set<File> files = getSources();
        files.stream().filter(f -> registerResourcePack(String.valueOf(f.hashCode()), f, profileAdder, factory))
                .map(File::toString).forEach(LOGGER::info);
    }

    private boolean registerResourcePack(
            String id, File resources, Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        boolean answer = false;
        ResourcePackProfile container = ResourcePackProfile.of(id , false, () -> new ZipResourcePack(resources),
                factory, ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_NONE);
        if (container != null) {
            profileAdder.accept(container);
            answer = true;
        }
        return answer;
    }
}

package net.javahub.rose.resources;

import net.minecraft.client.MinecraftClient;
import net.minecraft.world.level.storage.LevelStorage.LevelSave;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.javahub.rose.config.RoseConfig.CONFIG;

public class WorldSavesProvider {

    private static final Path SAVES_DIRECTORY = MinecraftClient.getInstance().getLevelStorage().getSavesDirectory();

    private static Set<Path> getSourcesFromSaves() throws IOException {
        try (Stream<Path> stream = Files.list(SAVES_DIRECTORY)) {
            return stream.collect(Collectors.toSet());
        }
    }

    private static Set<Path> getSourcesFromRose() {
        return CONFIG.worldSaves.stream().filter(source -> source.isEnabled)
                .map(source -> source.path).map(Path::of).filter(Files::exists).collect(Collectors.toSet());
    }

    private static Set<Path> getSources() throws IOException {
        Set<Path> saves = getSourcesFromSaves();
        saves.addAll(getSourcesFromRose());
        return saves;
    }

    public static List<LevelSave> getLevelSaves() throws IOException {
        Set<Path> saves = getSources();
        return saves.stream().map(LevelSave::new)
                .filter(levelSave -> Files.isRegularFile(levelSave.getLevelDatPath())
                || Files.isRegularFile(levelSave.getLevelDatOldPath())).toList();
    }

    public static boolean isFromRose(String name) {
        Set<Path> saves = getSourcesFromRose();
        return saves.contains(Path.of(name));
    }
}

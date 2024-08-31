package com.github.kaspiandev.safeelytra.config;

import com.github.kaspiandev.safeelytra.SafeElytra;
import com.github.kaspiandev.safeelytra.config.section.AlgorithmSection;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Config {

    private final SafeElytra plugin;
    private final YamlDocument document;
    private final AlgorithmSection algorithmSection;

    public Config(SafeElytra plugin) {
        this.plugin = plugin;
        InputStream defaults = plugin.getResource("config.yml");
        if (defaults == null) {
            throw new IllegalStateException("Defaults do not exist. Jar might be corrupted!");
        }
        try {
            this.document = YamlDocument.create(
                    new File(plugin.getDataFolder(), "config.yml"),
                    defaults,
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    UpdaterSettings.builder().setAutoSave(true).setVersioning(new BasicVersioning("version")).build()
            );
            this.algorithmSection = new AlgorithmSection(plugin.getConf());
        } catch (IOException ex) {
            throw new IllegalStateException("Config could not be loaded.", ex);
        }
    }

    public AlgorithmSection getAlgorithm() {
        return algorithmSection;
    }

    public YamlDocument getDocument() {
        return document;
    }

    public SafeElytra getPlugin() {
        return plugin;
    }

}

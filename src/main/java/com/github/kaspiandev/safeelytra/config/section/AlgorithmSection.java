package com.github.kaspiandev.safeelytra.config.section;

import com.github.kaspiandev.safeelytra.config.Config;
import dev.dejvokep.boostedyaml.block.implementation.Section;

public class AlgorithmSection {

    private final Section algorithmSection;
    private final Distance distance;

    public AlgorithmSection(Config config) {
        this.algorithmSection = config.getDocument().getSection("algorithm");
        this.distance = new Distance();
    }

    public Distance getDistance() {
        return distance;
    }

    public class Distance {

        private final Section distanceSection;

        public Distance() {
            this.distanceSection = algorithmSection.getSection("distance");
        }

        public double getThreshold() {
            return distanceSection.getDouble("threshold");
        }

    }

}

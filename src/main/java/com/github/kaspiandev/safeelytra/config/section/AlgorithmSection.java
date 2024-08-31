package com.github.kaspiandev.safeelytra.config.section;

import com.github.kaspiandev.safeelytra.config.Config;
import dev.dejvokep.boostedyaml.block.implementation.Section;

public class AlgorithmSection {

    private final Section algorithmSection;
    private final Type type;

    public AlgorithmSection(Config config) {
        this.algorithmSection = config.getDocument().getSection("algorithm");
        this.type = new Type();
    }

    public Type getType() {
        return type;
    }

    public class Type {

        private final Section typeSection;
        private final Distance distance;
        private final Damage damage;

        public Type() {
            this.typeSection = algorithmSection.getSection("type");
            this.distance = new Distance();
            this.damage = new Damage();
        }

        public Distance getDistance() {
            return distance;
        }

        public Damage getDamage() {
            return damage;
        }

        public Section getTypeSection() {
            return typeSection;
        }

    }

    public class Distance {

        private final Section distanceSection;

        public Distance() {
            this.distanceSection = getType().getTypeSection().getSection("distance");
        }

        public double getThreshold() {
            return distanceSection.getDouble("threshold");
        }

    }

    public class Damage {

        private final Section damageSection;

        public Damage() {
            this.damageSection = getType().getTypeSection().getSection("damage");
        }

        public int getThreshold() {
            return damageSection.getInt("threshold");
        }

    }

}

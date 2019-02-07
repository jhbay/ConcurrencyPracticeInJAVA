package net.jcip.examples.ch3;

import java.util.*;

/**
 * Animals
 * <p/>
 * Thread confinement of local primitive and reference variables
 *
 * @author Brian Goetz and Tim Peierls
 */

// 기본변수형의 로컬변수와 객체형의 로컬변수에 대한 스택한정.
public class Ex3_9_Animals {
    Ark ark;
    Species species;
    Gender gender;

    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        
        // 로컬변수의 스택한정 상태.
        int numPairs = 0;
        Animal candidate = null;

        // animals confined to method, don't let them escape!
        // 내부객체로 묶어두고, 스택한정 상태로 유지. TreeSet 인스턴스의 참조는 animals. animals은 클래스 내부에 보관되므로, 현재 실행중인 스레드의 스택에 안전하게 한정되어 있다.
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a))
                candidate = a;
            else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }


    class Animal {
        Species species;
        Gender gender;

        public boolean isPotentialMate(Animal other) {
            return species == other.species && gender != other.gender;
        }
    }

    enum Species {
        AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
        IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
        PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
        UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
    }

    enum Gender {
        MALE, FEMALE
    }

    class AnimalPair {
        private final Animal one, two;

        public AnimalPair(Animal one, Animal two) {
            this.one = one;
            this.two = two;
        }
    }

    class SpeciesGenderComparator implements Comparator<Animal> {
        public int compare(Animal one, Animal two) {
            int speciesCompare = one.species.compareTo(two.species);
            return (speciesCompare != 0)
                    ? speciesCompare
                    : one.gender.compareTo(two.gender);
        }
    }

    class Ark {
        private final Set<AnimalPair> loadedAnimals = new HashSet<AnimalPair>();

        public void load(AnimalPair pair) {
            loadedAnimals.add(pair);
        }
    }
}


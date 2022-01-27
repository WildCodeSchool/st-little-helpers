package dev.wcs.tutoring.littlehelpers;

import com.google.common.base.Function;
import com.google.common.collect.*;
import dev.wcs.tutoring.littlehelpers.pojo.City;
import dev.wcs.tutoring.littlehelpers.pojo.PokeCity;
import dev.wcs.tutoring.littlehelpers.pojo.Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.google.common.base.Preconditions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@Component
public class GuavaDataStructuresTests {

    /****************
     * Preconditions
     */

    @Test
    public void checkPreconditions() {
        int i = 12;
        int j = 11;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s >= %s", i, j);

        String thisIsAnArg = "This is an argument.";
        String checkArg = checkNotNull(thisIsAnArg, "argument was null.");
        assertEquals(thisIsAnArg, checkArg);

        City berlin = City.builder().name("Berlin").population(4_000_000).build();
        checkState(berlin.getPopulation() > 0, "%s has no population.", berlin.getName());
    }

    /************
     * Java Map
     */

    @Test
    public void simulateGuavaMultimapWithCollectionMap() {
        Map<Type, List<String>> cities = new LinkedHashMap<>();
        putElementIntoJavaMap(cities, Type.LARGE, "Frankfurt");
        putElementIntoJavaMap(cities, Type.LARGE, "Berlin");
        putElementIntoJavaMap(cities, Type.MEDIUM, "Darmstadt");
        System.out.println(cities);
    }

    private void putElementIntoJavaMap(Map<Type, List<String>> cities, Type type, String city) {
        List<String> values = cities.get(type);
        if(values == null) {
            values = new LinkedList<>();
            values.add(city);
        }
        cities.put(type, values);
    }

    /******************
     * Guava Multimap
     */

    @Test
    public void testGuavaMultimap() {
        Multimap<Type, String> cities = ArrayListMultimap.create();
        cities.put(Type.LARGE, "Frankfurt");
        cities.put(Type.LARGE, "Berlin");
        cities.put(Type.MEDIUM, "Darmstadt");
        System.out.println(cities);
    }

    @Test
    public void testGuavaMultimapWithFactoryFunction() {
        /**
         * Multiset
         */
        List<PokeCity> items =
            Lists.newArrayList(
                PokeCity.builder().name("berlin").amountItems(5).build(),
                PokeCity.builder().name("mainz").amountItems(1).build(),
                PokeCity.builder().name("darmstadt").amountItems(4).build(),
                PokeCity.builder().name("mainz").amountItems(2).build());

        Multiset<String> multiset =
            items
                .stream()
                .collect(Multisets.toMultiset(PokeCity::getName, PokeCity::getAmountItems, HashMultiset::create));
        System.out.println(multiset);//[berlin x 5, mainz x 3, darmstadt x 4]

        City berlin = City.builder().name("Berlin").population(4_000_000).build();
        City frankfurt = City.builder().name("Frankfurt").population(700_000).build();
        City darmstadt = City.builder().name("Darmstadt").population(120_000).build();

        /**
         * Index Function
         */
        List<City> cities = Lists.newArrayList(berlin, frankfurt, darmstadt);
        Function<City, Type> classification = getCityClassificationFunction();
        Multimap<Type, City> groups = Multimaps.index(cities, classification);
        assertEquals(2, groups.size());
    }

    private Function<City, Type> getCityClassificationFunction() {
        return city -> {
            if (city.getPopulation() < 100_000) {
                return Type.SMALL;
            } else if ( (city.getPopulation() >= 100_000) && (city.getPopulation() < 500_000) ) {
                return Type.MEDIUM;
            } else {
                return Type.LARGE;
            }
        };
    }

    private Function<City, Type> getCityClassificationFunctionWithRanges() {
        RangeMap<Integer, Type> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(0, 100_000), Type.SMALL);
        rangeMap.put(Range.closed(100_000, 500_000), Type.MEDIUM);
        rangeMap.put(Range.closed(500_000, Integer.MAX_VALUE), Type.LARGE);
        return city -> rangeMap.get(city.getPopulation());
    }

    @Test
    public void testCityClassificationWithRanges() {
        City berlin = City.builder().name("Berlin").population(4_000_000).build();
        City frankfurt = City.builder().name("Frankfurt").population(700_000).build();
        City darmstadt = City.builder().name("Darmstadt").population(80_000).build();

        List<City> cities = Lists.newArrayList(berlin, frankfurt, darmstadt);

        Function<City, Type> classification = getCityClassificationFunction();

        Multimap<Type, City> groups = Multimaps.index(cities, classification);

        assertEquals(1, groups.size());
    }

    @Test
    public void testTableStructure() {
        Table<String, String, Integer> universityCourseSeatTable = HashBasedTable.create();

        universityCourseSeatTable.put("Darmstadt", "Chemical", 120);
        universityCourseSeatTable.put("Darmstadt", "IT", 60);
        universityCourseSeatTable.put("Mainz", "Electrical", 60);
        universityCourseSeatTable.put("Mainz", "IT", 120);

        universityCourseSeatTable.columnMap().get("Mainz");

        int seatCountMainz = universityCourseSeatTable.get("Mainz", "IT");
        Integer seatCountForNoEntry = universityCourseSeatTable.get("Hanau", "IT");

        Map<String, Integer> allForMainz = universityCourseSeatTable.row("Mainz");

        assertEquals(120, seatCountMainz);
        assertEquals(2, allForMainz.size());
        assertNull(seatCountForNoEntry);
    }

    @Test
    public void testBidiMap() {
        Map<String, Integer> nameToId = Maps.newHashMap();
        Map<Integer, String> idToName = Maps.newHashMap();

        nameToId.put("Bob", 42);
        idToName.put(42, "Bob");

        BiMap<String, Integer> userId = HashBiMap.create();
        String userForId = userId.inverse().get(42);
// what happens if "Bob" or 42 are already present?
// weird bugs can arise if we forget to keep these in sync...
    }

    @Test
    public void testGuavaMaps() {
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
    }

    @Test
    public void testCollectionsUtilities() {
        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
//  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}

        Set<Set<String>> animalSets = Sets.powerSet(animals);
// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}

        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}


        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(animals, string -> string.length());
    }

}

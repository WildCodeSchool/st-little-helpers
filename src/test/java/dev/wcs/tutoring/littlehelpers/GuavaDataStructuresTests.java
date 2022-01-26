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

    /************
     * Java Map
     */

    @Test
    public void simulateGuavaMultimapWithCollectionMap() {
        Map<String, List<String>> cities = new LinkedHashMap<>();
        putElementIntoJavaMap(cities, "large", "Frankfurt");
        putElementIntoJavaMap(cities, "large", "Berlin");
    }

    private void putElementIntoJavaMap(Map<String, List<String>> cities, String type, String city) {
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
        Multimap<String, String> cities = ArrayListMultimap.create();
        putElementIntoGuavaMap(cities, "large", "Frankfurt");
        putElementIntoGuavaMap(cities, "large", "Berlin");
    }

    private void putElementIntoGuavaMap(Multimap<String, String> cities, String type, String city) {
        cities.put(type, city);
    }

    @Test
    public void testGuavaMultimapWithFactoryFunction() {
        City berlin = City.builder().name("Berlin").population(4_000_000).build();
        City frankfurt = City.builder().name("Frankfurt").population(700_000).build();
        City darmstadt = City.builder().name("Darmstadt").population(80_000).build();

        /**
         *
         */
        List<PokeCity> items =
            Lists.newArrayList(
                PokeCity.builder().name("berlin").amountItems(1000).build(),
                PokeCity.builder().name("mainz").amountItems(100).build(),
                PokeCity.builder().name("darmstadt").amountItems(500).build(),
                PokeCity.builder().name("mainz").amountItems(200).build());

        Multiset<String> multiset = items
            .stream()
            .collect(Multisets.toMultiset(PokeCity::getName, PokeCity::getAmountItems, HashMultiset::create));
        System.out.println(multiset);//[city1 x 30, city2 x 10, city3 x 25]

        /**
         *
         */

        List<City> cities = Lists.newArrayList(berlin, frankfurt, darmstadt);

        Function<City, Type> classification = getCityClassificationFunction();

        Multimap<Type, City> groups = Multimaps.index(cities, classification);

        assertEquals(1, groups.size());
        //assertThat(groups.get(3), containsInAnyOrder("Tom"));
        //assertThat(groups.get(4), containsInAnyOrder("John", "Adam"));
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
    public void givenTable_whenGet_returnsSuccessfully() {
        Table<String, String, Integer> universityCourseSeatTable = HashBasedTable.create();
        universityCourseSeatTable.put("Mumbai", "Chemical", 120);
        universityCourseSeatTable.put("Mumbai", "IT", 60);
        universityCourseSeatTable.put("Harvard", "Electrical", 60);
        universityCourseSeatTable.put("Harvard", "IT", 120);

        universityCourseSeatTable.columnMap().get("Mumbai");

        int seatCount
                = universityCourseSeatTable.get("Mumbai", "IT");
        Integer seatCountForNoEntry
                = universityCourseSeatTable.get("Oxford", "IT");

        assertEquals(60, seatCount);
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

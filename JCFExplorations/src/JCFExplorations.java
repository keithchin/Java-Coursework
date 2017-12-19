import components.naturalnumber.NaturalNumber;

/**
 * Simple class to experiment with the Java Collections Framework and how it
 * compares with the OSU CSE collection components.
 *
 * @author Put your name here
 *
 */
public final class JCFExplorations {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private JCFExplorations() {
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    public static void giveRaise(components.map.Map<String, Integer> map,
            char initial, int raisePercent) {
        assert map != null : "Violation of: map is not null";
        assert raisePercent > 0 : "Violation of: raisePercent > 0";

        components.map.Map<String, Integer> temp = map.newInstance();
        while (map.size() > 0) {
            components.map.Map.Pair<String, Integer> a = map.removeAny();
            if (a.key().charAt(0) == initial) {
                temp.add(a.key(), a.value() * (100 + raisePercent) / 100);
            } else {
                temp.add(a.key(), a.value());
            }
        }
        map.transferFrom(temp);

    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    public static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {
        assert map != null : "Violation of: map is not null";
        assert raisePercent > 0 : "Violation of: raisePercent > 0";

        // TODO - fill in body
        java.util.Set<java.util.Map.Entry<String, Integer>> temp = map
                .entrySet();
        for (java.util.Map.Entry<String, Integer> pair : temp) {
            if (pair.getKey().charAt(0) == initial) {
                pair.setValue(pair.getValue() * (100 + raisePercent) / 100);
            }
        }

    }

    /**
     * Increments by 1 every element in the given {@code Set}.
     *
     * @param set
     *            the set whose elements to increment
     * @updates set
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [set is the set of integers that are elements of #set incremented by 1]
     * </pre>
     */
    public static void incrementAll(components.set.Set<NaturalNumber> set) {
        assert set != null : "Violation of: set is not null";

        // TODO - fill in body
        components.set.Set<NaturalNumber> temp = set.newInstance();
        NaturalNumber one = new components.naturalnumber.NaturalNumber1L(1);
        while (set.size() > 0) {
            NaturalNumber tempN = set.removeAny();
            tempN.add(one);
            temp.add(tempN);
        }
        set.transferFrom(temp);
    }

    /**
     * Increments by 1 every element in the given {@code Set}.
     *
     * @param set
     *            the set whose elements to increment
     * @updates set
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [set is the set of integers that are elements of #set incremented by 1]
     * </pre>
     */
    public static void incrementAll(java.util.Set<NaturalNumber> set) {
        assert set != null : "Violation of: set is not null";

        // TODO - fill in body
        java.util.Iterator<NaturalNumber> setOfNumbers = set.iterator();
        java.util.Set<NaturalNumber> tempSet = new java.util.HashSet<NaturalNumber>();
        while (setOfNumbers.hasNext()) {
            NaturalNumber number = setOfNumbers.next();
            setOfNumbers.remove();
            number.increment();
            tempSet.add(number);
        }

        set.addAll(tempSet);
    }

}

package ru.labsystech.testproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.labsystech.testproblem.tools.Deserializer;
import ru.labsystech.testproblem.tools.Serializer;
import ru.labsystech.testproblem.tools.impl.TwoInOneNoSpaceDeserializer;
import ru.labsystech.testproblem.tools.impl.TwoInOneNoSpaceSerializer;

/**
 * @author tuspring
 */
public class Actions {

    Serializer serializer = new TwoInOneNoSpaceSerializer();
    Deserializer deserializer = new TwoInOneNoSpaceDeserializer();

    public static void main(String... cmd) {
        Actions actions = new Actions();

        actions.commonTest(1, 300, 50);
        actions.commonTest(1, 300, 100);
        actions.commonTest(1, 300, 500);
        actions.commonTest(1, 300, 1000);

        actions.commonTest(1, 9, 1000);
        actions.commonTest(1, 99, 1000);
        actions.commonTest(1, 300, 1000);
        List<Integer> customCollection = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 300; j++) {
                customCollection.add(j);
            }
        }
        actions.commonTest(customCollection, 1, 300, 900);
        if (cmd.length > 2) {
            System.out.println("Test with min %s max %s length %s: ".formatted(cmd[0], cmd[1], cmd[2])
                                   + ((actions.detailedTest(createRandomCollection(
                Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])))) ? "success"
                : "failed"));
        }
    }

    private void commonTest(int minValue, int maxValue, int length) {
        commonTest(createRandomCollection(minValue, maxValue, length), minValue, maxValue, length);
    }

    private void commonTest(List<Integer> rawCollection, int minValue, int maxValue, int length) {
        int rawCollectionLength = integerCollectionLength(rawCollection);

        System.out.println("Collection of integers length: " + rawCollectionLength);
        String serializedCollection = serializer.serialize(rawCollection);

        System.out.println("Serialized collection length: " + serializedCollection.length());
        System.out.println("Compressed by " + (serializedCollection.length() * 100 / rawCollectionLength) + "%.");
        List<Integer> deserializedString = deserializer.deserialize(serializedCollection);

        System.out.println("Deserialized string length: " + integerCollectionLength(deserializedString));

        System.out.println("Test with min %s max %s length %s: ".formatted(minValue, maxValue, length)
                               + (rawCollection.equals(deserializedString) ? "success" : "failed"));
    }

    private boolean detailedTest(List<Integer> rawCollection) {
        int rawCollectionLength = integerCollectionLength(rawCollection);
        System.out.println("Collection of integers: " + rawCollection + " with length " + rawCollectionLength);
        String serializedCollection = serializer.serialize(rawCollection);
        System.out.println(
            "Serialized collection: " + serializedCollection + " with length " + serializedCollection.length());
        System.out.println("Compressed " + (serializedCollection.length() * 100 / rawCollectionLength) + "%.");
        List<Integer> deserializedString = deserializer.deserialize(serializedCollection);
        System.out.println("Deserialized string: " + deserializedString + " with length " + integerCollectionLength(
            deserializedString));
        return rawCollection.equals(deserializedString);
    }

    private static int integerCollectionLength(List<Integer> testCollection) {
        int rawCollectionLength = 0;
        for (Integer eachNumber : testCollection) {
            rawCollectionLength += Math.log10(eachNumber) + 2;
        }
        return --rawCollectionLength;
    }

    private static List<Integer> createRandomCollection(int minValue, int maxValue, int length) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(maxValue - minValue) + minValue);
        }
        return result;
    }
}

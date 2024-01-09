package ru.labsystech.testproblem.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.labsystech.testproblem.tools.impl.EazyDeserializer;
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

        System.out.println("Test with min %s max %s length %s: ".formatted(1, 300, 50)
                               + ((actions.commonTest(createRandomCollection(1, 300, 50))) ? "success" : "failed"));
        System.out.println("Test with min %s max %s length %s: ".formatted(1, 300, 100)
                               + ((actions.commonTest(createRandomCollection(1, 300, 100))) ? "success" : "failed"));
        System.out.println("Test with min %s max %s length %s: ".formatted(1, 300, 500)
                               + ((actions.commonTest(createRandomCollection(1, 300, 500))) ? "success" : "failed"));
        System.out.println("Test with min %s max %s length %s: ".formatted(1, 300, 1000)
                               + ((actions.commonTest(createRandomCollection(1, 300, 1000))) ? "success" : "failed"));

        System.out.println("Test with min %s max %s length %s: ".formatted(1, 9, 1000)
                               + ((actions.commonTest(createRandomCollection(1, 9, 1000))) ? "success" : "failed"));
        System.out.println("Test with min %s max %s length %s: ".formatted(1, 99, 1000)
                               + ((actions.commonTest(createRandomCollection(1, 99, 1000))) ? "success" : "failed"));
        System.out.println("Test with min %s max %s length %s: ".formatted(1, 300, 1000)
                               + ((actions.commonTest(createRandomCollection(1, 300, 1000))) ? "success" : "failed"));

        List<Integer> customCollection = new ArrayList<>();
        for (int i = 1; i <= 3 ; i++) {
            for (int j = 1; j <= 300; j++) {
                customCollection.add(j);
            }
        }
        System.out.println("Test with min %s max %s length %s with triple number repeat: ".formatted(1, 300, 900)
                               + ((actions.commonTest(customCollection)) ? "success" : "failed"));
        if(cmd.length>2) {
            System.out.println("Test with min %s max %s length %s: ".formatted(cmd[0], cmd[1], cmd[2])
                                   + ((actions.detailedTest(createRandomCollection(
                Integer.parseInt(cmd[0]), Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])))) ? "success"
                : "failed"));
        }
    }

    private boolean commonTest(List<Integer> rawCollection){
        int rawCollectionLength = integerCollectionLength(rawCollection);
        System.out.println("Collection of integers length: " + rawCollectionLength);
        String serializedCollection = serializer.serialize(rawCollection);
        System.out.println("Serialized collection length: " + serializedCollection.length());
        System.out.println("Compressed by " + (serializedCollection.length()*100 / rawCollectionLength) + "%.");
        List<Integer> deserializedString = deserializer.deserialize(serializedCollection);
        System.out.println("Deserialized string length: " + integerCollectionLength(deserializedString));
        return rawCollection.equals(deserializedString);
    }

    private boolean detailedTest(List<Integer> rawCollection){
        int rawCollectionLength = integerCollectionLength(rawCollection);
        System.out.println("Collection of integers: " + rawCollection + " with length " + rawCollectionLength);
        String serializedCollection = serializer.serialize(rawCollection);
        System.out.println("Serialized collection: " + serializedCollection + " with length " + serializedCollection.length());
        System.out.println("Compressed " + (serializedCollection.length()*100 / rawCollectionLength) + "%.");
        List<Integer> deserializedString = deserializer.deserialize(serializedCollection);
        System.out.println("Deserialized string: " + deserializedString + " with length " + integerCollectionLength(deserializedString));
        return rawCollection.equals(deserializedString);
    }

    private static int integerCollectionLength(List<Integer> testCollection) {
        int rawCollectionLength = 0;
        for (Integer eachNumber : testCollection){
            rawCollectionLength += Math.log10(eachNumber) + 2;
        }
        return --rawCollectionLength;
    }

    private static List<Integer> createRandomCollection(int minValue, int maxValue, int length){
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++){
            result.add(random.nextInt(maxValue - minValue) + minValue);
        }
        return result;
    }
}

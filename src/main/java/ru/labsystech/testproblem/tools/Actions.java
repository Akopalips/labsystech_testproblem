package ru.labsystech.testproblem.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.labsystech.testproblem.tools.impl.EazyDeserializer;
import ru.labsystech.testproblem.tools.impl.EazySerializer;

/**
 * @author tuspring
 */
public class Actions {
    public static void main(String... shit) {
        Actions actions= new Actions();
        Serializer serializer = new EazySerializer();
        Deserializer deserializer = new EazyDeserializer();

        int maxValue = 300;
        int length = 100;
        List<Integer> rawCollection = actions.createRandomCollection(maxValue, length);
//        List<Integer> rawCollection = new ArrayList<>();
//        rawCollection.add(149);
        int rawCollectionLength = integerCollectionLength(rawCollection);
        System.out.println("Collection of integers: " + rawCollection + " with length " + rawCollectionLength);
        String serializedCollection = serializer.serialize(rawCollection);
        System.out.println("Serialized collection: " + serializedCollection + " with length " + serializedCollection.length());
        System.out.println("Difference is " + (serializedCollection.length()*100 / rawCollectionLength) );

        List<Integer> deserializedString = deserializer.deserialize(serializedCollection);
        System.out.println("Deserialized string: " + deserializedString + " with length " + integerCollectionLength(deserializedString));
        System.out.println(rawCollection.equals(deserializedString));

//        actions.serializer.serialize()
    }

    private static int integerCollectionLength(List<Integer> testCollection) {
        int rawCollectionLength = 0;
        for (Integer eachNumber : testCollection){
            rawCollectionLength += Math.log10(eachNumber) + 2;
        }
        return --rawCollectionLength;
    }

    private List<Integer> createRandomCollection(int maxValue, int length){
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++){
            result.add(random.nextInt(maxValue));
        }
        return result;
    }
}
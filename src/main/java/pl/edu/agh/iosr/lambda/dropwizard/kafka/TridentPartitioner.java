package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

import java.util.Random;

/**
 * Created by Puszek_SE on 2015-05-29.
 */
public class TridentPartitioner implements Partitioner {

    Random rand = new Random();

    public TridentPartitioner(VerifiableProperties props) {}

public int partition(Object key, int partitions_count) {
        return rand.nextInt(partitions_count);
    }
}


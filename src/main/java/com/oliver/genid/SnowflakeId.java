package com.oliver.genid;

/**
 * com.oliver.genid.SnowflakeId
 *
 * @author oliver
 * @date 2019/12/24 10:37
 */
public class SnowflakeId {
    private final long epoch = 1491004800000L;

    private final long workerIdBit = 5;

    /**
     * source code : 1....0000 0001
     * reverse code: 0....1111 1110
     * complement  : 0....1111 1111
     * shift       : 0....1111 0000
     * negate      : 0....0000 1111
     */
    private final long maxWorkerId = ~(-1 << workerIdBit);

    private final long dataCenterIdBit = 5;

    private final long maxDataCenterId = ~(-1 << dataCenterIdBit);

    private final long sequenceBits = 12L;

    private final long workerIdShift = sequenceBits;

    private final long dataCenterIdShift = sequenceBits + workerIdBit;

    private final long timestampShift = sequenceBits + workerIdBit + dataCenterIdBit;

    private final long sequenceMask = ~(-1 << sequenceBits);


    private long dataCenterId;
    private long workerId;
    private long lastTimestamp = 1L;

    private long sequence = 0L;

    public SnowflakeId() {
        this(0, 0);
    }

    public SnowflakeId(long dataCenterId, long workerId) {
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = genTimestamp();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock mover backwards," +
                            "Refusing to generate id for %d milliseconds"
                    , lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = nextTimestamp(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return (timestamp - epoch) << timestampShift
                | dataCenterId << dataCenterIdShift
                | workerId << workerIdBit
                | sequence;
    }

    private long genTimestamp() {
        return System.currentTimeMillis();
    }

    private long nextTimestamp(long lastTimestamp) {
        long timestamp = genTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = genTimestamp();
        }
        return timestamp;
    }


}

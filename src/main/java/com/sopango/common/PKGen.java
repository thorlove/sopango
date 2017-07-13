package com.sopango.common;

public class PKGen {
	private final static long twepoch = 1288834974657L;
	private static long sequence = 0L;
	private final static long workerIdBits = 4L;
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;

	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	public final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private static long lastTimestamp = -1L;

	public synchronized static long nextId() {
		long workerId = new Double(Math.ceil(Math.random()*maxWorkerId)).longValue();
		
		long timestamp = timeGen();
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception(
						String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift))
				| (workerId << workerIdShift) | (sequence);
		return nextId;
	}

	private static long tilNextMillis(final long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private static long timeGen() {
		return System.currentTimeMillis();
	}
}

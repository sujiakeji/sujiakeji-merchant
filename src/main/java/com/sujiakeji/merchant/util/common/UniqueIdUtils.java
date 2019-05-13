package com.sujiakeji.merchant.util.common;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class UniqueIdUtils {

    //   timestamp |datacenter | sequence
    //   41        |10         |  12

    private long datacenterId;

    private final long datacenterIdBitSize = 10L;
    private final long sequenceBitSize = 12;

    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBitSize);

    private final long datacenterIdShift = sequenceBitSize;
    private final long timestampLeftShift = sequenceBitSize + datacenterIdBitSize;

    private final long epoch = 1288834974657L;
    private final long sequenceMax = 4096;
    private volatile long sequence = 0L;
    private volatile long lastTimestamp = -1L;

    protected long getDatacenterId() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
        long id;
        if (networkInterface == null) {
            id = 1;
        } else {
            byte[] mac = networkInterface.getHardwareAddress();
            id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
        }
        return id;
    }

    public synchronized Long generateId() {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - epoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | sequence;
        return id;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public String convertToString(UUID id) {
        String str = null;
        if (id != null) {
            str = id.toString();
        }
        return str;
    }

    public UUID convertToUUID(String str) {
        UUID id = null;
        if (str != null) {
            id = UUID.fromString(str);
        }
        return id;
    }

    public List<String> convertToStringList(List<UUID> idList) {
        List<String> strList = null;
        if (idList != null) {
            strList = new ArrayList<String>();
            Iterator<UUID> iterator = idList.iterator();
            while (iterator.hasNext()) {
                String str = convertToString(iterator.next());
                if (str != null) {
                    strList.add(str);
                }
            }
        }
        return strList;
    }

    public List<UUID> convertToUUIDList(List<String> strList) {
        List<UUID> idList = null;
        if (strList != null) {
            idList = new ArrayList<UUID>();
            Iterator<String> iterator = strList.iterator();
            while (iterator.hasNext()) {
                UUID id = convertToUUID(iterator.next());
                if (id != null) {
                    idList.add(id);
                }
            }
        }
        return idList;
    }

    @PostConstruct
    private void init() throws Exception {
        datacenterId = getDatacenterId();
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new Exception("datacenterId > maxDatacenterId");
        }
    }

}

package com.jinshuo.core.idgen;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.jinshuo.core.idgen.time.AbstractClock;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Classname CommonSelfIdGenerator
 * @Description TODO
 * @Date 2019/7/10 14:18
 * @Created by dyh

 * 自生成Id生成器.
 *
 * <p>
 * 长度为64bit,从高位到低位依次为
 * </p>
 *
 * <pre>
 * 1bit   符号位
 * 41bits 时间偏移量从2016年11月1日零点到现在的毫秒数
 * 10bits 工作进程Id
 * 12bits 同一个毫秒内的自增量
 * </pre>
 *
 * <p>
 * 工作进程Id获取优先级: 系统变量{@code sjdbc.self.id.generator.worker.id} 大于 环境变量{@code SJDBC_SELF_ID_GENERATOR_WORKER_ID}
 * ,另外可以调用@{@code CommonSelfIdGenerator.setWorkerId}进行设置
 * </p>
 */
@Getter
@Slf4j
public class CommonSelfIdGenerator  {

    public static final long SJDBC_EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    @Setter
    private static AbstractClock clock = AbstractClock.systemClock();

    @Getter
    private static long workerId;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.OCTOBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SJDBC_EPOCH = calendar.getTimeInMillis();
        initWorkerId();
    }

    private static long sequence;

    private static long lastTime;

    static void initWorkerId() {
        String workerId = System.getProperty("sjdbc.self.id.generator.worker.id");
        if (!Strings.isNullOrEmpty(workerId)) {
            setWorkerId(Long.valueOf(workerId));
            return;
        }
        workerId = System.getenv("SJDBC_SELF_ID_GENERATOR_WORKER_ID");
        if (Strings.isNullOrEmpty(workerId)) {
            return;
        }
        setWorkerId(Long.valueOf(workerId));
    }

    /**
     * 设置工作进程Id.
     *
     * @param workerId 工作进程Id
     */
    public static void setWorkerId(final Long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
        CommonSelfIdGenerator.workerId = workerId;
    }

    /**
     * 生成Id.
     *
     * @return 返回@{@link Long}类型的Id
     */
    public synchronized static Long generateId() {
        long time = clock.millis();
        Preconditions.checkState(lastTime <= time, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, time);
        if (lastTime == time) {
            if (0L == (sequence = ++sequence & SEQUENCE_MASK)) {
                time = waitUntilNextTime(time);
            }
        } else {
            sequence = 0;
        }
        lastTime = time;
        if (log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTime)), workerId, sequence);
        }
        return ((time - SJDBC_EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private static long waitUntilNextTime(final long lastTime) {
        long time = clock.millis();
        while (time <= lastTime) {
            time = clock.millis();
        }
        return time;
    }
}


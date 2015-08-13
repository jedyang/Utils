import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 一种基本的对象锁的写法
 * 用hashmap存储关联对象的lock
 * @author  yunsheng
 */
public final class DeviceLocker
{

    private static Map<String, Lock> devLocker = new HashMap<String, Lock>();

    /**
     * <默认构造函数>
     *
     */
    private DeviceLocker()
    {
        
    }
    
    /** 
     * 对一台设备上锁，使其不能再被其他线程用来下发命令
     * @param deviceId deviceId
     */
    public static void lockDevice(final String deviceId)
    {
        Lock lock;
        lock = devLocker.get(deviceId);
        if (null == lock)
        {
            lock = getLock(deviceId);
        }

        LOGGER.info("Try to get lock for device {}", deviceId);
        lock.lock();
        LOGGER.info("Lock device {}", deviceId);
    }

    private static synchronized Lock getLock(final String deviceId)
    {
        Lock lock = devLocker.get(deviceId);
        if (null == lock)
        {
            lock = new ReentrantLock();
            devLocker.put(deviceId, lock);
        }
        return lock;
    }

    /** 
     * 对一台设备解锁，使其能继续被其他线程用来下发命令
     * @param deviceId deviceId
     */
    public static void unLockDevice(final String deviceId)
    {
        Lock lock = devLocker.get(deviceId);
        if (null != lock)
        {
            LOGGER.info("Unlock device {}", deviceId);
            lock.unlock();
            LOGGER.info("Unlock success");
        }
    }

}

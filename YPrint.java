
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * 对集合进行漂亮一点的打印
 *
 * @author  yunsheng
 */
public class YPrint
{
    public static void yPrint(Collection<?> c)
    {
        System.out.println(yFormat(c));
    }

    public static void yPrint(Object[] c)
    {
        System.out.println(Arrays.asList(c));
    }

    public static String yFormat(Collection<?> c)
    {
        if (null == c || c.size() == 0)
        {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : c)
        {
            if (c.size() != 1)
            {
                sb.append("\n ");
            }
            sb.append(obj);
        }

        if (c.size() != 1)
        {
            sb.append("\n ");
        }
        sb.append("]");

        return sb.toString();
    }
}

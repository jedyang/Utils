import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Directory
{
    /**
     *
     * 用于显示当前目录
     * @param dir
     * @param regex
     * @return
     * @author yunsheng
     */
    public static File[] local(File dir, final String regex)
    {
        return dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File paramFile, String paramString)
            {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(paramString);
                return m.matches();
            }
        });
    }

    // 重载方法
    public static File[] local(String dir, final String regex)
    {
        return local(new File(dir), regex);
    }

    // 静态内部类
    public static class TreeInfo implements Iterable<File>
    {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        @Override
        public Iterator<File> iterator()
        {
            // 默认files的迭代器
            return files.iterator();
        }

        void addAll(TreeInfo info)
        {
            files.addAll(info.files);
            dirs.addAll(info.dirs);
        }

        @Override
        public String toString()
        {
            return "dirs:" + dirs.toString() + "\n files:" + files.toString();
        }
    }

    // 递归显示所有文件
    static TreeInfo recurseDirs(File startDir, String regex)
    {
        TreeInfo result = new TreeInfo();
        for (File f : startDir.listFiles())
        {
            if (f.isDirectory())
            {
                result.dirs.add(f);
                result.addAll(recurseDirs(f, regex));
            }
            else if (f.getName().matches(regex))
            {
                result.files.add(f);
            }
        }

        return result;
    }

    // 四个重载方法
    /**
     *
     * 递归查询出所有目录和文件
     * @author yunsheng
     */
    public static TreeInfo walk(String startFile)
    {
        return recurseDirs(new File(startFile), ".*");
    }

    public static TreeInfo walk(File startFile)
    {
        return recurseDirs(startFile, ".*");
    }

    public static TreeInfo walk(String startFile, String regex)
    {
        return recurseDirs(new File(startFile), regex);
    }

    public static TreeInfo walk(File startFile, String regex)
    {
        return recurseDirs(startFile, ".*");
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println(walk("."));
        }
        else
        {
            for (String s : args)
            {
                System.out.println(walk(s));
            }
        }
    }
}

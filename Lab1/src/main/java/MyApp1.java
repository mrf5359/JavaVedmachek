import java.io.File;

public class MyApp1 {
    public static void main(String[] args) {
        File currentDirectory = new File(".");
        printDirectoryTree(currentDirectory, 0);
    }

    private static void printDirectoryTree(File directory, int depth) {
        if (directory.isDirectory()) {
            System.out.println(getIndentation(depth) + directory.getName());
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    printDirectoryTree(file, depth + 1);
                }
            }
        } else {
            System.out.println(getIndentation(depth) + directory.getName());
        }
    }

    private static String getIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("\t");
        }
        return indentation.toString();
    }
}

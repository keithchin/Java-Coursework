import components.binarytree.BinaryTree;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Put your name here
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        int tHeight = 0;
        int leftH = 0;
        int rightH = 0;
        T root;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        if (t.size() == 0) {
            return 0;
        }

        if (t.size() > 0) {
            root = t.disassemble(left, right);
            leftH = height(left);
            rightH = height(right);
            t.assemble(root, left, right);
            tHeight++;
            if (leftH > rightH) {
                tHeight += leftH;
            } else {
                tHeight += rightH;
            }

        }
        return tHeight;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size2(BinaryTree<T> t) {
        int sizeBinT = 0;
        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            sizeBinT = 1 + size(left) + size(right);
            t.assemble(root, left, right);
        }
        return sizeBinT;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {
        int sizeBinT = 0;
        if (t.height() > 0) {
            for (T element : t) {
                sizeBinT++;
            }
        }
        return sizeBinT;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        T root;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        boolean found = false;

        if (t.size() > 0) {
            root = t.disassemble(left, right);
            if (root.equals(x)) {
                found = true;
            }
            if (!found) {
                found = isInTree(left, x);
            }
            if (!found) {
                found = isInTree(right, x);
            }

            t.assemble(root, left, right);
        }
        return found;
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        String sl = "";
        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            sl = root.toString() + "(" + treeToString(left)
                    + treeToString(right) + ")";
            t.assemble(root, left, right);
        } else {
            sl = "()";
        }
        return sl;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> copy = t.newInstance();
        if (t.height() > 0) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            Integer root = t.disassemble(left, right);
            copy.assemble(root, copy(left), copy(right));
            t.assemble(root, left, right);
        }
        return copy;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTree(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}

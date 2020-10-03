//Code by-  Jai Gaur
import java.io.*;
import java.util.*;

public class printnodeskaway {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack < Pair > st = new Stack < > ();
        st.push(rtp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    top.node.left = new Node(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }

                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }

                top.state++;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }
    public static ArrayList<Node> ntrp(Node node, int data){
        if(node == null){
            return new ArrayList<>();
        }
        else if(node.data == data){
            ArrayList<Node> baseAns = new ArrayList<>();
            baseAns.add(node);
            return baseAns;
        }
        ArrayList<Node> lans = ntrp(node.left, data);
        if(lans.size() > 0){
            lans.add(node);
            return lans;
        }
       ArrayList<Node> rans = ntrp(node.right, data);
        if(rans.size() > 0){
            rans.add(node);
            
        }
        return rans;
    }
    public static void printXLevelDown(Node node, int x, Node blockage){
        if(node == null){
            return;
        }
        else if(x == 0){
            System.out.println(node.data);
            return;
        }
        if(node.left != blockage){
            printXLevelDown(node.left, x - 1, blockage  );
        }
        if(node.right != blockage){
            printXLevelDown(node.right, x - 1, blockage);
        }
    }
    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = ntrp(node, data);
        
        for(int i = 0; i < path.size() && i <= k; i++){
            Node blockage = null;
            if(i > 0){
                blockage = path.get(i - 1);
            }
            printXLevelDown(path.get(i), k - i, blockage);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            if (values[i].equals("n") == false) {
                arr[i] = Integer.parseInt(values[i]);
            } else {
                arr[i] = null;
            }
        }

        int data = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        Node root = construct(arr);
        printKNodesFar(root, data, k);
    }

}
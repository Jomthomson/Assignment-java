package Labsetup;

import java.util.Arrays;

public class MinimumConnections {
    public int findParent(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return findParent(parent, parent[i]);
    }

    public void union(int[] parent, int[] rank, int x, int y) {
        int xroot = findParent(parent, x);
        int yroot = findParent(parent, y);

        if (rank[xroot] < rank[yroot]) {
            parent[xroot] = yroot;
        } else if (rank[xroot] > rank[yroot]) {
            parent[yroot] = xroot;
        } else {
            parent[yroot] = xroot;
            rank[xroot] += 1;
        }
    }

    public int minimumConnections(int n, int[][] connections) {
        if (n <= 0 || connections == null || connections.length == 0) {
            return -1;
        }

        int[][] edges = new int[connections.length][2];
        for (int i = 0; i < connections.length; i++) {
            edges[i][0] = connections[i][0];
            edges[i][1] = connections[i][1];
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(a[0], b[0]));
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int count = 0;

        for (int[] e : edges) {
            int x = findParent(parent, e[0]);
            int y = findParent(parent, e[1]);
            if (x != y) {
                union(parent, rank, x, y);
                count += 1;
            }
        }

        if (count < n - 1) {
            return -1;
        }

        return count - (n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] connections = {{0, 1}, {1, 2}, {3, 4}};
        MinimumConnections mc = new MinimumConnections();
        System.out.println(mc.minimumConnections(n, connections));
    }
}

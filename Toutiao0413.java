package ls;

import java.util.*;

public class Toutiao0413 {
	static int m = 0, n = 0;
	
    public static void main(String[] args) {		
		List<List<Integer>> table = new ArrayList<>();		
        Scanner in = new Scanner(System.in);        
        while (in.hasNextLine()) {
        	String[] str = in.nextLine().split(" ");
        	if (str[0].equals("c"))
        		break;
        	List<Integer> line = new ArrayList<>();
        	for (String s : str) 
        		line.add(Integer.parseInt(s));
        	table.add(line);        	
        }
        in.close();
        m = table.size();
        if (m == 0) {
        	System.out.println(-1);
        	return;
        }
        n = table.get(0).size();
        if (n == 0) {
        	System.out.println(-1);
        	return;
        }
        System.out.println(bfs(table) - 1);
    }
    
    static int bfs(List<List<Integer>> table) {
    	Queue<Integer> qx = new LinkedList<Integer>(),
    		qy = new LinkedList<Integer>();
    	int[][] t = new int[m][n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			t[i][j] = table.get(i).get(j);
    			if (t[i][j] == 2) {
    				qx.add(i); qy.add(j);
    			}
    		}
    	}
    	int time = 0;
    	int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    	Queue<Integer> newqx = new LinkedList<Integer>(),
        		newqy = new LinkedList<Integer>();
    	while (!qx.isEmpty()) {
        	int x = qx.poll(), y = qy.poll();
            for (int i = 0; i < 4; i++) {
            	if (((x + dir[i][0]) >= 0) &&
            		((x + dir[i][0]) < m) &&
            		((y + dir[i][1]) >= 0) &&
            		((y + dir[i][1]) < n))
            	{
            		int newx = x + dir[i][0], newy = y + dir[i][1];
            		if (t[newx][newy] == 1) {
            			t[newx][newy] = 2;
	            		newqx.add(newx);
	            		newqy.add(newy);
            		}
            	}
            }
            if (qx.isEmpty()) {
            	time++;
            	qx = newqx; 
            	qy = newqy;
            	newqx = new LinkedList<Integer>();
                newqy = new LinkedList<Integer>();   
            }
    	}
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (t[i][j] == 1) {
    				return -1;
    			}
    		}
    	}
    	return time;
    }
}

//////////

package ls;

import java.util.*;

public class Toutiao0413 {
	static int m = 0, n = 0;
	
    public static void main(String[] args) {		
		List<List<Integer>> table = new ArrayList<>();		
        Scanner in = new Scanner(System.in);        
        while (in.hasNextLine()) {
        	String[] str = in.nextLine().split(" ");
        	System.out.println(str.length);
        	if (str[0].equals("c"))
        		break;
        	List<Integer> line = new ArrayList<>();
        	for (String s : str) 
        		line.add(Integer.parseInt(s));
        	table.add(line);        	
        }
        in.close();
        m = table.size();
        if (m == 0) {
        	System.out.println(-1);
        	return;
        }
        n = table.get(0).size();
        if (n == 0) {
        	System.out.println(-1);
        	return;
        }
        System.out.println(m + " " + n + ":");
        System.out.println(bfs(table));
    }
    
    static int bfs(List<List<Integer>> table) {
    	Queue<Integer> qx = new LinkedList<Integer>(),
    		qy = new LinkedList<Integer>();
    	int[][] t = new int[m][n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			t[i][j] = table.get(i).get(j);
    			if (t[i][j] == 2) {
    				qx.add(i); qy.add(j);
    			}
    		}
    	}
    	
    	System.out.println(qx);
    	int time = 0;
    	int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    	Queue<Integer> newqx = new LinkedList<Integer>(),
        		newqy = new LinkedList<Integer>();
    	while (!qx.isEmpty()) {
        	int x = qx.poll(), y = qy.poll();
        	System.out.println("x y" + x + " " + y);
            for (int i = 0; i < 4; i++) {
            	if (((x + dir[i][0]) >= 0) &&
            		((x + dir[i][0]) < m) &&
            		((y + dir[i][1]) >= 0) &&
            		((y + dir[i][1]) < n))
            	{
            		int newx = x + dir[i][0], newy = y + dir[i][1];
            		System.out.println(newx + " " + newy);
            		if (t[newx][newy] == 1) {
                		System.out.println("ok" + newx + " " + newy);
            			t[newx][newy] = 2;
	            		newqx.add(newx);
	            		newqy.add(newy);
            		}
            	}
            }
            if (qx.isEmpty()) {
            	time++;
            	qx = newqx; 
            	qy = newqy;
            	newqx = new LinkedList<Integer>();
                newqy = new LinkedList<Integer>();   
                System.out.println();
            }
//            System.out.println(qx);
//            System.out.println(qy);
//            System.out.println();
    	}
    	System.out.println();
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			System.out.print(t[i][j] + " ");
    		}
    		System.out.println();
    	}
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (t[i][j] == 1) {
    				return -1;
    			}
    		}
    	}
    	return time;
    }
}

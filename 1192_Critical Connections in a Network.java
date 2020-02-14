//Amazon OA Edition
class Solution {
    int cnt = 0;
    LinkedList<PairInt> res = new LinkedList<>();
    
    private class PairInt{
        int first;
        int second;
        PairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    private class Graph{
        private ArrayList<List<Integer>> adj; //graph adjacency list
        Graph(int v) {  
            this.adj = new ArrayList<List<Integer>>(); 
            for (int i = 0; i <= v; i++) {
                this.adj.add(new LinkedList<Integer>());
			}
        }       
        void addEdge(int v, int w) 
        { 
            this.adj.get(v).add(w);
            this.adj.get(w).add(v); //undirected graph 
        }
    } 
    
    private void dfs(boolean visited[], int disc[], int low[], int parent[], int u, Graph g){
        visited[u] = true;
		cnt++;
        disc[u] = cnt;
		low[u] = cnt;
        
        Iterator<Integer> index = (g.adj.get(u)).iterator();
        while(index.hasNext()) {
            int v = index.next();
            if (!visited[v]) {
                parent[v] = u;
                dfs(visited, disc, low, parent, v, g);
                low[u] = Math.min(low[u], low[v]);
                if(low[v] > disc[u]) {
					if (u < v) {
						res.add(new PairInt(u, v));
					} else {
						res.add(new PairInt(v, u));						
					}
				}
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	List<PairInt> criticalConnections(int numOfServers, int numOfConnections,
									  List<PairInt> connections)
    {
        Graph g = new Graph(numOfServers);
        for(int i = 0; i < numOfConnections; i++) {
            g.addEdge(connections.get(i).first, connections.get(i).second);
        }
        boolean visited[] = new boolean[numOfServers + 1]; // default value is false
        int disc[] = new int[numOfServers + 1];
        int low[] = new int[numOfServers + 1];
        int parent[] = new int[numOfServers + 1];      
        for(int i = 0; i < numOfServers; i++) {
            parent[i] = -1;
        }       
		// dfs in every nodes
        for(int i = 0; i < numOfServers; i++) {
            if (!visited[i]) {
                dfs(visited, disc, low, parent, i, g);
            }
        }
         
        return res;
    }
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<PairInt> connectionsPair = new ArrayList<>();
        for (List<Integer> ll : connections) {
            connectionsPair.add(new PairInt(ll.get(0), ll.get(1)));
        }
        List<PairInt> ansPair = criticalConnections(n, connections.size(), connectionsPair);
        List<List<Integer>> res = new ArrayList<>();
        for (PairInt p : ansPair) {
            res.add(new ArrayList<Integer>(Arrays.asList(p.first, p.second)));
        }        
        return res;
    }
}
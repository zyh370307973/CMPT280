

import lib280.graph.Edge280;
import lib280.graph.GraphAdjListRep280;
import lib280.graph.Vertex280;


public class UnionFind280 {
	GraphAdjListRep280<Vertex280, Edge280<Vertex280>> G;
	
	/**
	 * Create a new union-find structure.
	 * 
	 * @param numElements Number of elements (numbered 1 through numElements, inclusive) in the set.
	 * @postcond The structure is initialized such that each element is in its own subset.
	 */
	public UnionFind280(int numElements) {
		G = new GraphAdjListRep280<Vertex280, Edge280<Vertex280>>(numElements, true);
		G.ensureVertices(numElements);		
	}
	
	/**
	 * Return the representative element (equivalence class) of a given element.
	 * @param id The elements whose equivalence class we wish to find.
	 * @return The representative element (equivalence class) of the element 'id'.
	 */
	public int find(int id) {
		// TODO - Write this method
		// save the initial index
		int cachedIndex = G.itemIndex();

		// store the initial index
		int representativeIndex = id;

		// start at the vertex represented by the index given
		G.goIndex(id);
		// start at the first edge of this vertex
		G.eGoFirst(G.item());

		// continue iterating while there are still edges to iterate over
		while (!G.eAfter()) {
			// update stored index
			representativeIndex = G.eItemAdjacentIndex();

			// go to adjacent vertex referred to by this edge
			G.goIndex(representativeIndex);
			// start at the first edge of this new vertex
			G.eGoFirst(G.item());
		}

		// restore the initial index
		G.goIndex(cachedIndex);

		// return the final index
		return representativeIndex;
	}
		


	
	/**
	 * Merge the subsets containing two items, id1 and id2, making them, and all of the other elemnets in both sets, "equivalent".
	 * @param id1 First element.
	 * @param id2 Second element.
	 */
	public void union(int id1, int id2) {
		// TODO - Write this method.
		int root1 = find(id1);
		int root2 = find(id2);

		// if the two indices don't share a common root, merge them by adding an edge between their roots
		if (root2 != root1)
			G.addEdge(root1, root2);
	}


}
	
	
	


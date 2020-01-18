import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class InteractiveGraphView {

	Graph<MyNode, MyLink> g;

	static MyNode n1;
	static MyNode n2;
	static MyNode n3;
	static MyNode n4;
	static MyNode n5;
	int edgeCount = 0;

	/** Creates a new instance of SimpleGraphView */
	public InteractiveGraphView() {
		// Graph<V, E> where V is the type of the vertices and E is the type of the
		// edges
		g = new SparseMultigraph<MyNode, MyLink>();
		// Add some vertices and edges
		n1 = new MyNode(1);
		n2 = new MyNode(2);
		n3 = new MyNode(3);
		n4 = new MyNode(4);
		n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		g.addEdge(new MyLink(2.0, 48), n1, n2, EdgeType.DIRECTED); // This method
		g.addEdge(new MyLink(2.0, 48), n2, n3, EdgeType.DIRECTED);
		g.addEdge(new MyLink(3.0, 192), n3, n5, EdgeType.DIRECTED);
		g.addEdge(new MyLink(2.0, 48), n5, n4, EdgeType.DIRECTED); // or we can use
		g.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
		g.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
		g.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
		
	}

	class MyNode {
		int id; // good coding practice would have this as private

		public MyNode(int id) {
			this.id = id;
		}

		public String toString() { // Always a good idea for debuging
			return "V" + id; // JUNG2 makes good use of these.
		}
	}

	class MyLink {
		double capacity; // should be private
		double weight; // should be private for good practice
		int id;

		public MyLink(double weight, double capacity) {

			this.id = edgeCount++; // This is defined in the outer class.
			this.weight = weight;
			this.capacity = capacity;
		}

		public String toString() { // Always good for debugging
			return "E" + id;
		}

	}

}

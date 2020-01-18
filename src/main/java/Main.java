import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


public class Main {

	static InteractiveGraphView sgv;
	static JComboBox cbSecond;
	static Vector data;
	static JLabel label;

	public static void main(String[] args) {

		Main x = new Main();
		// I create the graph in the following...
		sgv = new InteractiveGraphView();
		// Layout<V, E>, VisualizationComponent<V,E>
		Layout<Integer, String> layout = new CircleLayout(sgv.g);
		layout.setSize(new Dimension(300, 300));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		// Show vertex and edge labels
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);

		data = new Vector();
		data.add(sgv.n1);
		data.add(sgv.n2);
		data.add(sgv.n3);
		data.add(sgv.n4);
		data.add(sgv.n5);
		cbSecond = new JComboBox(data);

		ActionListener actionListener = x.new TestActionListener();
		JButton button = new JButton("Рассчитать");
		label = new JLabel("Среднее значение длины кратчайших путей: ");
		button.addActionListener(actionListener);
		JPanel panel = new JPanel();
		panel.add(vv);
		panel.add(new JLabel("Выберите вершину"));
		panel.add(cbSecond);
		panel.add(button);
		panel.add(label);

		JFrame frame = new JFrame("Interactive Graph View 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}

	class TestActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double sr = getMean(cbSecond.getSelectedItem());
			label.setText("Среднее значение длины кратчайших путей: " + sr);
		}
	}

	public double getMean(Object selectItem) {
		double sum = 0;
		DijkstraShortestPath alg = new DijkstraShortestPath(sgv.g);
		for (int i = 0; i < 5; i++) {
			if (data.get(i) != selectItem)
				sum = sum + alg.getDistance(cbSecond.getSelectedItem(), data.get(i)).intValue();
		}
		double sr = sum / 4;
		return sr;
	}

}



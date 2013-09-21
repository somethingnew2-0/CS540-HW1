import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A kNN classification algorithm implementation.
 * 
 */

public class KNN {

	/**
	 * In this method, you should implement the kNN algorithm. You can add other
	 * methods in this class, or create a new class to facilitate your work. If
	 * you create other classes, DO NOT FORGET to include those java files when
	 * preparing your code for hand in.
	 * 
	 * Also, Please DO NOT MODIFY the parameters or return values of this
	 * method, or any other provided code. Again, create your own methods or
	 * classes as you need them.
	 * 
	 * @param trainingData
	 *            An Item array of training data
	 * @param testData
	 *            An Item array of test data
	 * @param k
	 *            The number of neighbors to use for classification
	 * @return The object KNNResult contains classification accuracy, category
	 *         assignment, etc.
	 */
	public KNNResult classify(Item[] trainingData, Item[] testData, int k) {
		KNNResult result = new KNNResult();
		result.categoryAssignment = new String[testData.length];
		result.nearestNeighbors = new String[testData.length][];
		
		// for each test item in testData
		for (int i = 0; i < testData.length; i++) {
			Item testItem = testData[i];

			// find kNN in trainingData
			PriorityQueue<DistanceItem> maxHeap = new PriorityQueue<DistanceItem>(
					k);
			for (Item trainingItem : trainingData) {
				DistanceItem trainingDistance = new DistanceItem(trainingItem,
						testItem);
				if (maxHeap.size() < k) {
					maxHeap.offer(trainingDistance);
				} else if (maxHeap.peek().compareTo(trainingDistance) > 0) {
					maxHeap.poll();
					maxHeap.offer(trainingDistance);
				}
			}

			// save kNN in KNNResult.nearestNeighbors and tally votes
			result.nearestNeighbors[i] = new String[maxHeap.size()];			
			HashMap<String, Integer> election = new HashMap<String, Integer>(
					maxHeap.size());
			Iterator<DistanceItem> iterator = maxHeap.iterator();
			for (int j = 0; j < maxHeap.size() && iterator.hasNext(); j++) {
				DistanceItem distanceItem = iterator.next();
				
				result.nearestNeighbors[i][j] = distanceItem.getItem().name;
				
				String category = distanceItem.getItem().category;
				Integer votes = election.get(category);
				if (votes == null) {
					election.put(category, 1);
				}
				election.put(category, votes + 1);
			}

			String winningCategory = "";
			int maxVotes = 0;
			for (String category : election.keySet()) {
				Integer votes = election.get(category);
				if (votes == null) {
					continue;
				}
				if (votes > maxVotes) {
					winningCategory = category;
					maxVotes = votes;
				}
			}

			// get predicted category, save in KNNResult.categoryAssignment
			result.categoryAssignment[i] = winningCategory;	
		}
		// calculate accuracy
		for (int i = 0; i < testData.length; i++) {
			if(testData[i].name.equals(result.categoryAssignment[i])) {
				result.accuracy++;
			}
		}
		result.accuracy /= testData.length;

		return result;
	}

	public double getDistance(Item item, Item otherItem) {
		return Math
				.sqrt(((otherItem.features[0] - item.features[0]) * (otherItem.features[0] - item.features[0]))
						+ ((otherItem.features[1] - item.features[1]) * (otherItem.features[1] - item.features[1]))
						+ ((otherItem.features[2] - item.features[2]) * (otherItem.features[2] - item.features[2])));
	}
}

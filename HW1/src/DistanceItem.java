public class DistanceItem implements Comparable<DistanceItem> {

	private Item trainingItem;

	// Distance from this training item to the test item
	private double distance;
	
	public Item getItem() {
		return trainingItem;
	}

	public DistanceItem(Item trainingItem, Item testItem) {
		this.trainingItem = trainingItem;
		this.distance = Math.sqrt(
				((testItem.features[0] - trainingItem.features[0]) * (testItem.features[0] - trainingItem.features[0]))
				+ ((testItem.features[1] - trainingItem.features[1]) * (testItem.features[1] - trainingItem.features[1]))
				+ ((testItem.features[2] - trainingItem.features[2]) * (testItem.features[2] - trainingItem.features[2]))
			);
	}

	@Override
	public int compareTo(DistanceItem otherItem) {
		return Double.compare(distance, otherItem.distance);
	}

}

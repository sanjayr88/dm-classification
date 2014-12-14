package dataobject;

import java.util.ArrayList;

public class Node {
	private int featureIndex;
	private ArrayList<Node> children = new ArrayList<Node>();
	private int value;
	private boolean isLeaf;
	private ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private int[] usedFeatures;
	public int getFeatureIndex() {
		return featureIndex;
	}
	public void setFeatureIndex(int featureIndex) {
		this.featureIndex = featureIndex;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	public void addChildren(Node child){
		this.children.add(child);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ArrayList<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(ArrayList<Encounter> encounters) {
		this.encounters = encounters;
	}
	public void addEncounter(Encounter encounter){
		this.encounters.add(encounter);
	}
	public int[] getUsedFeatures() {
		return usedFeatures;
	}
	public void setUsedFeatures(int[] usedFeatures) {
		this.usedFeatures = usedFeatures;
	}
	public Node(int value){
		this.value = value;
		this.isLeaf = true;
	}
	public Node(int[] usedFeatures){
		this.usedFeatures = usedFeatures;
	}
	
}

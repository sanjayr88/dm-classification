package dataobject;

import java.util.ArrayList;
import java.util.HashMap;

public class BayeProb {
	
	int sampleSize;
	int h1Size;  //Classifier 1 is for No
	int h2Size;  //Classifier 2 is for <30
	int h3Size;  //Classifier 3 is for >30
	ArrayList<HashMap<Object,Integer>> h1List ;
	ArrayList<HashMap<Object,Integer>> h2List ;
	ArrayList<HashMap<Object,Integer>> h3List ;
	double probH1;
	double probH2;
	double probH3;
	
	public BayeProb(){		
		sampleSize=0;
		h1Size=0;
		h2Size=0;
		h3Size=0;
		probH1=0;
		probH2=0;
		probH3=0;
		h1List = new ArrayList<HashMap<Object,Integer>>();
		h2List = new ArrayList<HashMap<Object,Integer>>();
		h3List = new ArrayList<HashMap<Object,Integer>>();
		
		for(int i=0;i<10;i++){
			h1List.add(new HashMap<Object,Integer>());
			h2List.add(new HashMap<Object,Integer>());
			h3List.add(new HashMap<Object,Integer>());
		}
	}
	 
	
	public ArrayList<HashMap<Object, Integer>> getH1List() {
		return h1List;
	}
	public void setH1List(ArrayList<HashMap<Object, Integer>> h1List) {
		this.h1List = h1List;
	}
	public ArrayList<HashMap<Object, Integer>> getH2List() {
		return h2List;
	}
	public void setH2List(ArrayList<HashMap<Object, Integer>> h2List) {
		this.h2List = h2List;
	}
	public ArrayList<HashMap<Object, Integer>> getH3List() {
		return h3List;
	}
	public void setH3List(ArrayList<HashMap<Object, Integer>> h3List) {
		this.h3List = h3List;
	}
	
	public int getSampleSize() {
		return sampleSize;
	}
	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	public int getH1Size() {
		return h1Size;
	}
	public void setH1Size(int h1Size) {
		this.h1Size = h1Size;
	}
	public int getH2Size() {
		return h2Size;
	}
	public void setH2Size(int h2Size) {
		this.h2Size = h2Size;
	}
	public int getH3Size() {
		return h3Size;
	}
	public void setH3Size(int h3Size) {
		this.h3Size = h3Size;
	}
	public double getProbH1() {
		return probH1;
	}


	public void setProbH1(double probH1) {
		this.probH1 = probH1;
	}


	public double getProbH2() {
		return probH2;
	}


	public void setProbH2(double probH2) {
		this.probH2 = probH2;
	}


	public double getProbH3() {
		return probH3;
	}


	public void setProbH3(double probH3) {
		this.probH3 = probH3;
	}

}

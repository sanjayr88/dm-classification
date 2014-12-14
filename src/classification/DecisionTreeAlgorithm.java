package classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dataobject.Encounter;
import dataobject.Node;

public class DecisionTreeAlgorithm {
	public static Node generateDecisionTree(ArrayList<Encounter> encounters, int[] usedFeatures){
		int indexForCalculation = selectNextBestFeature(encounters, usedFeatures);
		usedFeatures[indexForCalculation] = 1;
		Node node = new Node(usedFeatures);
		HashMap<Integer, ArrayList<Encounter>> attributes = new HashMap<Integer, ArrayList<Encounter>>();
		ArrayList<Encounter> temp;
		//segregate them according to their "attributes"
		for(Encounter e : encounters){
			//System.out.println(e.getFeatures()[indexForCalculation]);
			if(attributes.containsKey(e.getFeatures()[indexForCalculation])){
				temp = attributes.get(e.getFeatures()[indexForCalculation]);
				temp.add(e);
			}
			else{
				temp = new ArrayList<Encounter>();
				temp.add(e);
				attributes.put(e.getFeatures()[indexForCalculation], temp);
			}
			temp = null;
		}
		//check if pure/impure
		for(Map.Entry entry : attributes.entrySet()){
			ArrayList<Encounter> tempList = (ArrayList<Encounter>) entry.getValue();
			int groundTruth = 0;
			boolean isPure = true;
			for(Encounter e : tempList){
				if(groundTruth==0){
					groundTruth = e.getReadmittedGroundTruth();
				}
				else{
					if (e.getReadmittedGroundTruth()!=groundTruth) {
						isPure = false;
						break;
					}
				}
			}
			//if pure then create a leaf node
			if(isPure){
				Node child = new Node(groundTruth);
				node.addChildren(child);
			}
			//if impure go into recursion
			else{
				
			}
		}
		
		
		
		
		System.out.println("hello");
		return node;
	}

	public static int selectNextBestFeature(ArrayList<Encounter> encounters, int[] usedFeatures){
		int returnIndex = 0;
		double[] entropy = new double[usedFeatures.length];
		double minEntropy = Integer.MAX_VALUE;
		for(int i = 0; i<usedFeatures.length; i++){
			if(usedFeatures[i]==0){
				entropy[i] = calculateEntropy(encounters, i);
				if(entropy[i]<minEntropy){
					minEntropy = entropy[i];
					returnIndex=i;
				}
			}
		}
		System.out.println("returning index " + returnIndex);
		return returnIndex;
	}

	public static double calculateEntropy1(ArrayList<Encounter> encounters,int indexToUse){
		double result = 0;
		double result1 = 0, result2 = 0, result3 = 0;
		int value = 0;
		double size = encounters.size();
		HashMap<Integer, Integer> attributesH1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> attributesH2 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> attributesH3 = new HashMap<Integer, Integer>();
		for(Encounter e : encounters){
			value = e.getFeatures()[indexToUse];
			if(e.getReadmittedGroundTruth()=='<'){
				if(attributesH1.containsKey(value)){
					attributesH1.put(value, attributesH1.get(value)+1);
				}
				else{
					attributesH1.put(value, 1);
				}
			}else if(e.getReadmittedGroundTruth()=='>'){
				if(attributesH2.containsKey(value)){
					attributesH2.put(value, attributesH2.get(value)+1);
				}
				else{
					attributesH2.put(value, 1);
				}
			}else{
				if(attributesH3.containsKey(value)){
					attributesH3.put(value, attributesH3.get(value)+1);
				}
				else{
					attributesH3.put(value, 1);
				}
			}
		}
		int size1 = attributesH1.size();
		int size2 = attributesH2.size();
		int size3 = attributesH3.size();

		for(double i : attributesH1.values()){
			result1+=(-1)*(i/size1)*Math.log((i/size1))/Math.log(2);
		}
		for(double i : attributesH2.values()){
			result2+=(-1)*(i/size2)*Math.log((i/size2))/Math.log(2);
		}
		for(double i : attributesH3.values()){
			result3+=(-1)*(i/size3)*Math.log((i/size3))/Math.log(2);
		}
		result = (size1/size)*result1+(size2/size)*result2+(size3/size)*result3;
		System.out.println("for index = " + indexToUse + " entropy is " + result);
		return result;
	}

	public static double calculateEntropy(ArrayList<Encounter> encounters,int indexToUse){
		double result = 0;
		int value = 0;
		double size = encounters.size();
		HashMap<Integer, HashMap<Integer, Integer>> attributes = new HashMap<Integer, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> temp;
		for(Encounter e : encounters){
			value = e.getFeatures()[indexToUse];
			if(attributes.containsKey(value)){
				temp = attributes.get(value);
				if(temp.containsKey(e.getReadmittedGroundTruth())){
					temp.put((int) e.getReadmittedGroundTruth(), temp.get(e.getReadmittedGroundTruth())+1);
				}else{
					temp.put((int) e.getReadmittedGroundTruth(), 1);
				}
			}
			else{
				temp = new HashMap<Integer, Integer>();
				temp.put((int) e.getReadmittedGroundTruth(), 1);
				attributes.put(e.getFeatures()[indexToUse], temp);
			}
			temp = null;
		}
		double tempResult = 0, count = 0;
		for(HashMap<Integer, Integer> iteratorMap : attributes.values()){
			for(double i : iteratorMap.values()){
				count +=i;
			}
			for(double i : iteratorMap.values()){
				tempResult+=(-1)*(i/count)*Math.log((i/count))/Math.log(2);
			}
			result+=count/size*tempResult;
			tempResult = 0;
		}
		System.out.println("for index = " + indexToUse + " entropy is " + result);
		return result;
	}
}


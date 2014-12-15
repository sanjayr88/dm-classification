package classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dataobject.Encounter;
import dataobject.Node;

public class DecisionTreeAlgorithm {
	public static Node generateDecisionTree(Node node, ArrayList<Encounter> encounters, int[] usedFeatures){
		int indexForCalculation = selectNextBestFeature(encounters, usedFeatures);
		if(indexForCalculation == 15){
			node.setLeaf(true);
			int result1 = 0, result2 = 0, result3 = 0;
			for(Encounter e : encounters){
				if(e.getReadmittedGroundTruth()=='<') result1++;
				else if(e.getReadmittedGroundTruth()=='>') result2++;
				else result3++;
			}
			if(result1>=result2&&result1>=result3) 
				node.setValue('<');
			else if(result2>=result1&&result2>=result3) 
				node.setValue('>');
			else node.setValue('N');
			//node.setValue(Math.max(Math.max(result1, result2), result3));
			System.out.println("setting node value to "+node.getValue());
			return node;
		}else{
			usedFeatures[indexForCalculation] = 1;
			node.setFeatureIndex(indexForCalculation);
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
					child.setFeatureIndex(indexForCalculation);
					System.out.println("created pure child with "+child.getValue());
				}
				//if impure go into recursion
				else{
					Node child = new Node(usedFeatures);
					node.addChildren(child);
					generateDecisionTree(child, tempList, usedFeatures);
				}
			}
			System.out.println("hello");
			return node;
		}
	}

	public static int selectNextBestFeature(ArrayList<Encounter> encounters, int[] usedFeatures){
		int returnIndex = 0;
		double[] entropy = new double[usedFeatures.length];
		double minEntropy = Integer.MAX_VALUE;
		boolean testFlag = true;
		for(int i = 0; i<usedFeatures.length; i++){
			if(usedFeatures[i]==0){
				testFlag = false;
				entropy[i] = calculateEntropy(encounters, i);
				if(entropy[i]<minEntropy){
					minEntropy = entropy[i];
					returnIndex=i;
				}
			}
		}
		//all features are used
		if(testFlag) return 15;
		//System.out.println("returning index " + returnIndex);
		return returnIndex;
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
		//System.out.println("for index = " + indexToUse + " entropy is " + result);
		return result;
	}
}


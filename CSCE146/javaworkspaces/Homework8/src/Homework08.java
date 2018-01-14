import java.util.*;
public class Homework08 {/*
Name: Anthony Frazier
Date: April 6, 2016
Program Name: Homework08
Description: Homework08
Objective:	Objective:
Konisberg Bridge Puzzle

*/

		private static final int DEFAULT_NODE_COUNT = 10;
		private int[][][] BridgeGraph;
		private ArrayList<Integer> markedVerts;
		private ArrayList<Integer> visitedVerts;
		
		public Homework08()
		{
			BridgeGraph = new int[DEFAULT_NODE_COUNT][DEFAULT_NODE_COUNT][DEFAULT_NODE_COUNT];
			markedVerts = new ArrayList<Integer>();
			visitedVerts = new ArrayList<Integer>();
		}
		
		public Homework08(int size)
		{
			if (size <= 0)
			{
				System.out.println("Invalid size. Aborting.");
				return;
			}
			else
			{
				BridgeGraph = new int[size][size][size];
				markedVerts = new ArrayList<Integer>();
				visitedVerts = new ArrayList<Integer>();
				for (int i = 0; i < size; i++)
				{
					for (int y = 0; y < size; y++)
					{
						for (int z = 0; z < size; z++)
						{
							BridgeGraph[i][y][z] = 0;
						}
					}
				}
			}
		}
		
		public void addEdge(int fromMass, int toMass, int bridgeNumber)
		{
			if (fromMass < 0 || toMass < 0)
			{
				System.out.println("Invalid values. Aborting.");
				return;
			}
			else
			{
				BridgeGraph[fromMass][toMass][bridgeNumber] = bridgeNumber;
			}
		}
		
		public void printBridgePath(int bridgeNum)
		{
			markedVerts.clear();
			printBridgePath(0, BridgeGraph);
		}
		
		private void printBridgePath(int bridgeNum, int[][][] BridgeGraph)
		{
			for (int i = 0; i < BridgeGraph.length; i++)
			{
				for (int j = 0; j < BridgeGraph.length; j++)
				{
					for (int k = 0; k < BridgeGraph.length; k++)
					{
						if (BridgeGraph[i][j][k] != 0)
						{
							System.out.println(BridgeGraph[i][j][k]);
							BridgeGraph[i][j][k] = 0;
							BridgeGraph[j][i][k] = 0;
							printBridgePath(k++);
						}
					}
				}
			}
		}
		
		public void printDFS()
		{
			markedVerts.clear();
			printDFS(0);
		}
		
		public void printDFSForAll(int startIndex)
		{
			markedVerts.clear();
			printDFS(startIndex);
		}
		
		private void printDFS(int index)
		{
			System.out.println(BridgeGraph[index][index][index]);
			markedVerts.add(index);
			// Find a neighbor to traverse to
			for (int i = 1; i < BridgeGraph.length; i++)
			{
				if (BridgeGraph[index][index][index] != 0 && markedVerts.contains(i) == false)
				{
					System.out.println(BridgeGraph[index][index][i]);
					printDFS(i);
				}
			}
			return;
		}

		public void printBFS()
		{
			markedVerts.clear();
			visitedVerts.clear();
			printBFS(1);
		}
		
		public void printBFSForAll(int startIndex)
		{
			markedVerts.clear();
			visitedVerts.clear();
			printBFS(startIndex);
		}
		private void printBFS(int index)
		{
			if (visitedVerts.contains(index) == false)
			{
				System.out.println(index);
				visitedVerts.add(index);
			}
			markedVerts.add(index);
			for (int i = 0; i < BridgeGraph[index].length; i++)
			{
				if (BridgeGraph[index][i] != null && visitedVerts.contains(i) == false)
				{
					System.out.println(i);
					visitedVerts.add(i);
				}
			}
			// Move to next node that's not marked
			for (int i = 0; i < BridgeGraph.length; i++)
			{
				if (BridgeGraph[index][i] != null && markedVerts.contains(i) == false)
				{
					printBFS(i);
				}
			}
		}
}
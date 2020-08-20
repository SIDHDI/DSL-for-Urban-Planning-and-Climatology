import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Region
{
	public float TOTAL_AREA = (float) (250*250*1.0);
	public float AREA_PER_TREE = (float) (20*1.0);
	
	//fields in database
	private int regionID;
	private String buildingsDetails;
	private List<Building> buildings;
	private float treesArea;
	private float bushesArea;
	private float lakeArea;

	private float buildingArea;
	private float buildingHeight;
	private float freeSpace;

	//fractions
	private float perviousSurfaceFraction;
	private float buildingAreaFraction;
	private float treeAreaFraction;
	private float bushesAreaFraction;
	private float lakeAreaFraction;

	private int score;
	private String lcz;

	Region()
	{
		this.buildings = new ArrayList<Building>();
	}
	
	void getDataFromDB(int r)
	{
		setRegionID(r);
		try
		{
			final String URL = "jdbc:mysql://localhost/dataDB";
			final String USER = "root";
			final String PASSWORD = "password";
			
			java.sql.Connection myConn = java.sql.DriverManager.getConnection(URL, USER,
														PASSWORD);
			java.sql.Statement myStmt = myConn.createStatement();	
			java.sql.ResultSet myRs = myStmt.executeQuery("Select * from region_details where regionID =" + "'" + regionID + "'");
			
			if (myRs.next())
			{
				setBuildingsDetails(myRs.getString("buildings"));
				setTreesArea(myRs.getFloat("treesArea"));
				setBushesArea(myRs.getFloat("bushesArea"));
				setLakeArea(myRs.getFloat("lakeArea"));
//				System.out.println(treesArea + bushesArea + buildingsDetails + lakeArea);
			}
			else 
			{
				System.out.println("Region not found");	
			}	
			myStmt.close();	
			myConn.close();
		} 
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}				
	}

	void buildBuildingArray()
	{
		try {
			StringTokenizer st = new StringTokenizer(this.buildingsDetails,";");
			while (st.hasMoreTokens()) 
			{
				String token1 = st.nextToken();
				StringTokenizer st1 = new StringTokenizer(token1,",");
	//			System.out.println(token1);
				Building b = new Building();
				ArrayList<Integer> A = new ArrayList();
				while(st1.hasMoreTokens())
				{
					String tk = st1.nextToken();
	//				System.out.println(tk);
					A.add(Integer.parseInt(tk));
				}
				b.setLength(A.get(0));
				b.setBreadth(A.get(1));
				b.setHeight(A.get(2));
				buildings.add(b);				
			}
			float area = 0;
			float ht = 0;
			for (Building b: buildings)
			{
				area += (b.getLength()*b.getBreadth());
				ht += b.getHeight();
			}
			setBuildingArea(area);
			
			if (buildings.size() == 0)
				setBuildingHeight(0);
			else
				setBuildingHeight(ht/(buildings.size()));
		}
		catch(NullPointerException e){
			return;
		}

	}
	void performCalculations()
	{
//		System.out.println(buildingArea + ";" + treesArea + ";" + bushesArea + ";" + lakeArea);
		setFreeSpace(TOTAL_AREA - buildingArea - treesArea - bushesArea - lakeArea);

		//fractions
		setPerviousSurfaceFraction((freeSpace+treesArea+bushesArea)/TOTAL_AREA);
		setBuildingAreaFraction(buildingArea/TOTAL_AREA);
		setTreeAreaFraction(treesArea/TOTAL_AREA);
		setBushesAreaFraction(bushesArea/TOTAL_AREA);
		setLakeAreaFraction(lakeArea/TOTAL_AREA);
		System.out.println("buildingHeight: " + buildingHeight + "\nperviousSurfaceFraction: " + perviousSurfaceFraction + "\nbuildingAreaFraction: " + buildingAreaFraction + "\ntreeAreaFraction: " + treeAreaFraction + "\nbushesAreaFraction" + bushesAreaFraction + "\nlakeAreaFraction: " + lakeAreaFraction);
	}
	
	void calculateInitialLCZ(int r)
	{
		getDataFromDB(r);
		buildBuildingArray();
		calculateLCZ();
	}

	void calculateLCZ()
	{
		performCalculations();
		int sc = 0;
		List<Integer> scoreForVariousLCZ = new ArrayList<Integer>();

		//lcz 1
		if (buildingHeight > 25) sc++;
		//if (perviousSurfaceFraction < 0.1) sc++;
		if (buildingAreaFraction < 0.6 && buildingAreaFraction > 0.4) sc++;
		if (treeAreaFraction < 0.05) sc++;
		if (bushesAreaFraction < 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz2
		sc = 0;
		if (buildingHeight <= 25 && buildingHeight >= 10) sc++;
		////if (perviousSurfaceFraction < 0.2) sc++;
		if (buildingAreaFraction < 0.7 && buildingAreaFraction > 0.4) sc++;
		if (treeAreaFraction < 0.05) sc++;
		if (bushesAreaFraction < 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz3
		sc = 0;
		if (buildingHeight < 10 && buildingHeight >= 3) sc++;
		//if (perviousSurfaceFraction < 0.3) sc++;
		if (buildingAreaFraction < 0.7 && buildingAreaFraction > 0.4) sc++;
		if (treeAreaFraction < 0.05) sc++;
		if (bushesAreaFraction < 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz4
		sc = 0;
		if (buildingHeight > 25) sc++;
		//if (perviousSurfaceFraction <= 0.4 && perviousSurfaceFraction >= 0.3) sc++;
		if (buildingAreaFraction <= 0.4 && buildingAreaFraction >= 0.2) sc++;
		if (treeAreaFraction <= 0.15 && treeAreaFraction >= 0.1) sc++;
		if (bushesAreaFraction <= 0.15 && bushesAreaFraction >= 0.1) sc++;
		if (lakeAreaFraction < 0.9) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz5
		sc = 0;
		if (buildingHeight <= 25 && buildingHeight >= 10) sc++;
		//if (perviousSurfaceFraction <= 0.4 && perviousSurfaceFraction >= 0.2) sc++;
		if (buildingAreaFraction <= 0.4 && buildingAreaFraction >= 0.2) sc++;
		if (treeAreaFraction <= 0.15 && treeAreaFraction >= 0.05) sc++;
		if (bushesAreaFraction <= 0.15 && bushesAreaFraction >= 0.05) sc++;
		if (lakeAreaFraction < 0.9) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz6
		sc = 0;
		if (buildingHeight <= 10 && buildingHeight >= 3) sc++;
		//if (perviousSurfaceFraction <= 0.6 && perviousSurfaceFraction >= 0.3) sc++;
		if (buildingAreaFraction <= 0.4 && buildingAreaFraction >= 0.2) sc++;
		if (treeAreaFraction <= 0.15 && treeAreaFraction >= 0.1) sc++;
		if (bushesAreaFraction <= 0.15 && bushesAreaFraction >= 0.1) sc++;
		if (lakeAreaFraction < 0.9) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz7
		sc = 0;
		if (buildingHeight <= 4 && buildingHeight >= 2) sc++;
		//if (perviousSurfaceFraction < 0.3) sc++;
		if (buildingAreaFraction < 0.9 && buildingAreaFraction > 0.6) sc++;
		if (treeAreaFraction < 0.15) sc++;
		if (bushesAreaFraction < 0.15) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz8
		sc = 0;
		if (buildingHeight <= 10 && buildingHeight >= 3) sc++;
		//if (perviousSurfaceFraction < 0.2) sc++;
		if (buildingAreaFraction <= 0.5 && buildingAreaFraction >= 0.3) sc++;
		if (treeAreaFraction < 0.1) sc++;
		if (bushesAreaFraction < 0.1) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz9
		sc = 0;
		if (buildingHeight <= 10 && buildingHeight >= 3) sc++;
		//if (perviousSurfaceFraction <= 0.5 && perviousSurfaceFraction >= 0.4) sc++;
		if (buildingAreaFraction <= 0.2 && buildingAreaFraction >= 0.1) sc++;
		if (treeAreaFraction <= 0.2 && treeAreaFraction >= 0.1) sc++;
		if (bushesAreaFraction <= 0.2 && bushesAreaFraction >= 0.1) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lcz10
		sc = 0;
		if (buildingHeight <= 15 && buildingHeight >= 5) sc++;
		//if (perviousSurfaceFraction < 0.2) sc++;
		if (buildingAreaFraction <= 0.3 && buildingAreaFraction >= 0.2) sc++;
		if (treeAreaFraction <= 0.05) sc++;
		if (bushesAreaFraction <= 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lczA
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction <= 0.8 && perviousSurfaceFraction >= 0.6) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 1 && treeAreaFraction >= 0.7) sc++;
		if (bushesAreaFraction < 0.3) sc++;
		if (lakeAreaFraction < 0.85) sc++;
		scoreForVariousLCZ.add(sc);

		//lczB
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction <= 0.5 && perviousSurfaceFraction >= 0.4) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.7 && treeAreaFraction >= 0.3) sc++;
		if (bushesAreaFraction <= 0.7 && bushesArea >= 0.3) sc++;
		if (lakeAreaFraction < 0.85) sc++;
		scoreForVariousLCZ.add(sc);

		//lczC
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction >= 0.9) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.05) sc++;
		if (bushesAreaFraction <= 0.3 && bushesAreaFraction >= 0.1) sc++;
		if (lakeAreaFraction < 0.85) sc++;
		scoreForVariousLCZ.add(sc);

		//lczD
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction >= 0.9) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.05) sc++;
		if (bushesAreaFraction >= 0.3 && bushesAreaFraction < 0.7) sc++;
		if (lakeAreaFraction < 0.85) sc++;
		scoreForVariousLCZ.add(sc);

		//lczE
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction <= 0.1) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.05) sc++;
		if (bushesAreaFraction <= 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lczF
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction >= 0.9) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.05) sc++;
		if (bushesAreaFraction <= 0.05) sc++;
		if (lakeAreaFraction == 0) sc++;
		scoreForVariousLCZ.add(sc);

		//lczG
		sc = 0;
		if (buildingHeight < 0.05) sc++;
		if (perviousSurfaceFraction >= 0.9) sc++;
		if (buildingAreaFraction <= 0.1) sc++;
		if (treeAreaFraction <= 0.5) sc++;
		if (bushesAreaFraction <= 0.05) sc++;
		if (lakeAreaFraction >= 0.85) sc++;
		scoreForVariousLCZ.add(sc);

		int maxSc = 0;
		int maxLCZ = 0; 
		int counter = 1;
		for (int scores : scoreForVariousLCZ)
		{
//			System.out.println("Score for " + counter + ": " + scores);
			if (scores > maxSc)
			{
				maxSc = scores;
				maxLCZ = counter;
			}
			counter++;
		}

		String lczFinal;
		if (maxLCZ > 10)
			lczFinal = Character.toString((char)('A' + maxLCZ - 11));
		else if (maxLCZ < 10)
			lczFinal = Character.toString((char)('0' + maxLCZ));
		else
			lczFinal = "10";
		
		setScore(maxLCZ);

		setLcz(lczFinal);
		System.out.println("LCZ is:" + lcz + "\n\n");
	}
	
	void findTempBuildAction(int buildingCount, float l, float b, float h)
	{
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
	    
	    buildingArea += (l*b*buildingCount);
		buildingHeight = (buildingHeight * buildings.size() + h * buildingCount)/(buildings.size() + buildingCount);
		
	    calculateLCZ();
	    System.out.println("Changed Temperature");
	    LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
	    lczLstMapper1.getLST();
	    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
	}
	
	void findtempDemolish(int building_count, float l, float b, float h)
	{
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
		int counter = 0;
		Building bToBeRemoved = new Building(l,b,h);
		boolean found = false;
		for (Building build : buildings)
		{
			if (build.equals(bToBeRemoved))
			{	
				break;
			}
			counter++;
		}
		if (counter == buildings.size())
		{
			System.out.println("Building doesn't exist!!");
		}
		else
		{
			int c = 0;
			int init_c = counter;
			for (int i=counter; i<buildings.size() || !found; i++)
			{
				if (buildings.get(i).equals(bToBeRemoved))
				{
					c++;
					if (c == building_count)
						found = true;
				}
				else 
					break;
			}
			if (!found)
			{
				System.out.println("Building don't exist!!");
				return;
			}
			
			System.out.println("Changed Temperature");
			buildingArea -= (l*b*building_count);
			buildingHeight = (buildingHeight * buildings.size() - h * building_count)/(buildings.size() - building_count);
			calculateLCZ();
		    LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
		    lczLstMapper1.getLST();
		    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
		    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
		    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
		}
	}
	
	void findtempPlantTrees(int no_of_trees)
	{
		float area = no_of_trees * AREA_PER_TREE;
		if (area > freeSpace)
		{
			System.out.println("Not enough space!");
		}
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
	    
		setTreesArea(treesArea + area);
		calculateLCZ();
		
		LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
	    lczLstMapper1.getLST();
	    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
		
	}
	
	void findtempCutTrees(int no_of_trees)
	{
		float area = no_of_trees * AREA_PER_TREE;
		if (area > treesArea)
		{
			System.out.println("Inadequate trees");
			return;
		}
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
	    
		setTreesArea(treesArea - area);
		calculateLCZ();
		
		LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
	    lczLstMapper1.getLST();
	    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
	}

	void findtempPlantBushes(float area)
	{
		if (area > freeSpace)
		{
			System.out.println("Inadequate Space!");
			return;
		}
		else
		{
			System.out.println("Initial Temperature");
		    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
		    lczLstMapper.getLST();
		    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
		    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
		    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
		    
			setTreesArea(bushesArea + area);
			calculateLCZ();
			
			LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
		    lczLstMapper1.getLST();
		    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
		    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
		    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
		}
	}
	
	void findtempRemoveBushes(float area)
	{
		if (area > bushesArea)
		{
			System.out.println("Inadequate bushes");
			return;
		}
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
	    
		setBushesArea(bushesArea - area);
		calculateLCZ();
		
		LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
	    lczLstMapper1.getLST();
	    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
	}
	
	void findtempCreateLake(int area)
	{
		if (area > freeSpace)
		{
			System.out.println("Inadequate Space!");
			return;
		}
		else
		{
			System.out.println("Initial Temperature");
		    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
		    lczLstMapper.getLST();
		    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
		    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
		    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
		    
			setLakeArea(lakeArea + area);
			calculateLCZ();
			
			LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
		    lczLstMapper1.getLST();
		    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
		    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
		    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
		}
	}
	
	void findtempRemoveLake(int area)
	{
		if (area > lakeArea)
		{
			System.out.println("Inadequate lakes");
			return;
		}
		System.out.println("Initial Temperature");
	    LCZ_LSTMapper lczLstMapper = new LCZ_LSTMapper(getScore());
	    lczLstMapper.getLST();
	    System.out.println("min Temperature: " + lczLstMapper.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp()+"\n\n");
	    
		setLakeArea(lakeArea - area);
		calculateLCZ();
		
		LCZ_LSTMapper lczLstMapper1 = new LCZ_LSTMapper(getScore());
	    lczLstMapper1.getLST();
	    System.out.println("min Temperature: " + lczLstMapper1.getMinTemp());
	    System.out.println("max Temperature: " + lczLstMapper1.getMaxTemp());
	    System.out.println("avg Temperature: " + lczLstMapper.getAvgTemp());
	}
	
	void caltreesBuildAction(int no_of_buildings, int l, int b, int h)
	{
		float area = l*b*no_of_buildings;
		if (area > freeSpace)
		{
			System.out.println("Not enough Space");
			return;
		}
		else
		{
			buildingArea = area;
			freeSpace -= area;
			buildingHeight = (buildingHeight*buildings.size() + h*no_of_buildings)/(buildings.size()+no_of_buildings);
			int prevScore = score;
			calculateLCZ();
			int diff = Math.abs(prevScore-score);
			if (diff == 0)
			{
				System.out.println("No need of extra trees");
			}
			else if (diff == 1)
			{
				if (score <= 3 || score == 10 || score > 12)
				{
					// make treesArea > 5%
					float extraTrees = (float)0.05 * TOTAL_AREA - treesArea;
					if (extraTrees > freeSpace)
					{
						System.out.println("Insufficient space to cope up for LCZ");
					}
					else
					{
						System.out.println("Trees to be planted more: " + (int)(extraTrees/AREA_PER_TREE));
					}
				}
				else if (score == 6 || score == 9)
				{
					//make trees >20%
					float extraTrees = (float)0.2 * TOTAL_AREA - treesArea;
					if (extraTrees > freeSpace)
					{
						System.out.println("Insufficient space to cope up for LCZ");
					}
					else
					{
						System.out.println("Trees to be planted more: " + (int)(extraTrees/AREA_PER_TREE));
					}
				}
				else if (score == 4 || score == 5 || score == 7)
				{
					//make trees >15%
					float extraTrees = (float)0.15 * TOTAL_AREA - treesArea;
					if (extraTrees > freeSpace)
					{
						System.out.println("Insufficient space to cope up for LCZ");
					}
					else
					{
						System.out.println("Trees to be planted more: " + (int)(extraTrees/AREA_PER_TREE));
					}
				}
				else if (score == 8)
				{
					//make trees > 10%
					float extraTrees = (float)0.1 * TOTAL_AREA - treesArea;
					if (extraTrees > freeSpace)
					{
						System.out.println("Insufficient space to cope up for LCZ");
					}
					else
					{
						System.out.println("Trees to be planted more: " + (int)(extraTrees/AREA_PER_TREE));
					}
				}
				else if (score == 12)
				{
					//make trees > 70%
					float extraTrees = (float)0.7 * TOTAL_AREA - treesArea;
					if (extraTrees > freeSpace)
					{
						System.out.println("Insufficient space to cope up for LCZ");
					}
					else
					{
						System.out.println("Trees to be planted more: " + (int)(extraTrees/AREA_PER_TREE));
					}
				}
				else //for lcz A
				{
					System.out.println("Only planting trees won't help!");
				}
			}
			else
			{
				System.out.println("Only planting trees won't help!");
			}
			
		}
	}

	public void DesignResidential(int population)
	{
		int area_per_building = 80;
		double open_space_area = 0.1*area_per_building;
		double other_amenities = 0.05*area_per_building;
		double green_cover_area = 0.1*area_per_building;
		int height_per_storey = 5;
		int residents_per_floor = 4;
		int no_buildings_possible =(int)(freeSpace/(area_per_building+open_space_area+other_amenities+green_cover_area));
		int no_of_floors = (int)(this.getBuildingHeight()/height_per_storey + 1);
		int no_people_per_building_with_avg_height = residents_per_floor*no_of_floors;
		int no_of_buildings_with_avg_height_required = population/no_people_per_building_with_avg_height;
		if(no_of_buildings_with_avg_height_required > no_buildings_possible)
		{
			System.out.println("Plan Not possible with avg height");
		}
		else
		{
		System.out.println("Recomended Design plan in region " + this.regionID + ": ");
		System.out.println("Number of buildings: " + no_of_buildings_with_avg_height_required );
		System.out.println("area per building: " + area_per_building );
		System.out.println("Total building area required: "+ no_of_buildings_with_avg_height_required*area_per_building );
		System.out.println("Open space area: " + no_of_buildings_with_avg_height_required*open_space_area);
		System.out.println("Green cover area: " + no_of_buildings_with_avg_height_required*green_cover_area);
		System.out.println("Area for other amenities: " + no_of_buildings_with_avg_height_required*other_amenities);
		System.out.println("Total Area required: "+ no_of_buildings_with_avg_height_required*(area_per_building+open_space_area+other_amenities+green_cover_area));
		}
	}
	
	public void DesignEducational(int population)
	{
		double area_per_building = 2000; // 6 classroom 1sqm per student + laboratory + department library + Staff room + office area + HOD office + Toilet block 
		double open_space_area = 1.5 * area_per_building; 
		double other_amenities = 0.1*area_per_building;
		double green_cover_area = 0.2*area_per_building;
		float height_per_story = 3;
		int residents_per_flore = 60;
		int no_buildings_possible = (int) (freeSpace/(area_per_building+open_space_area+other_amenities+green_cover_area));
		int no_of_flores = (int) (this.getBuildingHeight()/height_per_story + 1);
		int no_people_per_building_with_avg_height = residents_per_flore*no_of_flores;
		int no_of_buildings_with_avg_height_required = population/no_people_per_building_with_avg_height;
		if(no_of_buildings_with_avg_height_required > no_buildings_possible)
		{
			System.out.println("Plan Not possible with avg height");
		}
		else
		{
			System.out.println("Recomended Design plan in region " + this.regionID + ": ");
			System.out.println("Number of buildings: " + no_of_buildings_with_avg_height_required );
			System.out.println("area per building: " + area_per_building );
			System.out.println("Total building area required: "+ no_of_buildings_with_avg_height_required*area_per_building );
			System.out.println("Open space area: " + no_of_buildings_with_avg_height_required*open_space_area);
			System.out.println("Green cover area: " + no_of_buildings_with_avg_height_required*green_cover_area);
			System.out.println("Area for other amenities: " + no_of_buildings_with_avg_height_required*other_amenities);
			System.out.println("Total Area required: "+ no_of_buildings_with_avg_height_required*(area_per_building+open_space_area+other_amenities+green_cover_area));
		}
	}
	
	public void DesignCommercial(int population)
	{
		int area_per_building = 300; 
		double open_space_area = 4*area_per_building; 
		double other_amenities = 0.1*area_per_building;
		double green_cover_area = 0.01*area_per_building;
		int height_per_story = 3;
		int residents_per_flore = 15;
		int no_buildings_possible = (int) (freeSpace/(area_per_building+open_space_area+other_amenities+green_cover_area));
		int no_of_flores = (int) (this.getBuildingHeight()/height_per_story + 1);
		int no_people_per_building_with_avg_height = residents_per_flore*no_of_flores;
		int no_of_buildings_with_avg_height_required = population/no_people_per_building_with_avg_height;
		if(no_of_buildings_with_avg_height_required > no_buildings_possible)
		{
			System.out.println("Plan Not possible with avg height");
		}
		else
		{
			System.out.println("Recomended Design plan in region " + this.regionID + ": ");
			System.out.println("Number of buildings: " + no_of_buildings_with_avg_height_required );
			System.out.println("area per building: " + area_per_building );
			System.out.println("Total building area required: "+ no_of_buildings_with_avg_height_required*area_per_building );
			System.out.println("Open space area: " + no_of_buildings_with_avg_height_required*open_space_area);
			System.out.println("Green cover area: " + no_of_buildings_with_avg_height_required*green_cover_area);
			System.out.println("Area for other amenities: " + no_of_buildings_with_avg_height_required*other_amenities);
			System.out.println("Total Area required: "+ no_of_buildings_with_avg_height_required*(area_per_building+open_space_area+other_amenities+green_cover_area));
		}
	}
	
	void showRegion()
	{
		System.out.println("treesArea: " + treesArea + "\nbushesArea: " + bushesArea + "\nbuildingDetails: " + buildingsDetails + "\nlakeArea: " + lakeArea +  "\nLCZ: " + lcz);
	}
	
	public int getRegionID() {
		return regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public float getTreesArea() {
		return treesArea;
	}

	public void setTreesArea(float treesArea) {
		this.treesArea = treesArea;
	}

	public float getBushesArea() {
		return bushesArea;
	}

	public void setBushesArea(float bushesArea) {
		this.bushesArea = bushesArea;
	}

	public float getLakeArea() {
		return lakeArea;
	}

	public void setLakeArea(float lakeArea) {
		this.lakeArea = lakeArea;
	}

	public float getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(float buildingArea) {
		this.buildingArea = buildingArea;
	}

	public float getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(float buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public float getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(float freeSpace) {
		this.freeSpace = freeSpace;
	}

	public float getPerviousSurfaceFraction() {
		return perviousSurfaceFraction;
	}

	public void setPerviousSurfaceFraction(float perviousSurfaceFraction) {
		this.perviousSurfaceFraction = perviousSurfaceFraction;
	}

	public float getBuildingAreaFraction() {
		return buildingAreaFraction;
	}

	public void setBuildingAreaFraction(float buildingAreaFraction) {
		this.buildingAreaFraction = buildingAreaFraction;
	}

	public float getTreeAreaFraction() {
		return treeAreaFraction;
	}

	public void setTreeAreaFraction(float treeAreaFraction) {
		this.treeAreaFraction = treeAreaFraction;
	}

	public float getBushesAreaFraction() {
		return bushesAreaFraction;
	}

	public void setBushesAreaFraction(float bushesAreaFraction) {
		this.bushesAreaFraction = bushesAreaFraction;
	}

	public float getLakeAreaFraction() {
		return lakeAreaFraction;
	}

	public void setLakeAreaFraction(float lakeAreaFraction) {
		this.lakeAreaFraction = lakeAreaFraction;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getLcz() {
		return lcz;
	}

	public void setLcz(String lcz) {
		this.lcz = lcz;
	}

	public String getBuildingsDetails() {
		return buildingsDetails;
	}

	public void setBuildingsDetails(String buildingsDetails) {
		this.buildingsDetails = buildingsDetails;
	}
	
//	public static void main(final String... args)
//	{
//		for (int i=2; i<3; i++)
//		{
//			Region region1 = new Region();
//			region1.calculateInitialLCZ(i);
//			region1.residentialPlan(1000);
////			region1.calculateLCZ();
//		}
//	}
}
